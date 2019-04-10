// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;

import java.util.List;

public interface PhaseService {

    Phase createPhase(PhaseBindingModel bindingModel);

    List<PhaseBindingModel> getAvailablePhasesByProjectId(final ProjectBindingModel project);
}
