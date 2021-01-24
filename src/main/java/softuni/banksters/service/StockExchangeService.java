package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;

import java.util.List;

public interface StockExchangeService {
    StockExchangeServiceModel createStockExchange(StockExchangeServiceModel stockExchangeServiceModel);

    StockExchangeServiceModel findStockExchangeById(String id);

    List<StockExchangeServiceModel> findAllStockExchange();

    StockExchangeServiceModel deleteStockExchange(String id);

    StockExchangeServiceModel editStockExchange(String id, StockExchangeServiceModel stockExchangeServiceModel);
}
