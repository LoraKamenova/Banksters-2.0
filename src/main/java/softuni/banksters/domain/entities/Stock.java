package softuni.banksters.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "stocks")
public class Stock extends BaseEntity{

    private String companyName;
    private String tickerCode;
    private String description;
    private String currency;
    private String logoURL;
    private String minTradedQty;
    private String exchange;
    private String includedInIndex;
    private String exDividendDate;
    private String forwardDividendAndYield;
    private BigDecimal previousDayClosingPrice;

    private String year;
    private BigDecimal january;
    private BigDecimal february;
    private BigDecimal march;
    private BigDecimal april;
    private BigDecimal may;
    private BigDecimal june;
    private BigDecimal july;
    private BigDecimal august;
    private BigDecimal september;
    private BigDecimal october;
    private BigDecimal november;
    private BigDecimal december;

    public Stock() {
    }

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "ticker_code", columnDefinition = "TEXT", nullable = false)
    public String getTickerCode() {
        return tickerCode;
    }

    public void setTickerCode(String tickerCode) {
        this.tickerCode = tickerCode;
    }

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "currency", columnDefinition = "TEXT", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "logo_url", columnDefinition = "TEXT", nullable = false)
    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    @Column(name = "min_traded_qty", columnDefinition = "TEXT", nullable = false)
    public String getMinTradedQty() {
        return minTradedQty;
    }

    public void setMinTradedQty(String minTradedQty) {
        this.minTradedQty = minTradedQty;
    }

    @Column(name = "exchange", columnDefinition = "TEXT", nullable = false)
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Column(name = "included_in_index", columnDefinition = "TEXT", nullable = false)
    public String getIncludedInIndex() {
        return includedInIndex;
    }

    public void setIncludedInIndex(String includedInIndex) {
        this.includedInIndex = includedInIndex;
    }

    @Column(name = "ex_dividend_date", columnDefinition = "TEXT", nullable = false)
    public String getExDividendDate() {
        return exDividendDate;
    }

    public void setExDividendDate(String exDividendDate) {
        this.exDividendDate = exDividendDate;
    }

    @Column(name = "forward_dividend_and_yield", columnDefinition = "TEXT", nullable = false)
    public String getForwardDividendAndYield() {
        return forwardDividendAndYield;
    }

    public void setForwardDividendAndYield(String forwardDividendAndYield) {
        this.forwardDividendAndYield = forwardDividendAndYield;
    }

    @Column(name = "previous_day_closing_price", nullable = true)
    public BigDecimal getPreviousDayClosingPrice() {
        return previousDayClosingPrice;
    }

    public void setPreviousDayClosingPrice(BigDecimal previousDayClosingPrice) {
        this.previousDayClosingPrice = previousDayClosingPrice;
    }

    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(name = "jan")
    public BigDecimal getJanuary() {
        return january;
    }

    public void setJanuary(BigDecimal january) {
        this.january = january;
    }

    @Column(name = "feb")
    public BigDecimal getFebruary() {
        return february;
    }

    public void setFebruary(BigDecimal february) {
        this.february = february;
    }

    @Column(name = "mar")
    public BigDecimal getMarch() {
        return march;
    }

    public void setMarch(BigDecimal march) {
        this.march = march;
    }

    @Column(name = "apr")
    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        this.april = april;
    }

    @Column(name = "may")
    public BigDecimal getMay() {
        return may;
    }

    public void setMay(BigDecimal may) {
        this.may = may;
    }

    @Column(name = "jun")
    public BigDecimal getJune() {
        return june;
    }

    public void setJune(BigDecimal june) {
        this.june = june;
    }

    @Column(name = "jul")
    public BigDecimal getJuly() {
        return july;
    }

    public void setJuly(BigDecimal july) {
        this.july = july;
    }

    @Column(name = "aug")
    public BigDecimal getAugust() {
        return august;
    }

    public void setAugust(BigDecimal august) {
        this.august = august;
    }

    @Column(name = "sep")
    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        this.september = september;
    }

    @Column(name = "oct")
    public BigDecimal getOctober() {
        return october;
    }

    public void setOctober(BigDecimal october) {
        this.october = october;
    }

    @Column(name = "nov")
    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        this.november = november;
    }

    @Column(name = "december")

    public BigDecimal getDecember() {
        return december;
    }

    public void setDecember(BigDecimal december) {
        this.december = december;
    }
}
