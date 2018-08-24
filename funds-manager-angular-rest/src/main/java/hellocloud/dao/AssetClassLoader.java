package hellocloud.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hellocloud.model.AssetClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Configuration
public class AssetClassLoader {
    @Autowired
    AssetClassCrudRepository assetClassCrudRepository;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = new ClassPathResource("asset-class.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<AssetClass> classes = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<AssetClass>>() {
        });

        for (AssetClass assetClass : classes) {
            assetClassCrudRepository.save(assetClass);
        }
    }
}
