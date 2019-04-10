// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.repositories;

import com.scalefocus.pms.domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task,Long> {
}
