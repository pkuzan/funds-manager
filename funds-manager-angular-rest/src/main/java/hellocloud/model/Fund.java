package hellocloud.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Fund {
    @Id
    private String symbol;
    @Column
    private String name;
    @Column
    private String headline;
    @Column
    private String description;
    @Column
    private BigDecimal expenseRatio;
    @Column
    private String manager;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ac_id")
    private AssetClass assetClass;

    @Column
    private LocalDateTime inceptionDate;

    @Column
    private String dividendsFrequency;

    @ElementCollection
    @JoinColumn(name = "fund_symbol")
    private List<Holding> holdings;

    @Column
    private Boolean active;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getExpenseRatio() {
        return expenseRatio;
    }

    public void setExpenseRatio(BigDecimal expenseRatio) {
        this.expenseRatio = expenseRatio;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public AssetClass getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(AssetClass assetClass) {
        this.assetClass = assetClass;
    }

    public LocalDateTime getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(LocalDateTime inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public String getDividendsFrequency() {
        return dividendsFrequency;
    }

    public void setDividendsFrequency(String dividendsFrequency) {
        this.dividendsFrequency = dividendsFrequency;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return Objects.equals(symbol, fund.symbol);
    }

    @Override
    public int hashCode() {

        return Objects.hash(symbol);
    }
}
