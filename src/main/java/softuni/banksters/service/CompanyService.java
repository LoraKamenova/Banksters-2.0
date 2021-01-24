package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.CompanyServiceModel;

import java.util.List;

public interface CompanyService {
    CompanyServiceModel createCompany(CompanyServiceModel companyServiceModel);

    CompanyServiceModel findCompanyById(String id);

    List<CompanyServiceModel> findAllCompany();

    CompanyServiceModel deleteCompany(String id);

    CompanyServiceModel editCompany(String id, CompanyServiceModel companyServiceModel);
}
