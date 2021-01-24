package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.ContractServiceModel;

import java.util.List;

public interface ContractService {

    void createContract(ContractServiceModel contractServiceModel, String name);

    List<ContractServiceModel> findAllContracts();

    ContractServiceModel findContractById(String id);
}
