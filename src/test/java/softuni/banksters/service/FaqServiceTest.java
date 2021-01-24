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
import softuni.banksters.domain.entities.Faq;
import softuni.banksters.domain.models.serivice.FaqServiceModel;
import softuni.banksters.repository.FaqRepository;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FaqServiceTest {

    @Autowired
    private FaqRepository faqRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void faqService_createFaq_ReturnsCorrect(){
        FaqService faqService = new FaqServiceImpl(this.faqRepository, this.modelMapper);

        FaqServiceModel toBeSaved = new FaqServiceModel();
        toBeSaved.setQuestion("Q");
        toBeSaved.setAnswer("A");


        FaqServiceModel actual = faqService.createFaq(toBeSaved);
        FaqServiceModel expected = this.modelMapper.map(this.faqRepository.findAll().get(0), FaqServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getQuestion(), actual.getQuestion());
        Assert.assertEquals(expected.getAnswer(), actual.getAnswer());

    }

    @Test
    public void analysisService_findAllAnalysisWithValidId_ReturnsCorrect() {
        FaqService faqService = new FaqServiceImpl(this.faqRepository, this.modelMapper);;

        Faq faq = new Faq();
        faq.setQuestion("Q");
        faq.setAnswer("A");

        faq = this.faqRepository.saveAndFlush(faq);

        Faq faq2 = new Faq();
        faq2.setQuestion("Q");
        faq2.setAnswer("A");

        faq2 = this.faqRepository.saveAndFlush(faq2);

        List<FaqServiceModel> actual = faqService.findAllFAQs();

        Assert.assertEquals(2, actual.size());
    }
}
