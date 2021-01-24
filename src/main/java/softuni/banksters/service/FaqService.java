package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.FaqServiceModel;

import java.util.List;

public interface FaqService {

    FaqServiceModel createFaq(FaqServiceModel faqServiceModel);
    List<FaqServiceModel> findAllFAQs();
}
