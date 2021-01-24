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
import softuni.banksters.domain.entities.Company;
import softuni.banksters.domain.models.serivice.CompanyServiceModel;
import softuni.banksters.repository.CompanyRepository;


import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CompanyServiceTests {

    @Autowired
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
    }

    @Test
    public void companyService_createCompany_ReturnsCorrect(){
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        CompanyServiceModel toBeSaved = new CompanyServiceModel();
        toBeSaved.setName("McDonald`s");
        toBeSaved.setIndustry("Food");
        toBeSaved.setDescription("American company");
        toBeSaved.setFounded("Long ago");
        toBeSaved.setLogoURL("www.");
        toBeSaved.setHeadquarters("US");
        toBeSaved.setBrandsURL("www.");
        toBeSaved.setRevenues("1000");
        toBeSaved.setWebsite("www.");
        toBeSaved.setEmployees("100");


        CompanyServiceModel actual = companyService.createCompany(toBeSaved);
        CompanyServiceModel expected = this.modelMapper.map(this.companyRepository.findAll().get(0), CompanyServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getIndustry(), actual.getIndustry());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getFounded(), actual.getFounded());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getHeadquarters(), actual.getHeadquarters());
        Assert.assertEquals(expected.getBrandsURL(), actual.getBrandsURL());
        Assert.assertEquals(expected.getRevenues(), actual.getRevenues());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getEmployees(), actual.getEmployees());
    }

    @Test
    public void companyService_editCompany_ReturnsCorrect(){
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        Company company = new Company();
        company.setName("McDonald`s");
        company.setIndustry("Food");
        company.setDescription("American company");
        company.setFounded("Long ago");
        company.setLogoURL("www.");
        company.setHeadquarters("US");
        company.setBrandsURL("www.");
        company.setRevenues("1000");
        company.setWebsite("www.");
        company.setEmployees("100");

        company = this.companyRepository.saveAndFlush(company);

        CompanyServiceModel toBeEdited = new CompanyServiceModel();
        toBeEdited.setId(company.getId());
        toBeEdited.setName("Edited");
        toBeEdited.setIndustry("Edited");
        toBeEdited.setDescription("Edited");
        toBeEdited.setFounded("Edited");
        toBeEdited.setLogoURL("Edited");
        toBeEdited.setHeadquarters("Edited");
        toBeEdited.setBrandsURL("Edited");
        toBeEdited.setRevenues("Edited");
        toBeEdited.setWebsite("Edited");
        toBeEdited.setEmployees("Edited");

        CompanyServiceModel actual = companyService.editCompany(company.getId(), toBeEdited);
        CompanyServiceModel expected = this.modelMapper.map(this.companyRepository.findAll().get(0), CompanyServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getIndustry(), actual.getIndustry());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getFounded(), actual.getFounded());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getHeadquarters(), actual.getHeadquarters());
        Assert.assertEquals(expected.getBrandsURL(), actual.getBrandsURL());
        Assert.assertEquals(expected.getRevenues(), actual.getRevenues());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getEmployees(), actual.getEmployees());
    }

    @Test
    public void companyService_deleteCompanyWithValidId_ReturnsCorrect() {
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        Company company = new Company();
        company.setName("McDonald`s");
        company.setIndustry("Food");
        company.setDescription("American company");
        company.setFounded("Long ago");
        company.setLogoURL("www.");
        company.setHeadquarters("US");
        company.setBrandsURL("www.");
        company.setRevenues("1000");
        company.setWebsite("www.");
        company.setEmployees("100");

        company = this.companyRepository.saveAndFlush(company);

        companyService.deleteCompany(company.getId());

        long expectedCount = 0;
        long accountCount = this.companyRepository.count();

        Assert.assertEquals(expectedCount, accountCount);
    }

    @Test(expected = Exception.class)
    public void companyService_deleteCompanyWithInvalidId_ReturnsCorrect() {
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        Company company = new Company();
        company.setName("McDonald`s");
        company.setIndustry("Food");
        company.setDescription("American company");
        company.setFounded("Long ago");
        company.setLogoURL("www.");
        company.setHeadquarters("US");
        company.setBrandsURL("www.");
        company.setRevenues("1000");
        company.setWebsite("www.");
        company.setEmployees("100");

        company = this.companyRepository.saveAndFlush(company);

        companyService.deleteCompany("InvalidId");

    }

    @Test
    public void companyService_findCompanyWithValidId_ReturnsCorrect() {
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        Company company = new Company();
        company.setName("McDonald`s");
        company.setIndustry("Food");
        company.setDescription("American company");
        company.setFounded("Long ago");
        company.setLogoURL("www.");
        company.setHeadquarters("US");
        company.setBrandsURL("www.");
        company.setRevenues("1000");
        company.setWebsite("www.");
        company.setEmployees("100");

        company = this.companyRepository.saveAndFlush(company);

        CompanyServiceModel actual = companyService.findCompanyById(company.getId());
        CompanyServiceModel expected = this.modelMapper.map(company, CompanyServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getIndustry(), actual.getIndustry());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getFounded(), actual.getFounded());
        Assert.assertEquals(expected.getLogoURL(), actual.getLogoURL());
        Assert.assertEquals(expected.getHeadquarters(), actual.getHeadquarters());
        Assert.assertEquals(expected.getBrandsURL(), actual.getBrandsURL());
        Assert.assertEquals(expected.getRevenues(), actual.getRevenues());
        Assert.assertEquals(expected.getWebsite(), actual.getWebsite());
        Assert.assertEquals(expected.getEmployees(), actual.getEmployees());
    }

    @Test(expected = Exception.class)
    public void companyService_findCompanyWithInvalidId_ReturnsCorrect() {
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        Company company = new Company();
        company.setName("McDonald`s");
        company.setIndustry("Food");
        company.setDescription("American company");
        company.setFounded("Long ago");
        company.setLogoURL("www.");
        company.setHeadquarters("US");
        company.setBrandsURL("www.");
        company.setRevenues("1000");
        company.setWebsite("www.");
        company.setEmployees("100");

        company = this.companyRepository.saveAndFlush(company);

        companyService.findCompanyById("InvalidId");
    }

    @Test
    public void comapnyService_findAllCompanyWithValidId_ReturnsCorrect() {
        CompanyService companyService = new CompanyServiceImpl(this.companyRepository, this.modelMapper);

        Company company = new Company();
        company.setName("McDonald`s");
        company.setIndustry("Food");
        company.setDescription("American company");
        company.setFounded("Long ago");
        company.setLogoURL("www.");
        company.setHeadquarters("US");
        company.setBrandsURL("www.");
        company.setRevenues("1000");
        company.setWebsite("www.");
        company.setEmployees("100");

        company = this.companyRepository.saveAndFlush(company);

        Company company2 = new Company();
        company2.setName("McDonald`s");
        company2.setIndustry("Food");
        company2.setDescription("American company");
        company2.setFounded("Long ago");
        company2.setLogoURL("www.");
        company2.setHeadquarters("US");
        company2.setBrandsURL("www.");
        company2.setRevenues("1000");
        company2.setWebsite("www.");
        company2.setEmployees("100");

        company2 = this.companyRepository.saveAndFlush(company2);

        List<CompanyServiceModel> actual = companyService.findAllCompany();

        Assert.assertEquals(2, actual.size());
    }
}
