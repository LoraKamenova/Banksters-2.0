package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.domain.entities.Faq;
import softuni.banksters.domain.models.serivice.FaqServiceModel;
import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;
import softuni.banksters.repository.FaqRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaqServiceImpl implements FaqService{

    private final FaqRepository faqRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FaqServiceImpl(FaqRepository faqRepository, ModelMapper modelMapper) {
        this.faqRepository = faqRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FaqServiceModel createFaq(FaqServiceModel faqServiceModel) {
        Faq faq = this.modelMapper.map(faqServiceModel, Faq.class);

        try {
            faq = this.faqRepository.saveAndFlush(faq);
            return this.modelMapper.map(faq, FaqServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FaqServiceModel> findAllFAQs() {
        return this.faqRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, FaqServiceModel.class))
                .collect(Collectors.toList());
    }
}
