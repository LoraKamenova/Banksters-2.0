package softuni.banksters.service;

import softuni.banksters.domain.models.serivice.FactServiceModel;

import java.util.List;

public interface FactService {
    List<FactServiceModel> findAllFacts();
}
