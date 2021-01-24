package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.OrderServiceModel;
import softuni.banksters.domain.models.serivice.QuestionServiceModel;
import softuni.banksters.domain.models.serivice.StockExchangeServiceModel;

import java.util.List;

public interface QuestionService {

    void createQuestion(QuestionServiceModel questionServiceModel, String name);

    List<QuestionServiceModel> findAllQuestions();

    QuestionServiceModel findQuestionById(String id);

    QuestionServiceModel editQuestion(String id, QuestionServiceModel questionServiceModel);

}
