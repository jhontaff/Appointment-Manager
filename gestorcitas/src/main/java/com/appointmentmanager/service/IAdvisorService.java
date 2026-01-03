package com.appointmentmanager.service;

import com.appointmentmanager.entity.Advisor;

import java.util.List;

public interface IAdvisorService {
    public List<Advisor> getAdvisors();
    public List<Advisor> searchAdvisor(String document, String name);
    public Advisor getAdvisorById(Long id);
    public Advisor CreateAdvisor(Advisor advisor);
    public void deleteAdvisor(Long id);
    public void updateAdvisor(Long id, Advisor advisor);
}
