package hellocloud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuote {
    private String symbol;
    private String name;
    private String sector;
    private BigDecimal price;
    private BigDecimal yearlyHigh;
    private BigDecimal yearlyLow;
    private BigDecimal change = BigDecimal.valueOf(0);

    public void simulateChange() {
        double rand = ((double) new Random().nextInt(400) - 200) / 100;
        change = new BigDecimal(rand).setScale(2, BigDecimal.ROUND_UP);
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("Symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("sector")
    public String getSector() {
        return sector;
    }

    @JsonProperty("Sector")
    public void setSector(String sector) {
        this.sector = sector;
    }

    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }

    @JsonProperty("Price")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonProperty("yearlyHigh")
    public BigDecimal getYearlyHigh() {
        return yearlyHigh;
    }

    @JsonProperty("52 week high")
    public void setYearlyHigh(BigDecimal yearlyHigh) {
        this.yearlyHigh = yearlyHigh;
    }

    @JsonProperty("yearlyLow")
    public BigDecimal getYearlyLow() {
        return yearlyLow;
    }

    @JsonProperty("52 week low")
    public void setYearlyLow(BigDecimal yearlyLow) {
        this.yearlyLow = yearlyLow;
    }

    @JsonProperty("change")
    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    @JsonProperty("currentPrice")
    public BigDecimal getCurrentPrice() {
        if (null == this.price) {
            return new BigDecimal(0);
        }
        return this.price.add(this.change);
    }

    @JsonProperty("up")
    public Boolean getUo() {
        return change.doubleValue() > 0;
    }

    @JsonProperty("down")
    public Boolean getDown() {
        return change.doubleValue() < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockQuote that = (StockQuote) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {

        return Objects.hash(symbol);
    }
}
