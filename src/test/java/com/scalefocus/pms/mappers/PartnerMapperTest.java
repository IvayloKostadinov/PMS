package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Partner;
import com.scalefocus.pms.models.binding.PartnerBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartnerMapperTest {

    private PartnerBindingModel bindingModel;
    private PartnerMapper partnerMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new PartnerBindingModel();
        partnerMapper = PartnerMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void partnerToModelTest() {
        Partner partner = new Partner();
        partner.setName(TestConstants.TEST_STRING);

        bindingModel = partnerMapper.partnerToModel(partner, context);

        assertEquals(bindingModel.getName(), TestConstants.TEST_STRING);
    }

    @Test
    public void modelToPartner() {
        bindingModel.setName(TestConstants.TEST_STRING);

        Partner partner = partnerMapper.modelToPartner(bindingModel, context);

        assertEquals(partner.getName(), TestConstants.TEST_STRING);
    }
}