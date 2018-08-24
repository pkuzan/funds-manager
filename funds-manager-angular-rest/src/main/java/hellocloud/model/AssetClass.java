package hellocloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class AssetClass {
    @Id
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    public AssetClass() {
    }

    public AssetClass(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetClass that = (AssetClass) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {

        return Objects.hash(symbol);
    }

    @Override
    public String toString() {
        return "AssetClass{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
