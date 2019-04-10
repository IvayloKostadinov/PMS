// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.repositories;

import com.scalefocus.pms.domain.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByClientName(String clientName);
}
