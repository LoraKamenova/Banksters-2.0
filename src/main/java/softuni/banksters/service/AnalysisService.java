package softuni.banksters.service;


import softuni.banksters.domain.models.serivice.AnalysisServiceModel;

import java.util.List;

public interface AnalysisService {

    AnalysisServiceModel createAnalysis(AnalysisServiceModel analysisServiceModel);

    AnalysisServiceModel findAnalysisById(String id);

    List<AnalysisServiceModel> findAllAnalysis();

    AnalysisServiceModel deleteAnalysis(String id);

    AnalysisServiceModel editAnalysis(String id, AnalysisServiceModel analysisServiceModel);
}
