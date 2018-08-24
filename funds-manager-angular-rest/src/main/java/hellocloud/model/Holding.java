package hellocloud.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Holding {
    @Column
    private String symbol;
    @Column
    private String name;
    @Column
    private BigDecimal percentage;

    public Holding() {
    }

    public Holding(String symbol, String name, BigDecimal percentage) {
        this.symbol = symbol;
        this.name = name;
        this.percentage = percentage;
    }

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

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holding holding = (Holding) o;
        return Objects.equals(symbol, holding.symbol);
    }

    @Override
    public int hashCode() {

        return Objects.hash(symbol);
    }
}
