package softuni.banksters.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softuni.banksters.domain.entities.Question;
import softuni.banksters.domain.models.serivice.QuestionServiceModel;
import softuni.banksters.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class QuestionServiceTests {

    @Autowired
    private QuestionRepository questionRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void questionService_findQuestionWithValidId_ReturnsCorrect() {
        QuestionService questionService = new QuestionServiceImpl(this.questionRepository, userService, this.modelMapper);

        Question question = new Question();
        question.setQuestion("Visa Inc.");
        question.setAnswer("V");
        question.setFinishedOn(LocalDateTime.now());

        question = this.questionRepository.saveAndFlush(question);

        QuestionServiceModel actual = questionService.findQuestionById(question.getId());
        QuestionServiceModel expected = this.modelMapper.map(question, QuestionServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getAnswer(), actual.getAnswer());
        Assert.assertEquals(expected.getFinishedOn(), actual.getFinishedOn());
        Assert.assertEquals(expected.getQuestion(), actual.getQuestion());

    }

    @Test
    public void questionService_findAllQuestionWithValidId_ReturnsCorrect() {
        QuestionService questionService = new QuestionServiceImpl(this.questionRepository, userService, this.modelMapper);

        Question question = new Question();
        question.setQuestion("Visa Inc.");
        question.setAnswer("V");
        question.setFinishedOn(LocalDateTime.now());

        question = this.questionRepository.saveAndFlush(question);

        Question question2 = new Question();
        question2.setQuestion("Visa Inc.");
        question2.setAnswer("V");
        question2.setFinishedOn(LocalDateTime.now());

        question2 = this.questionRepository.saveAndFlush(question2);

        List<QuestionServiceModel> actual = questionService.findAllQuestions();
        QuestionServiceModel expected = this.modelMapper.map(question, QuestionServiceModel.class);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void questionService_editQuestion_ReturnsCorrect(){
        QuestionService questionService = new QuestionServiceImpl(this.questionRepository, userService, this.modelMapper);

        Question question = new Question();
        question.setQuestion("Visa Inc.");
        question.setAnswer("V");
        question.setFinishedOn(LocalDateTime.now());

        question = this.questionRepository.saveAndFlush(question);

        QuestionServiceModel toBeEdited = new QuestionServiceModel();
        toBeEdited.setId(question.getId());
        toBeEdited.setQuestion("Edited");
        toBeEdited.setAnswer("Edited");
        toBeEdited.setFinishedOn(LocalDateTime.now());

        QuestionServiceModel actual = questionService.editQuestion(question.getId(), toBeEdited);
        QuestionServiceModel expected = this.modelMapper.map(this.questionRepository.findAll().get(0), QuestionServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestion(), actual.getQuestion());
        Assert.assertEquals(expected.getFinishedOn(), actual.getFinishedOn());
        Assert.assertEquals(expected.getAnswer(), actual.getAnswer());

    }
}
