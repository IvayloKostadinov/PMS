// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.PhaseMapper;
import com.scalefocus.pms.mappers.ProjectMapper;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.repositories.PhaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhaseServiceImpl implements PhaseService {

    private final PhaseRepository phaseRepository;
    private final PhaseMapper modelMapper;
    private final ProjectMapper projectMapper;
    private final CycleAvoidingMappingContext context;

    /**
     * This is the constructor autowiring all the dependencies.
     *
     * @param phaseRepository - The repository for the Phase entity.
     */
    @Autowired
    public PhaseServiceImpl(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
        this.modelMapper = PhaseMapper.INSTANCE;
        this.context = new CycleAvoidingMappingContext();
        this.projectMapper = ProjectMapper.INSTANCE;
    }

    /**
     * This method saves and then returns a new Phase object.
     *
     * @param bindingModel - The model binding model for the Phase entity.
     * @return - returns a new Phase object.
     */
    @Override
    public Phase createPhase(PhaseBindingModel bindingModel) {

        Phase phase = modelMapper.modelToPhase(bindingModel, context);

        phaseRepository.saveAndFlush(phase);
        return phase;
    }

    @Override
    public List<PhaseBindingModel> getAvailablePhasesByProjectId(final ProjectBindingModel projectModel) {
        Project project = projectMapper.projectModelToProject(projectModel, context);
        List<Phase> phases = phaseRepository.getPhasesByProject(project);
        List<PhaseBindingModel> phaseBindingModelList = new ArrayList<>();
        phases.forEach(phase -> phaseBindingModelList.add(modelMapper.phaseToPhaseBindingModel(phase, context)));
        return phaseBindingModelList;
    }

}
