package softuni.banksters.domain.models.binding;

import org.springframework.web.multipart.MultipartFile;
import softuni.banksters.common.Constants;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class StockBindingModel {
    private String companyName;
    private String tickerCode;
    private String description;
    private String currency;
    private MultipartFile logo;
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

    public StockBindingModel() {
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getTickerCode() {
        return tickerCode;
    }

    public void setTickerCode(String tickerCode) {
        this.tickerCode = tickerCode;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    @Size(min = 0, max = 1200, message = Constants.TOO_LONG_INFORMATION_1200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getMinTradedQty() {
        return minTradedQty;
    }

    public void setMinTradedQty(String minTradedQty) {
        this.minTradedQty = minTradedQty;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getIncludedInIndex() {
        return includedInIndex;
    }

    public void setIncludedInIndex(String includedInIndex) {
        this.includedInIndex = includedInIndex;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getExDividendDate() {
        return exDividendDate;
    }

    public void setExDividendDate(String exDividendDate) {
        this.exDividendDate = exDividendDate;
    }

    @NotNull
    @NotEmpty(message = Constants.EMPTY_FIELD)
    @NotBlank(message = Constants.EMPTY_FIELD)
    public String getForwardDividendAndYield() {
        return forwardDividendAndYield;
    }

    public void setForwardDividendAndYield(String forwardDividendAndYield) {
        this.forwardDividendAndYield = forwardDividendAndYield;
    }

  
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }



    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getPreviousDayClosingPrice() {
        return previousDayClosingPrice;
    }

    public void setPreviousDayClosingPrice(BigDecimal previousDayClosingPrice) {
        this.previousDayClosingPrice = previousDayClosingPrice;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getJanuary() {
        return january;
    }

    public void setJanuary(BigDecimal january) {
        this.january = january;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getFebruary() {
        return february;
    }

    public void setFebruary(BigDecimal february) {
        this.february = february;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getMarch() {
        return march;
    }

    public void setMarch(BigDecimal march) {
        this.march = march;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getApril() {
        return april;
    }

    public void setApril(BigDecimal april) {
        this.april = april;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getMay() {
        return may;
    }

    public void setMay(BigDecimal may) {
        this.may = may;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getJune() {
        return june;
    }

    public void setJune(BigDecimal june) {
        this.june = june;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getJuly() {
        return july;
    }

    public void setJuly(BigDecimal july) {
        this.july = july;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getAugust() {
        return august;
    }

    public void setAugust(BigDecimal august) {
        this.august = august;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getSeptember() {
        return september;
    }

    public void setSeptember(BigDecimal september) {
        this.september = september;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getOctober() {
        return october;
    }

    public void setOctober(BigDecimal october) {
        this.october = october;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getNovember() {
        return november;
    }

    public void setNovember(BigDecimal november) {
        this.november = november;
    }


    @Positive(message = Constants.POSITIVE_INPUT)
    public BigDecimal getDecember() {
        return december;
    }

    public void setDecember(BigDecimal december) {
        this.december = december;
    }
}
