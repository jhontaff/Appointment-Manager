package com.appointmentmanager.service;

import com.appointmentmanager.dto.request.AdvisorCreateRequest;
import com.appointmentmanager.dto.request.AdvisorUpdateRequest;
import com.appointmentmanager.dto.response.AdvisorResponse;

import java.util.List;

public interface IAdvisorService {
    public List<AdvisorResponse> getAdvisors();
    public List<AdvisorResponse> searchAdvisor(String document, String name);
    public AdvisorResponse getAdvisorById(Long id);
    public AdvisorResponse createAdvisor(AdvisorCreateRequest advisorCreateRequest);
    public void deleteAdvisor(Long id);
    public AdvisorResponse updateAdvisor(Long id, AdvisorUpdateRequest advisorUpdateRequest);
}
