package com.scalefocus.pms.services;

import com.scalefocus.pms.repositories.PartnerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PartnersServiceImplTest {


    private PartnersService partnersService;
    @Mock
    private PartnerRepository partnerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        partnersService = new PartnersServiceImpl(partnerRepository);
    }

    @Test
    public void generateCloudTag() throws IOException {
        assertTrue(partnersService.generateCloudTag());
    }


}