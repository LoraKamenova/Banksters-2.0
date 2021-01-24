package softuni.banksters.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.banksters.common.Constants;
import softuni.banksters.domain.entities.Analysis;
import softuni.banksters.domain.models.serivice.AnalysisServiceModel;
import softuni.banksters.error.AnalysisNotFoundException;
import softuni.banksters.repository.AnalysisRepository;


import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService{

    private final AnalysisRepository analysisRepository;
    private final ModelMapper modelMapper;


    public AnalysisServiceImpl(AnalysisRepository analysisRepository, ModelMapper modelMapper) {
        this.analysisRepository = analysisRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnalysisServiceModel createAnalysis(AnalysisServiceModel analysisServiceModel) {
        Analysis analysis = this.modelMapper.map(analysisServiceModel, Analysis.class);

        try {
            analysis = this.analysisRepository.saveAndFlush(analysis);
            return this.modelMapper.map(analysis, AnalysisServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AnalysisServiceModel findAnalysisById(String id) {
        Analysis analysis = this.analysisRepository.findById(id)
                .orElseThrow(() -> new AnalysisNotFoundException(Constants.ANALYSIS_NOT_FOUND));

        return this.modelMapper.map(analysis, AnalysisServiceModel.class);
    }

    @Override
    public List<AnalysisServiceModel> findAllAnalysis() {
        return this.analysisRepository.findAll()
                .stream()
                .map(d -> this.modelMapper.map(d, AnalysisServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AnalysisServiceModel deleteAnalysis(String id) {
        Analysis analysis = this.analysisRepository.findById(id)
                .orElseThrow(() -> new AnalysisNotFoundException(Constants.ANALYSIS_NOT_FOUND));

        this.analysisRepository.delete(analysis);

        return this.modelMapper.map(analysis, AnalysisServiceModel.class);
    }

    @Override
    public AnalysisServiceModel editAnalysis(String id, AnalysisServiceModel analysisServiceModel) {
        Analysis analysis = this.analysisRepository.findById(id)
                .orElseThrow(() -> new AnalysisNotFoundException(Constants.ANALYSIS_NOT_FOUND));

        analysis.setCompany(analysisServiceModel.getCompany());
        analysis.setTicker(analysisServiceModel.getTicker());
        analysis.setOpportunities(analysisServiceModel.getOpportunities());
        analysis.setProfile(analysisServiceModel.getProfile());
        analysis.setStrengths(analysisServiceModel.getStrengths());
        analysis.setWeaknesses(analysisServiceModel.getWeaknesses());
        analysis.setThreats(analysisServiceModel.getThreats());

        return this.modelMapper.map(this.analysisRepository.saveAndFlush(analysis), AnalysisServiceModel.class);
    }
}
