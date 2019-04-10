// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.repositories;

import com.scalefocus.pms.domain.Partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
