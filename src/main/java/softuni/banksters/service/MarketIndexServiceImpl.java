package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.MarketIndex;
import softuni.banksters.domain.models.serivice.MarketIndexServiceModel;
import softuni.banksters.error.MarketIndexNotFoundException;
import softuni.banksters.error.StockExchangeNotFoundException;
import softuni.banksters.repository.MarketIndexRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketIndexServiceImpl implements MarketIndexService{

    private final MarketIndexRepository marketIndexRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MarketIndexServiceImpl(MarketIndexRepository marketIndexRepository, ModelMapper modelMapper) {
        this.marketIndexRepository = marketIndexRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MarketIndexServiceModel createMarketIndex(MarketIndexServiceModel marketIndexServiceModel) {
        MarketIndex marketIndex = this.modelMapper.map(marketIndexServiceModel, MarketIndex.class);

        try {
            marketIndex = this.marketIndexRepository.saveAndFlush(marketIndex);
            return this.modelMapper.map(marketIndex, MarketIndexServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MarketIndexServiceModel findMarketIndexById(String id) {
        MarketIndex marketIndex = this.marketIndexRepository.findById(id).orElseThrow(() -> new MarketIndexNotFoundException(Constants.MARKET_INDEX_NOT_FOUND));

        return this.modelMapper.map(marketIndex, MarketIndexServiceModel.class);
    }

    @Override
    public List<MarketIndexServiceModel> findAllMarketIndex() {
        return this.marketIndexRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, MarketIndexServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public MarketIndexServiceModel deleteMarketIndex(String id) {
        MarketIndex marketIndex = this.marketIndexRepository.findById(id)
                .orElseThrow(() -> new MarketIndexNotFoundException(Constants.MARKET_INDEX_NOT_FOUND));

        this.marketIndexRepository.delete(marketIndex);

        return this.modelMapper.map(marketIndex, MarketIndexServiceModel.class);
    }

    @Override
    public MarketIndexServiceModel editMarketIndex(String id, MarketIndexServiceModel marketIndexServiceModel) {

        MarketIndex marketIndex = this.marketIndexRepository.findById(id)
                .orElseThrow(() -> new MarketIndexNotFoundException(Constants.MARKET_INDEX_NOT_FOUND));

        marketIndex.setName(marketIndexServiceModel.getName());
        marketIndex.setDescription(marketIndexServiceModel.getDescription());
        marketIndex.setWebsite(marketIndexServiceModel.getWebsite());
        marketIndex.setFoundation(marketIndexServiceModel.getFoundation());
        marketIndex.setOperator(marketIndexServiceModel.getOperator());
        marketIndex.setExchange(marketIndexServiceModel.getExchange());
        marketIndex.setConstituents(marketIndexServiceModel.getConstituents());
        marketIndex.setType(marketIndexServiceModel.getType());
        marketIndex.setClosingValue(marketIndexServiceModel.getClosingValue());
        marketIndex.setYear(marketIndexServiceModel.getYear());
        marketIndex.setJanuary(marketIndexServiceModel.getJanuary());
        marketIndex.setFebruary(marketIndexServiceModel.getFebruary());
        marketIndex.setMarch(marketIndexServiceModel.getMarch());
        marketIndex.setApril(marketIndexServiceModel.getApril());
        marketIndex.setMay(marketIndexServiceModel.getMay());
        marketIndex.setJune(marketIndexServiceModel.getJune());
        marketIndex.setJuly(marketIndexServiceModel.getJuly());
        marketIndex.setAugust(marketIndexServiceModel.getAugust());
        marketIndex.setSeptember(marketIndexServiceModel.getSeptember());
        marketIndex.setOctober(marketIndexServiceModel.getOctober());
        marketIndex.setNovember(marketIndexServiceModel.getNovember());
        marketIndex.setDecember(marketIndexServiceModel.getDecember());

        return this.modelMapper.map(this.marketIndexRepository.saveAndFlush(marketIndex), MarketIndexServiceModel.class);
    }
}
