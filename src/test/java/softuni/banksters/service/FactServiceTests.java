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
import softuni.banksters.domain.entities.Fact;
import softuni.banksters.domain.models.serivice.FactServiceModel;
import softuni.banksters.repository.FactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FactServiceTests {

    @Autowired
    private FactRepository factRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void factService_findAllFactWithValidId_ReturnsCorrect() {
        FactService factService = new FactServiceImpl(this.factRepository, this.modelMapper);

        Fact fact = new Fact();
        fact.setFactText("This is fact text");

        fact = this.factRepository.saveAndFlush(fact);

        Fact fact2 = new Fact();
        fact2.setFactText("This is fact text");

        fact2 = this.factRepository.saveAndFlush(fact2);

        List<FactServiceModel> actual = factService.findAllFacts();

        Assert.assertEquals(2, actual.size());
    }
}
