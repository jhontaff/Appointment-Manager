package com.appointmentmanager.repository;

import com.appointmentmanager.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    Optional<Advisor> findByDocument(String document);

    List<Advisor> findByNameContainingIgnoreCase(String name);
}

