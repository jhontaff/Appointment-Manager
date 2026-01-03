package com.appointmentmanager.controller;

import com.appointmentmanager.dto.request.AdvisorCreateRequest;
import com.appointmentmanager.dto.response.AdvisorResponse;
import com.appointmentmanager.service.IAdvisorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/advisors")
public class AdvisorController {

    private final IAdvisorService advisorService;

    public AdvisorController(IAdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdvisorResponse createAdvisor(@Valid @RequestBody AdvisorCreateRequest advisorCreateRequest) {
        return advisorService.createAdvisor(advisorCreateRequest);
    }

    @GetMapping
    public ResponseEntity<List<AdvisorResponse>> getAdvisors() {
        List<AdvisorResponse> advisors = advisorService.getAdvisors();
        return advisors.isEmpty()
                ? ResponseEntity.noContent().build() //204 No Content
                : ResponseEntity.ok(advisors); //200 OK with a list of advisors
    }

}
