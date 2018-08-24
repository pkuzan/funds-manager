package hellocloud.service;

import hellocloud.dao.AssetClassCrudRepository;
import hellocloud.dao.FundCrudRepository;
import hellocloud.exception.FundNotFoundException;
import hellocloud.exception.HoldingAlreadyExistsException;
import hellocloud.exception.HoldingNotFoundException;
import hellocloud.model.AssetClass;
import hellocloud.model.Fund;
import hellocloud.model.Holding;
import hellocloud.model.HoldingCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundService {
    @Autowired
    StockService stockService;

    @Autowired
    FundCrudRepository fundRepository;

    @Autowired
    AssetClassCrudRepository assetClassRepository;

    public void createFund(Fund fund) {
        fundRepository.save(fund);
    }

    public void deleteFund(String symbol) {
        fundRepository.delete(symbol);
    }

    public void editFund(Fund fund) {
        fundRepository.save(fund);
    }

    public void createHolding(String fundSymbol, Holding holding) {
        Fund fund = fundRepository.findOne(fundSymbol);
        if (null == fund) {
            throw new FundNotFoundException(String.format("Fund not found for symbol %s", fundSymbol));
        }
        Holding existingHolding = findHolding(fund, holding.getSymbol());
        if (null != existingHolding) {
            throw new HoldingAlreadyExistsException(String.format("Holding %s already exists for Fund %s", fundSymbol, holding.getSymbol()));
        }

        fund.getHoldings().add(holding);
        fundRepository.save(fund);
    }

    public void deleteHolding(String fundSymbol, String holdingSymbol) {
        Fund fund = fundRepository.findOne(fundSymbol);
        if (null == fund) {
            throw new FundNotFoundException(String.format("Fund not found for symbol %s", fundSymbol));
        }
        Holding holding = findHolding(fund, holdingSymbol);
        if (null == holding) {
            throw new HoldingNotFoundException(String.format("Holding %s not for Fund %s", fundSymbol, holding.getSymbol()));
        }
        fund.getHoldings().remove(holding);

        fundRepository.save(fund);
    }

    public void editHolding(String fundSymbol, Holding holding) {
        Fund fund = fundRepository.findOne(fundSymbol);
        if (null == fund) {
            throw new FundNotFoundException(String.format("Fund not found for symbol %s", fundSymbol));
        }
        Holding holdingToEdit = findHolding(fund, holding.getSymbol());
        if (null == holdingToEdit) {
            throw new HoldingNotFoundException(String.format("Holding %s not for Fund %s", fundSymbol, holding.getSymbol()));
        }
        removeHolding(fund, holding.getSymbol());
        fund.getHoldings().add(holding);

        fundRepository.save(fund);
    }

    public List<Fund> getFunds() {
        List<Fund> funds = new ArrayList<>();
        fundRepository.findAll().forEach(f -> funds.add(f));
        return funds;

    }

    public List<AssetClass> getAssetClasses() {
        List<AssetClass> assetClasses = new ArrayList<>();
        assetClassRepository.findAll().forEach(assetClasses::add);
        return assetClasses;
    }

    public Fund getFund(String symbol) {
        return fundRepository.findOne(symbol);
    }

    public List<HoldingCompany> getHoldingCompanies() {
        return stockService.getHoldingCompanies();
    }

    private Holding findHolding(Fund fund, String holdingSymbol) {
        for (Holding holding : fund.getHoldings()) {
            if (holding.getSymbol().equals(holdingSymbol)) {
                return holding;
            }
        }
        return null;
    }

    private void removeHolding(Fund fund, String holdingSymbol) {
        Holding holdingToReomove = findHolding(fund, holdingSymbol);

        if (holdingToReomove != null) {
            fund.getHoldings().remove(holdingToReomove);
        }
    }
}
