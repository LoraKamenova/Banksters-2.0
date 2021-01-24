package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.StockServiceModel;

import java.util.List;

public interface StockService {
    StockServiceModel createStock(StockServiceModel stockServiceModel);

    StockServiceModel findStockById(String id);

    List<StockServiceModel> findAllStock();

    StockServiceModel deleteStock(String id);

    StockServiceModel editStock(String id, StockServiceModel stockServiceModel);

}
