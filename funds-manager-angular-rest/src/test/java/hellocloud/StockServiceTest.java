package hellocloud;

import hellocloud.model.HoldingCompany;
import hellocloud.model.StockQuote;
import hellocloud.service.StockService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockServiceTestConfig.class)
@DataJpaTest
public class StockServiceTest {

    @Autowired
    StockService stockService;

    @Test
    public void init() {
        Assert.assertEquals(504, stockService.getAllQuotes().size());
    }

    @Test
    public void getHoldingCompanies() {
        List<HoldingCompany> compnaines = stockService.getHoldingCompanies();
        Assert.assertEquals(504, compnaines.size());
    }

    @Test
    public void getQuotes() {
        List<String> symbols = new ArrayList<>();
        symbols.add("ABBV");
        symbols.add("MMM");
        symbols.add("ABT");
        symbols.add("ZZZ");

        List<StockQuote> quotes = stockService.getQuotes(symbols);

        for (StockQuote quote : quotes) {
            if (!quote.getSymbol().equals("ZZZ")) {
                Assert.assertTrue(quote.getCurrentPrice().intValue() > 0);
            } else {
                Assert.assertNull(quote.getPrice());
            }
        }

    }
}
