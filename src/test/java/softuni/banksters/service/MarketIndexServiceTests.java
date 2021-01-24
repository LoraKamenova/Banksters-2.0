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
import softuni.banksters.domain.entities.MarketIndex;
import softuni.banksters.domain.models.serivice.MarketIndexServiceModel;
import softuni.banksters.repository.MarketIndexRepository;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MarketIndexServiceTests {

    @Autowired
    private MarketIndexRepository marketIndexRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }


    @Test
    public void marketIndexService_createMarketIndex_ReturnsCorrect(){
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndexServiceModel toBeSaved = new MarketIndexServiceModel();
        toBeSaved.setName("McDonald`s");
        toBeSaved.setPictureURL("Food");
        toBeSaved.setDescription("American company");
        toBeSaved.setWebsite("Long ago");
        toBeSaved.setLogoURL("www.");
        toBeSaved.setFoundation("US");
        toBeSaved.setExchange("www.");
        toBeSaved.setOperator("1000");
        toBeSaved.setConstituents("www.");
        toBeSaved.setType("100");
        toBeSaved.setClosingValue(BigDecimal.valueOf(10));
        toBeSaved.setYear("2020");
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


        MarketIndexServiceModel actual = marketIndexService.createMarketIndex(toBeSaved);
        MarketIndexServiceModel expected = this.modelMapper.map(this.marketIndexRepository.findAll().get(0), MarketIndexServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPictureURL(), actual.getPictureURL());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getFoundation(), actual.getFoundation());
        Assert.assertEquals(expected.getExchange(), actual.getExchange());
        Assert.assertEquals(expected.getOperator(), actual.getOperator());
        Assert.assertEquals(expected.getConstituents(), actual.getConstituents());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getClosingValue(), actual.getClosingValue());
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
    public void marketIndexService_editMarketIndex_ReturnsCorrect(){
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndex marketIndex = new MarketIndex();
        marketIndex.setName("McDonald`s");
        marketIndex.setPictureURL("Food");
        marketIndex.setDescription("American company");
        marketIndex.setWebsite("Long ago");
        marketIndex.setLogoURL("www.");
        marketIndex.setFoundation("US");
        marketIndex.setExchange("www.");
        marketIndex.setOperator("1000");
        marketIndex.setConstituents("www.");
        marketIndex.setType("100");
        marketIndex.setClosingValue(BigDecimal.valueOf(10));
        marketIndex.setYear("2020");
        marketIndex.setJanuary(BigDecimal.valueOf(10));
        marketIndex.setFebruary(BigDecimal.valueOf(10));
        marketIndex.setMarch(BigDecimal.valueOf(10));
        marketIndex.setApril(BigDecimal.valueOf(10));
        marketIndex.setMay(BigDecimal.valueOf(10));
        marketIndex.setJune(BigDecimal.valueOf(10));
        marketIndex.setJuly(BigDecimal.valueOf(10));
        marketIndex.setAugust(BigDecimal.valueOf(10));
        marketIndex.setSeptember(BigDecimal.valueOf(10));
        marketIndex.setOctober(BigDecimal.valueOf(10));
        marketIndex.setNovember(BigDecimal.valueOf(10));
        marketIndex.setDecember(BigDecimal.valueOf(10));

        marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);

        MarketIndexServiceModel toBeEdited = new MarketIndexServiceModel();
        toBeEdited.setName("Edited");
        toBeEdited.setPictureURL("Edited");
        toBeEdited.setDescription("Edited");
        toBeEdited.setWebsite("Edited");
        toBeEdited.setLogoURL("Edited");
        toBeEdited.setFoundation("Edited");
        toBeEdited.setExchange("Edited");
        toBeEdited.setOperator("Edited");
        toBeEdited.setConstituents("Edited");
        toBeEdited.setType("Edited");
        toBeEdited.setClosingValue(BigDecimal.valueOf(5));
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

        MarketIndexServiceModel actual = marketIndexService.editMarketIndex(marketIndex.getId(), toBeEdited);
        MarketIndexServiceModel expected = this.modelMapper.map(this.marketIndexRepository.findAll().get(0), MarketIndexServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPictureURL(), actual.getPictureURL());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getFoundation(), actual.getFoundation());
        Assert.assertEquals(expected.getExchange(), actual.getExchange());
        Assert.assertEquals(expected.getOperator(), actual.getOperator());
        Assert.assertEquals(expected.getConstituents(), actual.getConstituents());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getClosingValue(), actual.getClosingValue());
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
    public void marketIndexService_deleteMarketIndexWithValidId_ReturnsCorrect() {
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndex marketIndex = new MarketIndex();
        marketIndex.setName("McDonald`s");
        marketIndex.setPictureURL("Food");
        marketIndex.setDescription("American company");
        marketIndex.setWebsite("Long ago");
        marketIndex.setLogoURL("www.");
        marketIndex.setFoundation("US");
        marketIndex.setExchange("www.");
        marketIndex.setOperator("1000");
        marketIndex.setConstituents("www.");
        marketIndex.setType("100");
        marketIndex.setClosingValue(BigDecimal.valueOf(10));
        marketIndex.setYear("2020");
        marketIndex.setJanuary(BigDecimal.valueOf(10));
        marketIndex.setFebruary(BigDecimal.valueOf(10));
        marketIndex.setMarch(BigDecimal.valueOf(10));
        marketIndex.setApril(BigDecimal.valueOf(10));
        marketIndex.setMay(BigDecimal.valueOf(10));
        marketIndex.setJune(BigDecimal.valueOf(10));
        marketIndex.setJuly(BigDecimal.valueOf(10));
        marketIndex.setAugust(BigDecimal.valueOf(10));
        marketIndex.setSeptember(BigDecimal.valueOf(10));
        marketIndex.setOctober(BigDecimal.valueOf(10));
        marketIndex.setNovember(BigDecimal.valueOf(10));
        marketIndex.setDecember(BigDecimal.valueOf(10));

        marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);

        marketIndexService.deleteMarketIndex(marketIndex.getId());

        long expectedCount = 0;
        long accountCount = this.marketIndexRepository.count();

        Assert.assertEquals(expectedCount, accountCount);
    }

    @Test(expected = Exception.class)
    public void marketIndexService_deleteMarketIndexWithInvalidId_ReturnsCorrect() {
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndex marketIndex = new MarketIndex();
        marketIndex.setName("McDonald`s");
        marketIndex.setPictureURL("Food");
        marketIndex.setDescription("American company");
        marketIndex.setWebsite("Long ago");
        marketIndex.setLogoURL("www.");
        marketIndex.setFoundation("US");
        marketIndex.setExchange("www.");
        marketIndex.setOperator("1000");
        marketIndex.setConstituents("www.");
        marketIndex.setType("100");
        marketIndex.setClosingValue(BigDecimal.valueOf(10));
        marketIndex.setYear("2020");
        marketIndex.setJanuary(BigDecimal.valueOf(10));
        marketIndex.setFebruary(BigDecimal.valueOf(10));
        marketIndex.setMarch(BigDecimal.valueOf(10));
        marketIndex.setApril(BigDecimal.valueOf(10));
        marketIndex.setMay(BigDecimal.valueOf(10));
        marketIndex.setJune(BigDecimal.valueOf(10));
        marketIndex.setJuly(BigDecimal.valueOf(10));
        marketIndex.setAugust(BigDecimal.valueOf(10));
        marketIndex.setSeptember(BigDecimal.valueOf(10));
        marketIndex.setOctober(BigDecimal.valueOf(10));
        marketIndex.setNovember(BigDecimal.valueOf(10));
        marketIndex.setDecember(BigDecimal.valueOf(10));

        marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);

        marketIndexService.deleteMarketIndex("InvalidId");

    }

    @Test
    public void marketIndexService_findMarketIndexWithValidId_ReturnsCorrect() {
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndex marketIndex = new MarketIndex();
        marketIndex.setName("McDonald`s");
        marketIndex.setPictureURL("Food");
        marketIndex.setDescription("American company");
        marketIndex.setWebsite("Long ago");
        marketIndex.setLogoURL("www.");
        marketIndex.setFoundation("US");
        marketIndex.setExchange("www.");
        marketIndex.setOperator("1000");
        marketIndex.setConstituents("www.");
        marketIndex.setType("100");
        marketIndex.setClosingValue(BigDecimal.valueOf(10));
        marketIndex.setYear("2020");
        marketIndex.setJanuary(BigDecimal.valueOf(10));
        marketIndex.setFebruary(BigDecimal.valueOf(10));
        marketIndex.setMarch(BigDecimal.valueOf(10));
        marketIndex.setApril(BigDecimal.valueOf(10));
        marketIndex.setMay(BigDecimal.valueOf(10));
        marketIndex.setJune(BigDecimal.valueOf(10));
        marketIndex.setJuly(BigDecimal.valueOf(10));
        marketIndex.setAugust(BigDecimal.valueOf(10));
        marketIndex.setSeptember(BigDecimal.valueOf(10));
        marketIndex.setOctober(BigDecimal.valueOf(10));
        marketIndex.setNovember(BigDecimal.valueOf(10));
        marketIndex.setDecember(BigDecimal.valueOf(10));

        marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);

        MarketIndexServiceModel actual = marketIndexService.findMarketIndexById(marketIndex.getId());
        MarketIndexServiceModel expected = this.modelMapper.map(marketIndex, MarketIndexServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPictureURL(), actual.getPictureURL());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getFoundation(), actual.getFoundation());
        Assert.assertEquals(expected.getExchange(), actual.getExchange());
        Assert.assertEquals(expected.getOperator(), actual.getOperator());
        Assert.assertEquals(expected.getConstituents(), actual.getConstituents());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getClosingValue(), actual.getClosingValue());
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
    public void marketIndexService_findMarketIndexWithInvalidId_ReturnsCorrect() {
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndex marketIndex = new MarketIndex();
        marketIndex.setName("McDonald`s");
        marketIndex.setPictureURL("Food");
        marketIndex.setDescription("American company");
        marketIndex.setWebsite("Long ago");
        marketIndex.setLogoURL("www.");
        marketIndex.setFoundation("US");
        marketIndex.setExchange("www.");
        marketIndex.setOperator("1000");
        marketIndex.setConstituents("www.");
        marketIndex.setType("100");
        marketIndex.setClosingValue(BigDecimal.valueOf(10));
        marketIndex.setYear("2020");
        marketIndex.setJanuary(BigDecimal.valueOf(10));
        marketIndex.setFebruary(BigDecimal.valueOf(10));
        marketIndex.setMarch(BigDecimal.valueOf(10));
        marketIndex.setApril(BigDecimal.valueOf(10));
        marketIndex.setMay(BigDecimal.valueOf(10));
        marketIndex.setJune(BigDecimal.valueOf(10));
        marketIndex.setJuly(BigDecimal.valueOf(10));
        marketIndex.setAugust(BigDecimal.valueOf(10));
        marketIndex.setSeptember(BigDecimal.valueOf(10));
        marketIndex.setOctober(BigDecimal.valueOf(10));
        marketIndex.setNovember(BigDecimal.valueOf(10));
        marketIndex.setDecember(BigDecimal.valueOf(10));

        marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);

        marketIndexService.findMarketIndexById("InvalidId");
    }

    @Test
    public void marketIndexService_findAllMarketIndexWithValidId_ReturnsCorrect() {
        MarketIndexService marketIndexService = new MarketIndexServiceImpl(this.marketIndexRepository, this.modelMapper);

        MarketIndex marketIndex = new MarketIndex();
        marketIndex.setName("McDonald`s");
        marketIndex.setPictureURL("Food");
        marketIndex.setDescription("American company");
        marketIndex.setWebsite("Long ago");
        marketIndex.setLogoURL("www.");
        marketIndex.setFoundation("US");
        marketIndex.setExchange("www.");
        marketIndex.setOperator("1000");
        marketIndex.setConstituents("www.");
        marketIndex.setType("100");
        marketIndex.setClosingValue(BigDecimal.valueOf(10));
        marketIndex.setYear("2020");
        marketIndex.setJanuary(BigDecimal.valueOf(10));
        marketIndex.setFebruary(BigDecimal.valueOf(10));
        marketIndex.setMarch(BigDecimal.valueOf(10));
        marketIndex.setApril(BigDecimal.valueOf(10));
        marketIndex.setMay(BigDecimal.valueOf(10));
        marketIndex.setJune(BigDecimal.valueOf(10));
        marketIndex.setJuly(BigDecimal.valueOf(10));
        marketIndex.setAugust(BigDecimal.valueOf(10));
        marketIndex.setSeptember(BigDecimal.valueOf(10));
        marketIndex.setOctober(BigDecimal.valueOf(10));
        marketIndex.setNovember(BigDecimal.valueOf(10));
        marketIndex.setDecember(BigDecimal.valueOf(10));

        marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);

        MarketIndex marketIndex2 = new MarketIndex();
        marketIndex2.setName("McDonald`s");
        marketIndex2.setPictureURL("Food");
        marketIndex2.setDescription("American company");
        marketIndex2.setWebsite("Long ago");
        marketIndex2.setLogoURL("www.");
        marketIndex2.setFoundation("US");
        marketIndex2.setExchange("www.");
        marketIndex2.setOperator("1000");
        marketIndex2.setConstituents("www.");
        marketIndex2.setType("100");
        marketIndex2.setClosingValue(BigDecimal.valueOf(10));
        marketIndex2.setYear("2020");
        marketIndex2.setJanuary(BigDecimal.valueOf(10));
        marketIndex2.setFebruary(BigDecimal.valueOf(10));
        marketIndex2.setMarch(BigDecimal.valueOf(10));
        marketIndex2.setApril(BigDecimal.valueOf(10));
        marketIndex2.setMay(BigDecimal.valueOf(10));
        marketIndex2.setJune(BigDecimal.valueOf(10));
        marketIndex2.setJuly(BigDecimal.valueOf(10));
        marketIndex2.setAugust(BigDecimal.valueOf(10));
        marketIndex2.setSeptember(BigDecimal.valueOf(10));
        marketIndex2.setOctober(BigDecimal.valueOf(10));
        marketIndex2.setNovember(BigDecimal.valueOf(10));
        marketIndex2.setDecember(BigDecimal.valueOf(10));

        marketIndex2 = this.marketIndexRepository.saveAndFlush(marketIndex2);

        List<MarketIndexServiceModel> actual = marketIndexService.findAllMarketIndex();

        Assert.assertEquals(2, actual.size());
    }
}
