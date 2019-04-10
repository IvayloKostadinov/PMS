// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import static com.scalefocus.pms.constants.CloudTagConstants.HEIGHT;
import static com.scalefocus.pms.constants.CloudTagConstants.MAX_FONT;
import static com.scalefocus.pms.constants.CloudTagConstants.MIN_FONT;
import static com.scalefocus.pms.constants.CloudTagConstants.MIN_WORD_LENGTH;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB1;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB2;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB3;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB4;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB5;
import static com.scalefocus.pms.constants.CloudTagConstants.RGB6;
import static com.scalefocus.pms.constants.CloudTagConstants.STATIC_IMG_SCALEFOCUS_LOGO_PNG;
import static com.scalefocus.pms.constants.CloudTagConstants.WIDTH;

import com.scalefocus.pms.constants.CloudTagConstants;
import com.scalefocus.pms.domain.Partner;
import com.scalefocus.pms.repositories.PartnerRepository;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for Partners.
 */
@Service
public class PartnersServiceImpl implements PartnersService {


    private static boolean isImageGenerated = false;
    @Autowired
    private final PartnerRepository partnerRepository;

    public PartnersServiceImpl(final PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public boolean generateCloudTag() throws IOException {
        if (!isImageGenerated) {
            buildWordCloud();
            isImageGenerated = true;
        }
        return isImageGenerated;
    }

    /**
     * Get all partners form DB.
     *
     * @return List of partner's name.
     */
    private List<String> getAllPartners() {

        final List<Partner> allPartners = partnerRepository.findAll();
        final List<String> partnersNames = new ArrayList<>();
        for (Partner partner : allPartners) {
            partnersNames.add(partner.getName());
        }

        return partnersNames;
    }

    /**
     * Logic for creating a picture with tag cloud.
     *
     * @throws IOException .
     */
    private void buildWordCloud() throws IOException {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(CloudTagConstants.WORD_FREQUENCIES_TO_RETURN);
        frequencyAnalyzer.setMinWordLength(MIN_WORD_LENGTH);
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(getAllPartners());

        final Dimension dimension = new Dimension(WIDTH, HEIGHT);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(MIN_WORD_LENGTH);
        wordCloud.setBackground(new PixelBoundryBackground(getInputStream(STATIC_IMG_SCALEFOCUS_LOGO_PNG)));
        wordCloud.setBackgroundColor(new Color(RGB5));
        wordCloud.setColorPalette(new ColorPalette(
                new Color(RGB),
                new Color(RGB1),
                new Color(RGB2),
                new Color(RGB3),
                new Color(RGB4),
                new Color(RGB6)));
        wordCloud.setFontScalar(new LinearFontScalar(MIN_FONT, MAX_FONT));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile(CloudTagConstants.CLOUD_PNG);

    }

    private InputStream getInputStream(final String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }


}
