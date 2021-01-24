package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Fact;
import softuni.banksters.domain.models.serivice.FactServiceModel;
import softuni.banksters.repository.FactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FactServiceImpl implements FactService {
    private final FactRepository factRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FactServiceImpl(FactRepository factRepository, ModelMapper modelMapper) {
        this.factRepository = factRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FactServiceModel> findAllFacts() {
        return this.factRepository.findAll().stream()
                .map(o -> this.modelMapper.map(o, FactServiceModel.class))
                .collect(Collectors.toList());
    }

    //300000 = 5 minutes
    @Scheduled(fixedRate = 300000)
    private void generateFact() {
        this.factRepository.deleteAll();

        Random rnd = new Random();
        int x = rnd.nextInt(Constants.DAILY_TIPS.size());

        List<Fact> facts = new ArrayList<>();
        Fact fact = new Fact();
        fact.setFactText(Constants.DAILY_TIPS.get(x));
        facts.add(fact);

        this.factRepository.saveAll(facts);
    }
}
