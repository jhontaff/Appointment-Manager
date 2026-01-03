package com.appointmentmanager.service.impl;

import com.appointmentmanager.entity.Advisor;
import com.appointmentmanager.repository.AdvisorRepository;
import com.appointmentmanager.service.IAdvisorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisorServiceImpl implements IAdvisorService {

    private final AdvisorRepository advisorRepository;

    public AdvisorServiceImpl(AdvisorRepository advisorRepository) {
        this.advisorRepository = advisorRepository;
    }

    @Override
    public List<Advisor> getAdvisors() {
        return advisorRepository.findAll();
    }

    @Override
    public List<Advisor> searchAdvisor(String document, String name) {
        if(document != null && !document.isEmpty()) {
            return List.of(
                advisorRepository.findByDocument(document)
                        .orElseThrow(
                                () -> new RuntimeException("Advisor with document " + document + " not found")
                        )
            );
        }
        if(name != null && !name.isEmpty()) {
            return advisorRepository.findByNameContainingIgnoreCase(name);
        }

        throw new RuntimeException("At least one search parameter (document or name) must be provided");
    }

    @Override
    public Advisor getAdvisorById(Long id) {
        return null;
    }

    @Override
    public Advisor CreateAdvisor(Advisor advisor) {
        return null;
    }

    @Override
    public void deleteAdvisor(Long id) {

    }

    @Override
    public void updateAdvisor(Long id, Advisor advisor) {

    }
}
