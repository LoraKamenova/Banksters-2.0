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
import softuni.banksters.domain.entities.StockExchange;
import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;
import softuni.banksters.repository.StockExchangeRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StockExchangeServiceTests {

    @Autowired
    private StockExchangeRepository stockExchangeRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }


    @Test
    public void stockExchangeService_createStockExchange_ReturnsCorrect(){
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchangeServiceModel toBeSaved = new StockExchangeServiceModel();
        toBeSaved.setName("McDonald`s");
        toBeSaved.setRegion("Food");
        toBeSaved.setDescription("American company");
        toBeSaved.setCurrency("Long ago");
        toBeSaved.setLogoURL("www.");
        toBeSaved.setMarketPlace("US");
        toBeSaved.setMarketCap("www.");
        toBeSaved.setPictureURL("1000");
        toBeSaved.setTimeZone("www.");
        toBeSaved.setWebsite("100");

        StockExchangeServiceModel actual = stockExchangeService.createStockExchange(toBeSaved);
        StockExchangeServiceModel expected = this.modelMapper.map(this.stockExchangeRepository.findAll().get(0), StockExchangeServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getRegion(), actual.getRegion());
        Assert.assertEquals(expected.getMarketPlace(), actual.getMarketPlace());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getMarketCap(), actual.getMarketCap());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getTimeZone(), actual.getTimeZone());
        Assert.assertEquals(expected.getPictureURL(), actual.getPictureURL());
    }

    @Test
    public void stockService_editStock_ReturnsCorrect(){
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("McDonald`s");
        stockExchange.setRegion("Food");
        stockExchange.setDescription("American company");
        stockExchange.setCurrency("Long ago");
        stockExchange.setLogoURL("www.");
        stockExchange.setMarketPlace("US");
        stockExchange.setMarketCap("www.");
        stockExchange.setPictureURL("1000");
        stockExchange.setTimeZone("www.");
        stockExchange.setWebsite("100");

        stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);

        StockExchangeServiceModel toBeEdited = new StockExchangeServiceModel();
        toBeEdited.setName("Edited");
        toBeEdited.setRegion("Edited");
        toBeEdited.setDescription("Edited");
        toBeEdited.setCurrency("Edited");
        toBeEdited.setLogoURL("Edited");
        toBeEdited.setMarketPlace("Edited");
        toBeEdited.setMarketCap("Edited");
        toBeEdited.setPictureURL("Edited");
        toBeEdited.setTimeZone("Edited");
        toBeEdited.setWebsite("Edited");

        StockExchangeServiceModel actual = stockExchangeService.editStockExchange(stockExchange.getId(), toBeEdited);
        StockExchangeServiceModel expected = this.modelMapper.map(this.stockExchangeRepository.findAll().get(0), StockExchangeServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getRegion(), actual.getRegion());
        Assert.assertEquals(expected.getMarketPlace(), actual.getMarketPlace());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getMarketCap(), actual.getMarketCap());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getTimeZone(), actual.getTimeZone());
        Assert.assertEquals(expected.getPictureURL(), actual.getPictureURL());
    }

    @Test
    public void stockService_deleteStockWithValidId_ReturnsCorrect() {
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("McDonald`s");
        stockExchange.setRegion("Food");
        stockExchange.setDescription("American company");
        stockExchange.setCurrency("Long ago");
        stockExchange.setLogoURL("www.");
        stockExchange.setMarketPlace("US");
        stockExchange.setMarketCap("www.");
        stockExchange.setPictureURL("1000");
        stockExchange.setTimeZone("www.");
        stockExchange.setWebsite("100");

        stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);

        stockExchangeService.deleteStockExchange(stockExchange.getId());

        long expectedCount = 0;
        long accountCount = this.stockExchangeRepository.count();

        Assert.assertEquals(expectedCount, accountCount);
    }

    @Test(expected = Exception.class)
    public void stockService_deleteStockWithInvalidId_ReturnsCorrect() {
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("McDonald`s");
        stockExchange.setRegion("Food");
        stockExchange.setDescription("American company");
        stockExchange.setCurrency("Long ago");
        stockExchange.setLogoURL("www.");
        stockExchange.setMarketPlace("US");
        stockExchange.setMarketCap("www.");
        stockExchange.setPictureURL("1000");
        stockExchange.setTimeZone("www.");
        stockExchange.setWebsite("100");

        stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);

        stockExchangeService.deleteStockExchange("InvalidId");

    }

    @Test
    public void stockService_findStockWithValidId_ReturnsCorrect() {
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("McDonald`s");
        stockExchange.setRegion("Food");
        stockExchange.setDescription("American company");
        stockExchange.setCurrency("Long ago");
        stockExchange.setLogoURL("www.");
        stockExchange.setMarketPlace("US");
        stockExchange.setMarketCap("www.");
        stockExchange.setPictureURL("1000");
        stockExchange.setTimeZone("www.");
        stockExchange.setWebsite("100");

        stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);

        StockExchangeServiceModel actual = stockExchangeService.findStockExchangeById(stockExchange.getId());
        StockExchangeServiceModel expected = this.modelMapper.map(stockExchange, StockExchangeServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getRegion(), actual.getRegion());
        Assert.assertEquals(expected.getMarketPlace(), actual.getMarketPlace());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getCurrency(), actual.getCurrency());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getMarketCap(), actual.getMarketCap());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getTimeZone(), actual.getTimeZone());
        Assert.assertEquals(expected.getPictureURL(), actual.getPictureURL());
    }

    @Test(expected = Exception.class)
    public void stockService_findStockWithInvalidId_ReturnsCorrect() {
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("McDonald`s");
        stockExchange.setRegion("Food");
        stockExchange.setDescription("American company");
        stockExchange.setCurrency("Long ago");
        stockExchange.setLogoURL("www.");
        stockExchange.setMarketPlace("US");
        stockExchange.setMarketCap("www.");
        stockExchange.setPictureURL("1000");
        stockExchange.setTimeZone("www.");
        stockExchange.setWebsite("100");

        stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);

        stockExchangeService.findStockExchangeById("InvalidId");
    }

    @Test
    public void stockService_findAllStockWithValidId_ReturnsCorrect() {
        StockExchangeService stockExchangeService = new StockExchangeServiceImpl(this.stockExchangeRepository, this.modelMapper);

        StockExchange stockExchange = new StockExchange();
        stockExchange.setName("McDonald`s");
        stockExchange.setRegion("Food");
        stockExchange.setDescription("American company");
        stockExchange.setCurrency("Long ago");
        stockExchange.setLogoURL("www.");
        stockExchange.setMarketPlace("US");
        stockExchange.setMarketCap("www.");
        stockExchange.setPictureURL("1000");
        stockExchange.setTimeZone("www.");
        stockExchange.setWebsite("100");

        stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);

        StockExchange stockExchange2 = new StockExchange();
        stockExchange2.setName("McDonald`s");
        stockExchange2.setRegion("Food");
        stockExchange2.setDescription("American company");
        stockExchange2.setCurrency("Long ago");
        stockExchange2.setLogoURL("www.");
        stockExchange2.setMarketPlace("US");
        stockExchange2.setMarketCap("www.");
        stockExchange2.setPictureURL("1000");
        stockExchange2.setTimeZone("www.");
        stockExchange2.setWebsite("100");

        stockExchange2 = this.stockExchangeRepository.saveAndFlush(stockExchange2);

        List<StockExchangeServiceModel> actual = stockExchangeService.findAllStockExchange();

        Assert.assertEquals(2, actual.size());
    }
}
