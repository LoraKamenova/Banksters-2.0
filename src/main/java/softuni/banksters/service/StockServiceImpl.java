package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Stock;
import softuni.banksters.domain.entities.StockExchange;
import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;
import softuni.banksters.domain.models.serivice.StockServiceModel;
import softuni.banksters.error.StockExchangeNotFoundException;
import softuni.banksters.error.StockNotFoundException;
import softuni.banksters.repository.StockRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StockServiceModel createStock(StockServiceModel stockServiceModel) {
        Stock stock = this.modelMapper.map(stockServiceModel, Stock.class);

        try {
            stock = this.stockRepository.saveAndFlush(stock);
            return this.modelMapper.map(stock, StockServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StockServiceModel findStockById(String id) {
        Stock stock = this.stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(Constants.STOCK_NOT_FOUND));

        return this.modelMapper.map(stock, StockServiceModel.class);
    }

    @Override
    public List<StockServiceModel> findAllStock() {
        return this.stockRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, StockServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public StockServiceModel deleteStock(String id) {
        Stock stock = this.stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(Constants.STOCK_NOT_FOUND));

        this.stockRepository.delete(stock);

        return this.modelMapper.map(stock, StockServiceModel.class);
    }

    @Override
    public StockServiceModel editStock(String id, StockServiceModel stockServiceModel) {
        Stock stock = this.stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(Constants.STOCK_NOT_FOUND));

        stock.setCompanyName(stockServiceModel.getCompanyName());
        stock.setTickerCode(stockServiceModel.getTickerCode());
        stock.setDescription(stockServiceModel.getDescription());
        stock.setCurrency(stockServiceModel.getCurrency());
        stock.setMinTradedQty(stockServiceModel.getMinTradedQty());
        stock.setExchange(stockServiceModel.getExchange());
        stock.setIncludedInIndex(stockServiceModel.getIncludedInIndex());
        stock.setExDividendDate(stockServiceModel.getExDividendDate());
        stock.setForwardDividendAndYield(stockServiceModel.getForwardDividendAndYield());
        stock.setPreviousDayClosingPrice(stockServiceModel.getPreviousDayClosingPrice());
        stock.setYear(stockServiceModel.getYear());
        stock.setJanuary(stockServiceModel.getJanuary());
        stock.setFebruary(stockServiceModel.getFebruary());
        stock.setMarch(stockServiceModel.getMarch());
        stock.setApril(stockServiceModel.getApril());
        stock.setMay(stockServiceModel.getMay());
        stock.setJune(stockServiceModel.getJune());
        stock.setJuly(stockServiceModel.getJuly());
        stock.setAugust(stockServiceModel.getAugust());
        stock.setSeptember(stockServiceModel.getSeptember());
        stock.setOctober(stockServiceModel.getOctober());
        stock.setNovember(stockServiceModel.getNovember());
        stock.setDecember(stockServiceModel.getDecember());

        return this.modelMapper.map(this.stockRepository.saveAndFlush(stock), StockServiceModel.class);
    }
}
