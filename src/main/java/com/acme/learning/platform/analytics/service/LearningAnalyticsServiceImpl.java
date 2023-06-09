package com.acme.learning.platform.analytics.service;

import com.acme.learning.platform.analytics.domain.service.LearningAnalyticsService;
import com.acme.learning.platform.learning.api.internal.LearningContextFacade;
import org.springframework.stereotype.Service;

@Service
public class LearningAnalyticsServiceImpl implements LearningAnalyticsService {

    private final LearningContextFacade learningContextFacade;

    public LearningAnalyticsServiceImpl(LearningContextFacade learningContextFacade) {
        this.learningContextFacade = learningContextFacade;
    }

    @Override
    public int getTotalStudent() {
        return learningContextFacade.getAllStudents().size();
    }
}
