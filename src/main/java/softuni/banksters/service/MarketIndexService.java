package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.MarketIndexServiceModel;

import java.util.List;

public interface MarketIndexService {

    MarketIndexServiceModel createMarketIndex(MarketIndexServiceModel marketIndexServiceModel);

    MarketIndexServiceModel findMarketIndexById(String id);

    List<MarketIndexServiceModel> findAllMarketIndex();

    MarketIndexServiceModel deleteMarketIndex(String id);

    MarketIndexServiceModel editMarketIndex(String id, MarketIndexServiceModel marketIndexServiceModel);
}
