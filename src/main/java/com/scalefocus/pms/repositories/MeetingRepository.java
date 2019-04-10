// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.repositories;

import com.scalefocus.pms.domain.Meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
