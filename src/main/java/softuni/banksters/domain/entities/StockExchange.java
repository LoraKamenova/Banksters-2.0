package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock_exchanges")
public class StockExchange extends BaseEntity{
    private String name;
    private String region;
    private String description;
    private String marketPlace;
    private String logoURL;
    private String marketCap;
    private String pictureURL;
    private String currency;
    private String website;
    private String timeZone;

    public StockExchange() {
    }

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "region", columnDefinition = "TEXT", nullable = false)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "market_place", columnDefinition = "TEXT", nullable = false)
    public String getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(String marketPlace) {
        this.marketPlace = marketPlace;
    }

    @Column(name = "logoURL", columnDefinition = "TEXT", nullable = false)
    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    @Column(name = "market_cap", columnDefinition = "TEXT", nullable = false)
    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    @Column(name = "pictureURL", columnDefinition = "TEXT", nullable = false)
    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
    @Column(name = "currency", columnDefinition = "TEXT", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "website", columnDefinition = "TEXT", nullable = false)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(name = "time_zone", columnDefinition = "TEXT", nullable = false)
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
