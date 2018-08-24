package hellocloud.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hellocloud.model.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class FundLoader {
    @Autowired
    FundCrudRepository fundCrudRepository;

    @PostConstruct
    public void init() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("fund.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<Fund> funds = objectMapper.readValue(is, new TypeReference<List<Fund>>() {
        });

        funds.forEach(fund -> fundCrudRepository.save(fund));
    }
}
