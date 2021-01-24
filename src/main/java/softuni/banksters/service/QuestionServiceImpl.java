package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Question;
import softuni.banksters.domain.models.serivice.QuestionServiceModel;
import softuni.banksters.domain.models.serivice.UserServiceModel;
import softuni.banksters.error.QuestionNotFoundException;
import softuni.banksters.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, UserService userService, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createQuestion(QuestionServiceModel questionServiceModel, String name) {
        UserServiceModel userModel = userService.findUserByUsername(name);
        questionServiceModel.setUser(userModel);

        questionServiceModel.setFinishedOn(LocalDateTime.now());

        this.questionRepository.saveAndFlush(this.modelMapper.map(questionServiceModel, Question.class));
    }

    @Override
    public List<QuestionServiceModel> findAllQuestions() {
        List<Question> orders = this.questionRepository.findAll();
        List<QuestionServiceModel> questionServiceModels = orders
                .stream()
                .map(o -> this.modelMapper.map(o, QuestionServiceModel.class))
                .collect(Collectors.toList());

        return questionServiceModels;
    }


    @Override
    public QuestionServiceModel findQuestionById(String id) {
        return this.questionRepository.findById(id)
                .map(o -> this.modelMapper.map(o, QuestionServiceModel.class))
                .orElseThrow(() -> new QuestionNotFoundException(Constants.QUESTION_NOT_FOUND));
    }

    @Override
    public QuestionServiceModel editQuestion(String id, QuestionServiceModel questionServiceModel) {
        Question question = this.questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(Constants.QUESTION_NOT_FOUND));

        question.setQuestion(questionServiceModel.getQuestion());
        question.setAnswer(questionServiceModel.getAnswer());

        return this.modelMapper.map(this.questionRepository.saveAndFlush(question), QuestionServiceModel.class);
    }
}