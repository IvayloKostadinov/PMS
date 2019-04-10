package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.FieldConstants;
import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.repositories.PhaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class PhaseServiceImplTest {

    private PhaseService service;

    @Mock
    private PhaseRepository phaseRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new PhaseServiceImpl(phaseRepository);

    }

    @Test
    public void createPhaseTest() {

        PhaseBindingModel model = new PhaseBindingModel();
        model.setPhaseEndDate(FieldConstants.BINDING_MODEL_DATE);
        model.setPhaseBudget(FieldConstants.BINDING_MODEL_DOUBLE);
        model.setPhaseName(FieldConstants.BINDING_MODEL_STRING);
        model.setPhaseStartDate(FieldConstants.BINDING_MODEL_DATE);

        Phase phase = service.createPhase(model);

        assertEquals(phase.getPhaseName(), FieldConstants.BINDING_MODEL_STRING);
    }
}