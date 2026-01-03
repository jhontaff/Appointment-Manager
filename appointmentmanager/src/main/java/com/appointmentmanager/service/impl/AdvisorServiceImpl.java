package com.appointmentmanager.service.impl;

import com.appointmentmanager.dto.mapper.AdvisorMapper;
import com.appointmentmanager.dto.request.AdvisorCreateRequest;
import com.appointmentmanager.dto.request.AdvisorUpdateRequest;
import com.appointmentmanager.dto.response.AdvisorResponse;
import com.appointmentmanager.entity.Advisor;
import com.appointmentmanager.exception.BusinessException;
import com.appointmentmanager.exception.ResourceNotFoundException;
import com.appointmentmanager.repository.AdvisorRepository;
import com.appointmentmanager.service.IAdvisorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisorServiceImpl implements IAdvisorService {

    private final AdvisorRepository advisorRepository;
    private final AdvisorMapper advisorMapper;

    public AdvisorServiceImpl(AdvisorRepository advisorRepository,
                              AdvisorMapper advisorMapper) {
        this.advisorRepository = advisorRepository;
        this.advisorMapper = advisorMapper;
    }

    @Override
    public List<AdvisorResponse> getAdvisors() {
        return advisorRepository.findAll().stream().map(advisorMapper::toDto).toList();
    }

    @Override
    public List<AdvisorResponse> searchAdvisor(String document, String name) {
        List<Advisor> advisors;
        if(document != null && !document.isEmpty()) {
             advisors =List.of(
                advisorRepository.findByDocument(document)
                        .orElseThrow(
                                () -> new BusinessException("Advisor with document " + document + " not found")
                        )
            );
            return advisors.stream().map(advisorMapper::toDto).toList();
        }
        if(name != null && !name.isEmpty()) {
            advisors = advisorRepository.findByNameContainingIgnoreCase(name);
            return advisors.stream().map(advisorMapper::toDto).toList();
        }
        throw new BusinessException("At least one search parameter (document or name) must be provided");
    }

    @Override
    public AdvisorResponse getAdvisorById(Long id) {
         Advisor entity = advisorRepository.findById(id)
                .orElseThrow(
                        () -> new BusinessException("Advisor with id " + id + " not found")
                );
        return advisorMapper.toDto(entity);
    }

    @Override
    public AdvisorResponse createAdvisor(AdvisorCreateRequest advisorCreateRequest) {
        Advisor advisor = advisorMapper.toEntity(advisorCreateRequest);
        if (isAdvisorValidToCreate(advisor)) {
            advisorRepository.save(advisor);
            return advisorMapper.toDto(advisor);
        }
        throw new BusinessException("Advisor could not be created");
    }

    private boolean isAdvisorValidToCreate(Advisor advisor) {
        if (advisor.getDocumentNumber() == null || advisor.getDocumentNumber().isEmpty()) {
            throw new BusinessException("Document is required");
        }
        if (advisorRepository.findByDocument(advisor.getDocumentNumber()).isPresent()) {
            throw new BusinessException("Advisor with document " + advisor.getDocumentNumber() + " already exists");
        }
        return true;
    }

    @Override
    public void deleteAdvisor(Long id) {
        if (!advisorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Advisor not found");
        }
        advisorRepository.deleteById(id);
    }

    @Override
    public AdvisorResponse updateAdvisor(Long id, AdvisorUpdateRequest advisorUpdateRequest) {
        Advisor existingAdvisor = advisorRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Advisor not found")
                );
        advisorMapper.updateEntityFromDto(advisorUpdateRequest, existingAdvisor);
        //advisorRepository.save(existingAdvisor);
        return advisorMapper.toDto(existingAdvisor);
    }
}
