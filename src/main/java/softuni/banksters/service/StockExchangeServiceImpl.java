package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.StockExchange;
import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;
import softuni.banksters.error.StockExchangeNotFoundException;
import softuni.banksters.repository.StockExchangeRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{

    private final StockExchangeRepository stockExchangeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, ModelMapper modelMapper) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StockExchangeServiceModel createStockExchange(StockExchangeServiceModel stockExchangeServiceModel) {
        StockExchange stockExchange = this.modelMapper.map(stockExchangeServiceModel, StockExchange.class);

        try {
            stockExchange = this.stockExchangeRepository.saveAndFlush(stockExchange);
            return this.modelMapper.map(stockExchange, StockExchangeServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StockExchangeServiceModel findStockExchangeById(String id) {
        StockExchange stockExchange = this.stockExchangeRepository.findById(id)
                .orElseThrow(() -> new StockExchangeNotFoundException(Constants.STOCK_EXCHANGE_NOT_FOUND));

        return this.modelMapper.map(stockExchange, StockExchangeServiceModel.class);
    }

    @Override
    public List<StockExchangeServiceModel> findAllStockExchange() {
        return this.stockExchangeRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, StockExchangeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StockExchangeServiceModel deleteStockExchange(String id) {
        StockExchange stockExchange = this.stockExchangeRepository.findById(id)
                .orElseThrow(() -> new StockExchangeNotFoundException(Constants.STOCK_EXCHANGE_NOT_FOUND));

            this.stockExchangeRepository.delete(stockExchange);

            return this.modelMapper.map(stockExchange, StockExchangeServiceModel.class);
    }

    @Override
    public StockExchangeServiceModel editStockExchange(String id, StockExchangeServiceModel stockExchangeServiceModel) {

        StockExchange stockExchange = this.stockExchangeRepository.findById(id)
                .orElseThrow(() -> new StockExchangeNotFoundException(Constants.STOCK_EXCHANGE_NOT_FOUND));

        stockExchange.setName(stockExchangeServiceModel.getName());
        stockExchange.setRegion(stockExchangeServiceModel.getRegion());
        stockExchange.setDescription(stockExchangeServiceModel.getDescription());
        stockExchange.setMarketPlace(stockExchangeServiceModel.getMarketPlace());
        stockExchange.setMarketCap(stockExchangeServiceModel.getMarketCap());
        stockExchange.setCurrency(stockExchangeServiceModel.getCurrency());
        stockExchange.setWebsite(stockExchangeServiceModel.getWebsite());
        stockExchange.setTimeZone(stockExchangeServiceModel.getTimeZone());

        return this.modelMapper.map(this.stockExchangeRepository.saveAndFlush(stockExchange), StockExchangeServiceModel.class);
    }
}
