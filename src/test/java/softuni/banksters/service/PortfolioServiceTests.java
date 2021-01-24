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
import softuni.banksters.domain.entities.Portfolio;
import softuni.banksters.domain.models.serivice.PortfolioServiceModel;
import softuni.banksters.repository.PortfolioRepository;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PortfolioServiceTests {

    @Autowired
    private PortfolioRepository portfolioRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void portfolioService_createPortfolio_ReturnsCorrect(){
        PortfolioService portfolioService = new PortfolioServiceImpl(this.portfolioRepository, this.modelMapper);

        PortfolioServiceModel toBeSaved = new PortfolioServiceModel();
        toBeSaved.setCompany("Visa");
        toBeSaved.setTicker("V");
        toBeSaved.setQuantity(4);
        toBeSaved.setUser("Pesho");

        portfolioService.createPortfolio(toBeSaved, "V", "Visa", 4, "Pesho");

        PortfolioServiceModel expected = this.modelMapper.map(this.portfolioRepository.findAll().get(0), PortfolioServiceModel.class);

        expected.setId(toBeSaved.getId());
        Assert.assertEquals(expected.getId(), toBeSaved.getId());
        Assert.assertEquals(expected.getCompany(), toBeSaved.getCompany());
        Assert.assertEquals(expected.getTicker(), toBeSaved.getTicker());
        Assert.assertEquals(expected.getQuantity(), toBeSaved.getQuantity());
        Assert.assertEquals(expected.getUser(), toBeSaved.getUser());

    }

    @Test
    public void portfolioService_editPortfolio_ReturnsCorrect(){
        PortfolioService portfolioService = new PortfolioServiceImpl(this.portfolioRepository, this.modelMapper);

        Portfolio portfolio = new Portfolio();
        portfolio.setCompany("Visa");
        portfolio.setTicker("V");
        portfolio.setQuantity(4);
        portfolio.setUser("Pesho");

        portfolio = this.portfolioRepository.saveAndFlush(portfolio);

        PortfolioServiceModel toBeEdited = new PortfolioServiceModel();
        toBeEdited.setCompany("toBeEdited");


        PortfolioServiceModel actual = portfolioService.editPortfolio("V", 5, "Buy");
        PortfolioServiceModel expected = this.modelMapper.map(this.portfolioRepository.findAll().get(0), PortfolioServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTicker(), actual.getTicker());
        Assert.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assert.assertEquals(expected.getCompany(), actual.getCompany());
        Assert.assertEquals(expected.getUser(), actual.getUser());

    }
}
