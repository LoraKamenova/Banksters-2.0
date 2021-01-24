package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Company;
import softuni.banksters.domain.models.serivice.CompanyServiceModel;
import softuni.banksters.error.CompanyNotFoundException;
import softuni.banksters.error.StockExchangeNotFoundException;
import softuni.banksters.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyServiceModel createCompany(CompanyServiceModel companyServiceModel) {
        Company company = this.modelMapper.map(companyServiceModel, Company.class);

        try {
            company = this.companyRepository.saveAndFlush(company);
            return this.modelMapper.map(company, CompanyServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CompanyServiceModel findCompanyById(String id) {
        Company company = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(Constants.COMPANY_NOT_FOUND));

        return this.modelMapper.map(company, CompanyServiceModel.class);
    }

    @Override
    public List<CompanyServiceModel> findAllCompany() {
        return this.companyRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, CompanyServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyServiceModel deleteCompany(String id) {
        Company company = this.companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(Constants.COMPANY_NOT_FOUND));

        this.companyRepository.delete(company);

        return this.modelMapper.map(company, CompanyServiceModel.class);
    }

    @Override
    public CompanyServiceModel editCompany(String id, CompanyServiceModel companyServiceModel) {
        Company company = this.companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(Constants.COMPANY_NOT_FOUND));

        company.setName(companyServiceModel.getName());
        company.setIndustry(companyServiceModel.getIndustry());
        company.setDescription(companyServiceModel.getDescription());
        company.setFounded(companyServiceModel.getFounded());
        company.setEmployees(companyServiceModel.getEmployees());
        company.setHeadquarters(companyServiceModel.getHeadquarters());
        company.setWebsite(companyServiceModel.getWebsite());
        company.setRevenues(companyServiceModel.getRevenues());

        return this.modelMapper.map(this.companyRepository.saveAndFlush(company), CompanyServiceModel.class);
    }
}
