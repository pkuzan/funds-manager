package hellocloud.api;

import hellocloud.model.StockQuote;
import hellocloud.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/stock")
public class StockResource {
    @Autowired
    StockService stockService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/quotes")
    public List<StockQuote> quotes(@RequestParam("symbols") String symbols) {
        return stockService.getQuotes(Arrays.asList(symbols.split(",")));
    }
}
