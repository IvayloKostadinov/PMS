package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhaseMapperTest {

    private PhaseBindingModel bindingModel;
    private PhaseMapper phaseMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new PhaseBindingModel();
        phaseMapper = PhaseMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void phaseToModelTest() {
        Phase phase = new Phase();
        phase.setPhaseName(TestConstants.TEST_STRING);

        bindingModel = phaseMapper.phaseToPhaseBindingModel(phase, context);

        assertEquals(bindingModel.getPhaseName(), TestConstants.TEST_STRING);
    }

    @Test
    public void modelToPhaseTest() {
        bindingModel.setPhaseBudget(TestConstants.TEST_DOUBLE);

        Phase phase = phaseMapper.modelToPhase(bindingModel, context);

        assertEquals(phase.getPhaseBudget(), TestConstants.TEST_DOUBLE);
    }
}