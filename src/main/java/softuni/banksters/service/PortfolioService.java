package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.PortfolioServiceModel;

public interface PortfolioService {

    void createPortfolio(PortfolioServiceModel portfolioServiceModel, String ticker, String company, Integer quantity, String user);

    PortfolioServiceModel editPortfolio(String ticker, Integer quantity, String type);
}
