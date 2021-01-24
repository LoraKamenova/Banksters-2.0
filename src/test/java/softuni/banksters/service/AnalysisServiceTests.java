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
import softuni.banksters.domain.entities.Analysis;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.repository.AnalysisRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AnalysisServiceTests {

    @Autowired
    private AnalysisRepository analysisRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void analysisService_createAnalysis_ReturnsCorrect(){
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        AnalysisServiceModel toBeSaved = new AnalysisServiceModel();
        toBeSaved.setCompany("Visa Inc.");
        toBeSaved.setTicker("V");
        toBeSaved.setProfile("American company");
        toBeSaved.setStrengths("Many Strengths");
        toBeSaved.setWeaknesses("Few Weaknesses");
        toBeSaved.setOpportunities("Many Opportunities");
        toBeSaved.setThreats("Few Threats");

        AnalysisServiceModel actual = analysisService.createAnalysis(toBeSaved);
        AnalysisServiceModel expected = this.modelMapper.map(this.analysisRepository.findAll().get(0), AnalysisServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCompany(), actual.getCompany());
        Assert.assertEquals(expected.getTicker(), actual.getTicker());
        Assert.assertEquals(expected.getProfile(), actual.getProfile());
        Assert.assertEquals(expected.getStrengths(), actual.getStrengths());
        Assert.assertEquals(expected.getWeaknesses(), actual.getWeaknesses());
        Assert.assertEquals(expected.getOpportunities(), actual.getOpportunities());
        Assert.assertEquals(expected.getThreats(), actual.getThreats());
    }

    @Test
    public void analysisService_editAnalysis_ReturnsCorrect(){
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        Analysis analysis = new Analysis();
        analysis.setCompany("Visa Inc.");
        analysis.setTicker("V");
        analysis.setProfile("American company");
        analysis.setStrengths("Many Strengths");
        analysis.setWeaknesses("Few Weaknesses");
        analysis.setOpportunities("Many Opportunities");
        analysis.setThreats("Few Threats");

        analysis = this.analysisRepository.saveAndFlush(analysis);

        AnalysisServiceModel toBeEdited = new AnalysisServiceModel();
        toBeEdited.setId(analysis.getId());
        toBeEdited.setCompany("Edited");
        toBeEdited.setTicker("Edited");
        toBeEdited.setProfile("Edited");
        toBeEdited.setStrengths("Edited");
        toBeEdited.setWeaknesses("Edited");
        toBeEdited.setOpportunities("Edited");
        toBeEdited.setThreats("Edited");

        AnalysisServiceModel actual = analysisService.editAnalysis(analysis.getId(), toBeEdited);
        AnalysisServiceModel expected = this.modelMapper.map(this.analysisRepository.findAll().get(0), AnalysisServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCompany(), actual.getCompany());
        Assert.assertEquals(expected.getTicker(), actual.getTicker());
        Assert.assertEquals(expected.getProfile(), actual.getProfile());
        Assert.assertEquals(expected.getStrengths(), actual.getStrengths());
        Assert.assertEquals(expected.getWeaknesses(), actual.getWeaknesses());
        Assert.assertEquals(expected.getOpportunities(), actual.getOpportunities());
        Assert.assertEquals(expected.getThreats(), actual.getThreats());
    }

    @Test
    public void analysisService_deleteAnalysisWithValidId_ReturnsCorrect() {
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        Analysis analysis = new Analysis();
        analysis.setCompany("Visa Inc.");
        analysis.setTicker("V");
        analysis.setProfile("American company");
        analysis.setStrengths("Many Strengths");
        analysis.setWeaknesses("Few Weaknesses");
        analysis.setOpportunities("Many Opportunities");
        analysis.setThreats("Few Threats");

        analysis = this.analysisRepository.saveAndFlush(analysis);

        analysisService.deleteAnalysis(analysis.getId());

        long expectedCount = 0;
        long accountCount = this.analysisRepository.count();

        Assert.assertEquals(expectedCount, accountCount);
    }

    @Test(expected = Exception.class)
    public void analysisService_deleteAnalysisWithInvalidId_ReturnsCorrect() {
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        Analysis analysis = new Analysis();
        analysis.setCompany("Visa Inc.");
        analysis.setTicker("V");
        analysis.setProfile("American company");
        analysis.setStrengths("Many Strengths");
        analysis.setWeaknesses("Few Weaknesses");
        analysis.setOpportunities("Many Opportunities");
        analysis.setThreats("Few Threats");

        analysis = this.analysisRepository.saveAndFlush(analysis);

        analysisService.deleteAnalysis("InvalidId");

    }

    @Test
    public void analysisService_findAnalysisWithValidId_ReturnsCorrect() {
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        Analysis analysis = new Analysis();
        analysis.setCompany("Visa Inc.");
        analysis.setTicker("V");
        analysis.setProfile("American company");
        analysis.setStrengths("Many Strengths");
        analysis.setWeaknesses("Few Weaknesses");
        analysis.setOpportunities("Many Opportunities");
        analysis.setThreats("Few Threats");

        analysis = this.analysisRepository.saveAndFlush(analysis);

        AnalysisServiceModel actual = analysisService.findAnalysisById(analysis.getId());
        AnalysisServiceModel expected = this.modelMapper.map(analysis, AnalysisServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getCompany(), actual.getCompany());
        Assert.assertEquals(expected.getTicker(), actual.getTicker());
        Assert.assertEquals(expected.getProfile(), actual.getProfile());
        Assert.assertEquals(expected.getStrengths(), actual.getStrengths());
        Assert.assertEquals(expected.getWeaknesses(), actual.getWeaknesses());
        Assert.assertEquals(expected.getOpportunities(), actual.getOpportunities());
        Assert.assertEquals(expected.getThreats(), actual.getThreats());
    }

    @Test(expected = Exception.class)
    public void analysisService_findAnalysisWithInvalidId_ReturnsCorrect() {
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        Analysis analysis = new Analysis();
        analysis.setCompany("Visa Inc.");
        analysis.setTicker("V");
        analysis.setProfile("American company");
        analysis.setStrengths("Many Strengths");
        analysis.setWeaknesses("Few Weaknesses");
        analysis.setOpportunities("Many Opportunities");
        analysis.setThreats("Few Threats");

        analysis = this.analysisRepository.saveAndFlush(analysis);

        analysisService.findAnalysisById("InvalidId");
    }

    @Test
    public void analysisService_findAllAnalysisWithValidId_ReturnsCorrect() {
        AnalysisService analysisService = new AnalysisServiceImpl(this.analysisRepository, this.modelMapper);

        Analysis analysis = new Analysis();
        analysis.setCompany("Visa Inc.");
        analysis.setTicker("V");
        analysis.setProfile("American company");
        analysis.setStrengths("Many Strengths");
        analysis.setWeaknesses("Few Weaknesses");
        analysis.setOpportunities("Many Opportunities");
        analysis.setThreats("Few Threats");

        analysis = this.analysisRepository.saveAndFlush(analysis);

        Analysis analysis2 = new Analysis();
        analysis2.setCompany("Visa Inc.");
        analysis2.setTicker("V");
        analysis2.setProfile("American company");
        analysis2.setStrengths("Many Strengths");
        analysis2.setWeaknesses("Few Weaknesses");
        analysis2.setOpportunities("Many Opportunities");
        analysis2.setThreats("Few Threats");

        analysis2 = this.analysisRepository.saveAndFlush(analysis2);

        List<AnalysisServiceModel> actual = analysisService.findAllAnalysis();

        Assert.assertEquals(2, actual.size());
    }
}
