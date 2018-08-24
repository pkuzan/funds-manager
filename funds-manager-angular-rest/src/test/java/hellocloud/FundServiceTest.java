package hellocloud;

import hellocloud.dao.AssetClassCrudRepository;
import hellocloud.model.AssetClass;
import hellocloud.model.Fund;
import hellocloud.model.Holding;
import hellocloud.model.HoldingCompany;
import hellocloud.service.FundService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FundServiceTestConfig.class)
@DataJpaTest
public class FundServiceTest {
    @Autowired
    FundService fundService;

    @Autowired
    AssetClassCrudRepository assetClassRepository;

    @Test
    public void getHoldingCompanies() {
        List<HoldingCompany> companies = fundService.getHoldingCompanies();
        Assert.assertEquals(504, companies.size());
    }

    @Test
    public void createFund() {
        Fund fund = createFundFixture();
        fundService.createFund(fund);

        Fund saved = fundService.getFund("fund_1");
        Assert.assertNotNull(saved);
        Assert.assertEquals(2, saved.getHoldings().size());
        Assert.assertNotNull(saved.getAssetClass());
    }

    @Test
    public void getFunds() {
        Fund fund1 = createFundFixture();
        fundService.createFund(fund1);
        Fund fund2 = createFundFixture();
        fund2.setSymbol("fund_2");
        fundService.createFund(fund2);

        List<Fund> funds = fundService.getFunds();
        Assert.assertEquals(2, funds.size());
    }

    @Test
    public void deleteFund() {
        Fund fund = createFundFixture();
        fundService.createFund(fund);

        fundService.deleteFund("fund_1");
        Assert.assertNull(fundService.getFund("fund_1"));

        List<AssetClass> assetClasses = new ArrayList<>();
        assetClassRepository.findAll().forEach(assetClasses::add);
        Assert.assertEquals(1, assetClasses.size());

        fundService.createFund(fund);
    }

    @Test
    public void createHolding() {
        Fund fund = createFundFixture();
        fundService.createFund(fund);
        Assert.assertEquals(2, fund.getHoldings().size());

        Holding holding = new Holding("new", "new name", new BigDecimal(30));

        fundService.createHolding("fund_1", holding);

        Fund saved = fundService.getFund("fund_1");

        Assert.assertEquals(3, saved.getHoldings().size());
    }

    @Test
    public void deleteHolding() {
        Fund fund = createFundFixture();
        fundService.createFund(fund);
        Assert.assertEquals(2, fund.getHoldings().size());

        Holding holdingToDelete = fund.getHoldings().get(1);

        fundService.deleteHolding("fund_1", holdingToDelete.getSymbol());

        Fund saved = fundService.getFund("fund_1");

        Assert.assertEquals(1, saved.getHoldings().size());
    }

    @Test
    public void editHolding() {
        Fund fund = createFundFixture();
        fundService.createFund(fund);

        Holding holdingToEdit = fund.getHoldings().get(1);
        holdingToEdit.setPercentage(new BigDecimal(100));

        fundService.editHolding("fund_1", holdingToEdit);

        Fund saved = fundService.getFund("fund_1");

    }

    private Fund createFundFixture() {
        Fund fund = new Fund();
        fund.setSymbol("fund_1");
        fund.setActive(true);
        fund.setDescription("Description");
        fund.setDividendsFrequency("daily");
        fund.setName("Name");
        fund.setManager("Manager");
        fund.setHeadline("Headline");
        fund.setExpenseRatio(new BigDecimal(30));
        fund.setInceptionDate(LocalDateTime.now());
        fund.setAssetClass(new AssetClass("asset_symbol", "asset_name"));
        List<Holding> holdings = new ArrayList<>();
        holdings.add(new Holding("holding symbol", "holding_name", new BigDecimal(50)));
        holdings.add(new Holding("holding symbol_1", "holding_name_1", new BigDecimal(60)));
        fund.setHoldings(holdings);

        return fund;
    }
}
