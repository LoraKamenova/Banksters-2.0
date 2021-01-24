package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.domain.entities.Portfolio;
import softuni.banksters.domain.models.serivice.PortfolioServiceModel;
import softuni.banksters.repository.PortfolioRepository;


@Service
public class PortfolioServiceImpl implements PortfolioService{
    private final PortfolioRepository portfolioRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, ModelMapper modelMapper) {
        this.portfolioRepository = portfolioRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPortfolio(PortfolioServiceModel portfolioServiceModel, String ticker, String company, Integer quantity, String user) {
        portfolioServiceModel.setTicker(ticker);
        portfolioServiceModel.setCompany(company);
        portfolioServiceModel.setQuantity(quantity);
        portfolioServiceModel.setUser(user);
        this.portfolioRepository.saveAndFlush(this.modelMapper.map(portfolioServiceModel, Portfolio.class));
    }

    @Override
    public PortfolioServiceModel editPortfolio(String ticker, Integer quantity, String type) {
        Portfolio portfolio = this.portfolioRepository.findPortfolioByTicker(ticker).get(0);
        if(type.equals("Buy")) {
            portfolio.setQuantity(portfolio.getQuantity() + quantity);
        } else if (type.equals("Sell")) {
            portfolio.setQuantity(portfolio.getQuantity() - quantity);
        }

        return this.modelMapper.map(this.portfolioRepository.saveAndFlush(portfolio), PortfolioServiceModel.class);
    }
}
