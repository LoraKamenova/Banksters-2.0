package softuni.banksters.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softuni.banksters.domain.entities.Stock;
import softuni.banksters.domain.models.serivice.StockServiceModel;
import softuni.banksters.repository.StockRepository;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StockServiceTests {

    @Autowired
    private StockRepository stockRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }


    @Test
    public void stockService_createStock_ReturnsCorrect(){
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        StockServiceModel toBeSaved = new StockServiceModel();
        toBeSaved.setCompanyName("McDonald`s");
        toBeSaved.setTickerCode("Food");
        toBeSaved.setDescription("American company");
        toBeSaved.setCurrency("Long ago");
        toBeSaved.setLogoURL("www.");
        toBeSaved.setMinTradedQty("US");
        toBeSaved.setExchange("www.");
        toBeSaved.setIncludedInIndex("1000");
        toBeSaved.setExDividendDate("www.");
        toBeSaved.setForwardDividendAndYield("100");
        toBeSaved.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        toBeSaved.setYear("McDonald`s");
        toBeSaved.setJanuary(BigDecimal.valueOf(10));
        toBeSaved.setFebruary(BigDecimal.valueOf(10));
        toBeSaved.setMarch(BigDecimal.valueOf(10));
        toBeSaved.setApril(BigDecimal.valueOf(10));
        toBeSaved.setMay(BigDecimal.valueOf(10));
        toBeSaved.setJune(BigDecimal.valueOf(10));
        toBeSaved.setJuly(BigDecimal.valueOf(10));
        toBeSaved.setAugust(BigDecimal.valueOf(10));
        toBeSaved.setSeptember(BigDecimal.valueOf(10));
        toBeSaved.setOctober(BigDecimal.valueOf(10));
        toBeSaved.setNovember(BigDecimal.valueOf(10));
        toBeSaved.setDecember(BigDecimal.valueOf(10));


        StockServiceModel actual = stockService.createStock(toBeSaved);
        StockServiceModel expected = this.modelMapper.map(this.stockRepository.findAll().get(0), StockServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCompanyName(), actual.getCompanyName());
        Assert.assertEquals(expected.getTickerCode(), actual.getTickerCode());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getMinTradedQty(), actual.getMinTradedQty());
        Assert.assertEquals(expected.getExchange(), actual.getExchange());
        Assert.assertEquals(expected.getIncludedInIndex(), actual.getIncludedInIndex());
        Assert.assertEquals(expected.getExDividendDate(), actual.getExDividendDate());
        Assert.assertEquals(expected.getForwardDividendAndYield(), actual.getForwardDividendAndYield());
        Assert.assertEquals(expected.getPreviousDayClosingPrice(), actual.getPreviousDayClosingPrice());
        Assert.assertEquals(expected.getYear(), actual.getYear());
        Assert.assertEquals(expected.getJanuary(), actual.getJanuary());
        Assert.assertEquals(expected.getFebruary(), actual.getFebruary());
        Assert.assertEquals(expected.getMarch(), actual.getMarch());
        Assert.assertEquals(expected.getApril(), actual.getApril());
        Assert.assertEquals(expected.getMay(), actual.getMay());
        Assert.assertEquals(expected.getJune(), actual.getJune());
        Assert.assertEquals(expected.getJuly(), actual.getJuly());
        Assert.assertEquals(expected.getAugust(), actual.getAugust());
        Assert.assertEquals(expected.getSeptember(), actual.getSeptember());
        Assert.assertEquals(expected.getOctober(), actual.getOctober());
        Assert.assertEquals(expected.getNovember(), actual.getNovember());
        Assert.assertEquals(expected.getDecember(), actual.getDecember());
    }

    @Test
    public void stockService_editStock_ReturnsCorrect(){
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        Stock stock = new Stock();
        stock.setCompanyName("McDonald`s");
        stock.setTickerCode("Food");
        stock.setDescription("American company");
        stock.setCurrency("Long ago");
        stock.setLogoURL("www.");
        stock.setMinTradedQty("US");
        stock.setExchange("www.");
        stock.setIncludedInIndex("1000");
        stock.setExDividendDate("www.");
        stock.setForwardDividendAndYield("100");
        stock.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock.setYear("McDonald`s");
        stock.setJanuary(BigDecimal.valueOf(10));
        stock.setFebruary(BigDecimal.valueOf(10));
        stock.setMarch(BigDecimal.valueOf(10));
        stock.setApril(BigDecimal.valueOf(10));
        stock.setMay(BigDecimal.valueOf(10));
        stock.setJune(BigDecimal.valueOf(10));
        stock.setJuly(BigDecimal.valueOf(10));
        stock.setAugust(BigDecimal.valueOf(10));
        stock.setSeptember(BigDecimal.valueOf(10));
        stock.setOctober(BigDecimal.valueOf(10));
        stock.setNovember(BigDecimal.valueOf(10));
        stock.setDecember(BigDecimal.valueOf(10));

        stock = this.stockRepository.saveAndFlush(stock);

        StockServiceModel toBeEdited = new StockServiceModel();
        toBeEdited.setId(stock.getId());
        toBeEdited.setCompanyName("McDonald`s");
        toBeEdited.setTickerCode("Edited");
        toBeEdited.setDescription("Edited");
        toBeEdited.setCurrency("Edited");
        toBeEdited.setLogoURL("Edited");
        toBeEdited.setMinTradedQty("Edited");
        toBeEdited.setExchange("Edited");
        toBeEdited.setIncludedInIndex("Edited");
        toBeEdited.setExDividendDate("Edited");
        toBeEdited.setForwardDividendAndYield("Edited");
        toBeEdited.setPreviousDayClosingPrice(BigDecimal.valueOf(5));
        toBeEdited.setYear("Edited");
        toBeEdited.setJanuary(BigDecimal.valueOf(5));
        toBeEdited.setFebruary(BigDecimal.valueOf(5));
        toBeEdited.setMarch(BigDecimal.valueOf(5));
        toBeEdited.setApril(BigDecimal.valueOf(5));
        toBeEdited.setMay(BigDecimal.valueOf(5));
        toBeEdited.setJune(BigDecimal.valueOf(5));
        toBeEdited.setJuly(BigDecimal.valueOf(5));
        toBeEdited.setAugust(BigDecimal.valueOf(5));
        toBeEdited.setSeptember(BigDecimal.valueOf(5));
        toBeEdited.setOctober(BigDecimal.valueOf(5));
        toBeEdited.setNovember(BigDecimal.valueOf(5));
        toBeEdited.setDecember(BigDecimal.valueOf(5));

        StockServiceModel actual = stockService.editStock(stock.getId(), toBeEdited);
        StockServiceModel expected = this.modelMapper.map(this.stockRepository.findAll().get(0), StockServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCompanyName(), actual.getCompanyName());
        Assert.assertEquals(expected.getTickerCode(), actual.getTickerCode());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getMinTradedQty(), actual.getMinTradedQty());
        Assert.assertEquals(expected.getExchange(), actual.getExchange());
        Assert.assertEquals(expected.getIncludedInIndex(), actual.getIncludedInIndex());
        Assert.assertEquals(expected.getExDividendDate(), actual.getExDividendDate());
        Assert.assertEquals(expected.getForwardDividendAndYield(), actual.getForwardDividendAndYield());
        Assert.assertEquals(expected.getYear(), actual.getYear());
        Assert.assertEquals(expected.getJanuary(), actual.getJanuary());
        Assert.assertEquals(expected.getFebruary(), actual.getFebruary());
        Assert.assertEquals(expected.getMarch(), actual.getMarch());
        Assert.assertEquals(expected.getApril(), actual.getApril());
        Assert.assertEquals(expected.getMay(), actual.getMay());
        Assert.assertEquals(expected.getJune(), actual.getJune());
        Assert.assertEquals(expected.getJuly(), actual.getJuly());
        Assert.assertEquals(expected.getAugust(), actual.getAugust());
        Assert.assertEquals(expected.getSeptember(), actual.getSeptember());
        Assert.assertEquals(expected.getOctober(), actual.getOctober());
        Assert.assertEquals(expected.getNovember(), actual.getNovember());
        Assert.assertEquals(expected.getDecember(), actual.getDecember());
    }

    @Test
    public void stockService_deleteStockWithValidId_ReturnsCorrect() {
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        Stock stock = new Stock();
        stock.setCompanyName("McDonald`s");
        stock.setTickerCode("Food");
        stock.setDescription("American company");
        stock.setCurrency("Long ago");
        stock.setLogoURL("www.");
        stock.setMinTradedQty("US");
        stock.setExchange("www.");
        stock.setIncludedInIndex("1000");
        stock.setExDividendDate("www.");
        stock.setForwardDividendAndYield("100");
        stock.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock.setYear("McDonald`s");
        stock.setJanuary(BigDecimal.valueOf(10));
        stock.setFebruary(BigDecimal.valueOf(10));
        stock.setMarch(BigDecimal.valueOf(10));
        stock.setApril(BigDecimal.valueOf(10));
        stock.setMay(BigDecimal.valueOf(10));
        stock.setJune(BigDecimal.valueOf(10));
        stock.setJuly(BigDecimal.valueOf(10));
        stock.setAugust(BigDecimal.valueOf(10));
        stock.setSeptember(BigDecimal.valueOf(10));
        stock.setOctober(BigDecimal.valueOf(10));
        stock.setNovember(BigDecimal.valueOf(10));
        stock.setDecember(BigDecimal.valueOf(10));

        stock = this.stockRepository.saveAndFlush(stock);

        stockService.deleteStock(stock.getId());

        long expectedCount = 0;
        long accountCount = this.stockRepository.count();

        Assert.assertEquals(expectedCount, accountCount);
    }

    @Test(expected = Exception.class)
    public void stockService_deleteStockWithInvalidId_ReturnsCorrect() {
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        Stock stock = new Stock();
        stock.setCompanyName("McDonald`s");
        stock.setTickerCode("Food");
        stock.setDescription("American company");
        stock.setCurrency("Long ago");
        stock.setLogoURL("www.");
        stock.setMinTradedQty("US");
        stock.setExchange("www.");
        stock.setIncludedInIndex("1000");
        stock.setExDividendDate("www.");
        stock.setForwardDividendAndYield("100");
        stock.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock.setYear("McDonald`s");
        stock.setJanuary(BigDecimal.valueOf(10));
        stock.setFebruary(BigDecimal.valueOf(10));
        stock.setMarch(BigDecimal.valueOf(10));
        stock.setApril(BigDecimal.valueOf(10));
        stock.setMay(BigDecimal.valueOf(10));
        stock.setJune(BigDecimal.valueOf(10));
        stock.setJuly(BigDecimal.valueOf(10));
        stock.setAugust(BigDecimal.valueOf(10));
        stock.setSeptember(BigDecimal.valueOf(10));
        stock.setOctober(BigDecimal.valueOf(10));
        stock.setNovember(BigDecimal.valueOf(10));
        stock.setDecember(BigDecimal.valueOf(10));

        stock = this.stockRepository.saveAndFlush(stock);

        stockService.deleteStock("InvalidId");

    }

    @Test
    public void stockService_findStockWithValidId_ReturnsCorrect() {
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        Stock stock = new Stock();
        stock.setCompanyName("McDonald`s");
        stock.setTickerCode("Food");
        stock.setDescription("American company");
        stock.setCurrency("Long ago");
        stock.setLogoURL("www.");
        stock.setMinTradedQty("US");
        stock.setExchange("www.");
        stock.setIncludedInIndex("1000");
        stock.setExDividendDate("www.");
        stock.setForwardDividendAndYield("100");
        stock.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock.setYear("McDonald`s");
        stock.setJanuary(BigDecimal.valueOf(10));
        stock.setFebruary(BigDecimal.valueOf(10));
        stock.setMarch(BigDecimal.valueOf(10));
        stock.setApril(BigDecimal.valueOf(10));
        stock.setMay(BigDecimal.valueOf(10));
        stock.setJune(BigDecimal.valueOf(10));
        stock.setJuly(BigDecimal.valueOf(10));
        stock.setAugust(BigDecimal.valueOf(10));
        stock.setSeptember(BigDecimal.valueOf(10));
        stock.setOctober(BigDecimal.valueOf(10));
        stock.setNovember(BigDecimal.valueOf(10));
        stock.setDecember(BigDecimal.valueOf(10));

        stock = this.stockRepository.saveAndFlush(stock);

        StockServiceModel actual = stockService.findStockById(stock.getId());
        StockServiceModel expected = this.modelMapper.map(stock, StockServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCompanyName(), actual.getCompanyName());
        Assert.assertEquals(expected.getTickerCode(), actual.getTickerCode());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getMinTradedQty(), actual.getMinTradedQty());
        Assert.assertEquals(expected.getExchange(), actual.getExchange());
        Assert.assertEquals(expected.getIncludedInIndex(), actual.getIncludedInIndex());
        Assert.assertEquals(expected.getExDividendDate(), actual.getExDividendDate());
        Assert.assertEquals(expected.getForwardDividendAndYield(), actual.getForwardDividendAndYield());
        Assert.assertEquals(expected.getYear(), actual.getYear());
        Assert.assertEquals(expected.getJanuary(), actual.getJanuary());
        Assert.assertEquals(expected.getFebruary(), actual.getFebruary());
        Assert.assertEquals(expected.getMarch(), actual.getMarch());
        Assert.assertEquals(expected.getApril(), actual.getApril());
        Assert.assertEquals(expected.getMay(), actual.getMay());
        Assert.assertEquals(expected.getJune(), actual.getJune());
        Assert.assertEquals(expected.getJuly(), actual.getJuly());
        Assert.assertEquals(expected.getAugust(), actual.getAugust());
        Assert.assertEquals(expected.getSeptember(), actual.getSeptember());
        Assert.assertEquals(expected.getOctober(), actual.getOctober());
        Assert.assertEquals(expected.getNovember(), actual.getNovember());
        Assert.assertEquals(expected.getDecember(), actual.getDecember());
    }

    @Test(expected = Exception.class)
    public void stockService_findStockWithInvalidId_ReturnsCorrect() {
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        Stock stock = new Stock();
        stock.setCompanyName("McDonald`s");
        stock.setTickerCode("Food");
        stock.setDescription("American company");
        stock.setCurrency("Long ago");
        stock.setLogoURL("www.");
        stock.setMinTradedQty("US");
        stock.setExchange("www.");
        stock.setIncludedInIndex("1000");
        stock.setExDividendDate("www.");
        stock.setForwardDividendAndYield("100");
        stock.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock.setYear("McDonald`s");
        stock.setJanuary(BigDecimal.valueOf(10));
        stock.setFebruary(BigDecimal.valueOf(10));
        stock.setMarch(BigDecimal.valueOf(10));
        stock.setApril(BigDecimal.valueOf(10));
        stock.setMay(BigDecimal.valueOf(10));
        stock.setJune(BigDecimal.valueOf(10));
        stock.setJuly(BigDecimal.valueOf(10));
        stock.setAugust(BigDecimal.valueOf(10));
        stock.setSeptember(BigDecimal.valueOf(10));
        stock.setOctober(BigDecimal.valueOf(10));
        stock.setNovember(BigDecimal.valueOf(10));
        stock.setDecember(BigDecimal.valueOf(10));

        stock = this.stockRepository.saveAndFlush(stock);

        stockService.findStockById("InvalidId");
    }

    @Test
    public void stockService_findAllStockWithValidId_ReturnsCorrect() {
        StockService stockService = new StockServiceImpl(this.stockRepository, this.modelMapper);

        Stock stock = new Stock();
        stock.setCompanyName("McDonald`s");
        stock.setTickerCode("Food");
        stock.setDescription("American company");
        stock.setCurrency("Long ago");
        stock.setLogoURL("www.");
        stock.setMinTradedQty("US");
        stock.setExchange("www.");
        stock.setIncludedInIndex("1000");
        stock.setExDividendDate("www.");
        stock.setForwardDividendAndYield("100");
        stock.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock.setYear("McDonald`s");
        stock.setJanuary(BigDecimal.valueOf(10));
        stock.setFebruary(BigDecimal.valueOf(10));
        stock.setMarch(BigDecimal.valueOf(10));
        stock.setApril(BigDecimal.valueOf(10));
        stock.setMay(BigDecimal.valueOf(10));
        stock.setJune(BigDecimal.valueOf(10));
        stock.setJuly(BigDecimal.valueOf(10));
        stock.setAugust(BigDecimal.valueOf(10));
        stock.setSeptember(BigDecimal.valueOf(10));
        stock.setOctober(BigDecimal.valueOf(10));
        stock.setNovember(BigDecimal.valueOf(10));
        stock.setDecember(BigDecimal.valueOf(10));

        stock = this.stockRepository.saveAndFlush(stock);

        Stock stock2 = new Stock();
        stock2.setCompanyName("McDonald`s");
        stock2.setTickerCode("Food");
        stock2.setDescription("American company");
        stock2.setCurrency("Long ago");
        stock2.setLogoURL("www.");
        stock2.setMinTradedQty("US");
        stock2.setExchange("www.");
        stock2.setIncludedInIndex("1000");
        stock2.setExDividendDate("www.");
        stock2.setForwardDividendAndYield("100");
        stock2.setPreviousDayClosingPrice(BigDecimal.valueOf(10));
        stock2.setYear("McDonald`s");
        stock2.setJanuary(BigDecimal.valueOf(10));
        stock2.setFebruary(BigDecimal.valueOf(10));
        stock2.setMarch(BigDecimal.valueOf(10));
        stock2.setApril(BigDecimal.valueOf(10));
        stock2.setMay(BigDecimal.valueOf(10));
        stock2.setJune(BigDecimal.valueOf(10));
        stock2.setJuly(BigDecimal.valueOf(10));
        stock2.setAugust(BigDecimal.valueOf(10));
        stock2.setSeptember(BigDecimal.valueOf(10));
        stock2.setOctober(BigDecimal.valueOf(10));
        stock2.setNovember(BigDecimal.valueOf(10));
        stock2.setDecember(BigDecimal.valueOf(10));

        stock2 = this.stockRepository.saveAndFlush(stock2);

        List<StockServiceModel> actual = stockService.findAllStock();

        Assert.assertEquals(2, actual.size());
    }
}
