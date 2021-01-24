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
import softuni.banksters.domain.entities.Contract;
import softuni.banksters.domain.models.serivice.ContractServiceModel;
import softuni.banksters.repository.ContractRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContractServiceTests {


    @Autowired
    private ContractRepository contractRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

//    @Test
//    public void contactService_createContract_ReturnsCorrect(){
//        ContractService contractService = new ContractServiceImpl(this.contractRepository, userService, this.modelMapper);
//
//        UserServiceModel userModel = new UserServiceModel();
//        userModel.setUsername("pesho");
//
//        ContractServiceModel toBeSaved = new ContractServiceModel();
//        toBeSaved.setFullName("Visa Inc.");
//        toBeSaved.setPIN("V");
//        toBeSaved.setIdCard("American company");
//        toBeSaved.setAddress("Many Strengths");
//        toBeSaved.setProfession("Few Weaknesses");
//        toBeSaved.setNetIncome(BigDecimal.valueOf(10));
//        toBeSaved.setInitialAccountBalance(BigDecimal.valueOf(10));
//        toBeSaved.setMobile("Few Threats");
//        toBeSaved.setSigning("Few Threats");
//        toBeSaved.setFinishedOn(LocalDateTime.now());
//
//
//        contractService.createContract(toBeSaved, "name");
////        ContractServiceModel actual = contractService.createContract(, "name");
//        ContractServiceModel expected = this.modelMapper.map(this.contractRepository.findAll().get(0), ContractServiceModel.class);
//
//        Assert.assertEquals(expected.getId(), toBeSaved.getId());
//        Assert.assertEquals(expected.getFullName(), toBeSaved.getFullName());
//        Assert.assertEquals(expected.getPIN(), toBeSaved.getPIN());
//        Assert.assertEquals(expected.getIdCard(), toBeSaved.getIdCard());
//        Assert.assertEquals(expected.getAddress(), toBeSaved.getAddress());
//        Assert.assertEquals(expected.getProfession(), toBeSaved.getProfession());
//        Assert.assertEquals(expected.getNetIncome(), toBeSaved.getNetIncome());
//        Assert.assertEquals(expected.getInitialAccountBalance(), toBeSaved.getInitialAccountBalance());
//        Assert.assertEquals(expected.getMobile(), toBeSaved.getMobile());
//        Assert.assertEquals(expected.getSigning(), toBeSaved.getSigning());
//        Assert.assertEquals(expected.getFinishedOn(), toBeSaved.getFinishedOn());
//    }



    @Test
    public void contactService_findContractWithValidId_ReturnsCorrect() {
        ContractService contractService = new ContractServiceImpl(this.contractRepository, userService, this.modelMapper);

        Contract contract = new Contract();
        contract.setFullName("Visa Inc.");
        contract.setPIN("V");
        contract.setIdCard("American company");
        contract.setAddress("Many Strengths");
        contract.setProfession("Few Weaknesses");
        contract.setNetIncome(BigDecimal.valueOf(10));
        contract.setInitialAccountBalance(BigDecimal.valueOf(10));
        contract.setMobile("Few Threats");
        contract.setSigning("Few Threats");
        contract.setFinishedOn(LocalDateTime.now());

        contract = this.contractRepository.saveAndFlush(contract);

        ContractServiceModel actual = contractService.findContractById(contract.getId());
        ContractServiceModel expected = this.modelMapper.map(contract, ContractServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFullName(), actual.getFullName());
        Assert.assertEquals(expected.getPIN(), actual.getPIN());
        Assert.assertEquals(expected.getIdCard(), actual.getIdCard());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getNetIncome(), actual.getNetIncome());
        Assert.assertEquals(expected.getInitialAccountBalance(), actual.getInitialAccountBalance());
        Assert.assertEquals(expected.getMobile(), actual.getMobile());
        Assert.assertEquals(expected.getSigning(), actual.getSigning());
        Assert.assertEquals(expected.getSigning(), actual.getSigning());

    }

    @Test(expected = Exception.class)
    public void contactService_findContractWithInvalidId_ReturnsCorrect() {
        ContractService contractService = new ContractServiceImpl(this.contractRepository, userService, this.modelMapper);

        Contract contract = new Contract();
        contract.setFullName("Visa Inc.");
        contract.setPIN("V");
        contract.setIdCard("American company");
        contract.setAddress("Many Strengths");
        contract.setProfession("Few Weaknesses");
        contract.setNetIncome(BigDecimal.valueOf(10));
        contract.setInitialAccountBalance(BigDecimal.valueOf(10));
        contract.setMobile("Few Threats");
        contract.setSigning("Few Threats");
        contract.setFinishedOn(LocalDateTime.now());

        contract = this.contractRepository.saveAndFlush(contract);

        contractService.findContractById("InvalidId");
    }

    @Test
    public void contactService_findAllContractWithValidId_ReturnsCorrect() {
        ContractService contractService = new ContractServiceImpl(this.contractRepository, userService, this.modelMapper);

        Contract contract = new Contract();
        contract.setFullName("Visa Inc.");
        contract.setPIN("V");
        contract.setIdCard("American company");
        contract.setAddress("Many Strengths");
        contract.setProfession("Few Weaknesses");
        contract.setNetIncome(BigDecimal.valueOf(10));
        contract.setInitialAccountBalance(BigDecimal.valueOf(10));
        contract.setMobile("Few Threats");
        contract.setSigning("Few Threats");
        contract.setFinishedOn(LocalDateTime.now());

        contract = this.contractRepository.saveAndFlush(contract);

        Contract contract2 = new Contract();
        contract2.setFullName("Visa Inc.");
        contract2.setPIN("V");
        contract2.setIdCard("American company");
        contract2.setAddress("Many Strengths");
        contract2.setProfession("Few Weaknesses");
        contract2.setNetIncome(BigDecimal.valueOf(10));
        contract2.setInitialAccountBalance(BigDecimal.valueOf(10));
        contract2.setMobile("Few Threats");
        contract2.setSigning("Few Threats");
        contract2.setFinishedOn(LocalDateTime.now());

        contract2 = this.contractRepository.saveAndFlush(contract2);

        List<ContractServiceModel> actual = contractService.findAllContracts();
        ContractServiceModel expected = this.modelMapper.map(contract, ContractServiceModel.class);

        Assert.assertEquals(2, actual.size());
    }
}
