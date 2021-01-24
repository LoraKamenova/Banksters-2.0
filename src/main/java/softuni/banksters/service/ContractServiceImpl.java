package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Contract;
import softuni.banksters.domain.models.serivice.ContractServiceModel;
import softuni.banksters.domain.models.serivice.UserServiceModel;
import softuni.banksters.error.ContractNotFoundException;
import softuni.banksters.repository.ContractRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService{

    private final ContractRepository contractRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, UserService userService, ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createContract(ContractServiceModel contractServiceModel, String name) {
        UserServiceModel userModel = userService.findUserByUsername(name);
        contractServiceModel.setUser(userModel);

        contractServiceModel.setFinishedOn(LocalDateTime.now());

        this.contractRepository.saveAndFlush(this.modelMapper.map(contractServiceModel, Contract.class));
    }

    @Override
    public List<ContractServiceModel> findAllContracts() {
        List<Contract> contracts = this.contractRepository.findAll();
        List<ContractServiceModel> contractServiceModels = contracts
                .stream()
                .map(o -> this.modelMapper.map(o, ContractServiceModel.class))
                .collect(Collectors.toList());

        return contractServiceModels;
    }


    @Override
    public ContractServiceModel findContractById(String id) {
        return this.contractRepository.findById(id)
                .map(o -> this.modelMapper.map(o, ContractServiceModel.class))
                .orElseThrow(() -> new ContractNotFoundException(Constants.CONTRACT_NOT_FOUND));
    }
}
