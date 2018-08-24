package hellocloud.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hellocloud.model.HoldingCompany;
import hellocloud.model.StockQuote;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class StockService {

    Map<String, StockQuote> stockQuotesDb = new LinkedHashMap<>();

    @PostConstruct
    public void init() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sp500.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockQuote> quotes = objectMapper.readValue(is, new TypeReference<List<StockQuote>>() {
        });

        for (StockQuote quote : quotes) {
            quote.simulateChange();
            stockQuotesDb.put(quote.getSymbol(), quote);
        }
    }

    public List<HoldingCompany> getHoldingCompanies() {
        List<HoldingCompany> holdingCompanies = new ArrayList<>();
        for (StockQuote quote : stockQuotesDb.values()) {
            holdingCompanies.add(new HoldingCompany(quote.getSymbol(), quote.getName()));
        }

        return holdingCompanies;
    }

    public List<StockQuote> getQuotes(List<String> symbols) {
        List<StockQuote> quotes = new ArrayList<>();
        for (String symbol : symbols) {
            if (stockQuotesDb.containsKey(symbol)) {
                StockQuote stockQuote = stockQuotesDb.get(symbol);
                stockQuote.simulateChange();
                quotes.add(stockQuote);
            } else {
                StockQuote stockQuote = new StockQuote();
                stockQuote.setSymbol(symbol);
                quotes.add(stockQuote);
            }
        }
        return quotes;
    }

    public Collection<StockQuote> getAllQuotes() {
        return stockQuotesDb.values();
    }
}
