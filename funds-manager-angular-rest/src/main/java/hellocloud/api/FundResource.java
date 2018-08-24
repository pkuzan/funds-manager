package hellocloud.api;

import hellocloud.model.AssetClass;
import hellocloud.model.Fund;
import hellocloud.model.Holding;
import hellocloud.model.HoldingCompany;
import hellocloud.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fund")
public class FundResource {
    @Autowired
    FundService fundService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createFun(@RequestBody Fund fund) {
        this.fundService.createFund(fund);
    }

    @DeleteMapping(path = "/{symbol}")
    public void deleteFund(@PathVariable("symbol") String symbol) {
        this.fundService.deleteFund(symbol);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void editFund(@RequestBody Fund fund) {
        this.fundService.editFund(fund);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{fundSymbol/holding}")
    public void createHolding(@PathVariable("fundSymbol") String fundSymbol, @RequestBody Holding holding) {
        this.fundService.createHolding(fundSymbol, holding);
    }

    @DeleteMapping(path = "/{fundSymbol}/holding/{holdingSymbol}")
    public void deleteHolding(@PathVariable("fundSymbol") String fundSymbol, @PathVariable("holdingSymbol") String holdingSymbol) {
        this.fundService.deleteHolding(fundSymbol, holdingSymbol);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{fundSymbol/holding}")
    public void editHolding(@PathVariable("fundSymbol") String fundSymbol, @RequestBody Holding holding) {
        this.fundService.editHolding(fundSymbol, holding);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/asset-class")
    @RequestMapping("/asset-class")
    public List<AssetClass> getAssetClasses() {
        return this.fundService.getAssetClasses();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Fund> getFunds() {
        return fundService.getFunds();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{symbol}")
    public Fund getFund(@PathVariable("symbol") String symbol) {
        return fundService.getFund(symbol);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/holding")
    public List<HoldingCompany> getHoldings() {
        return fundService.getHoldingCompanies();
    }
}
