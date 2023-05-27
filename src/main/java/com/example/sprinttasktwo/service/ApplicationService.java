package com.example.sprinttasktwo.service;

import com.example.sprinttasktwo.models.ApplicationRequest;
import com.example.sprinttasktwo.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public List<ApplicationRequest> getApplicationRequestList() {
        return applicationRepository.findAll();
    }

    public void saveApp(ApplicationRequest applicationRequest) {
        applicationRepository.save(applicationRequest);
    }

    public ApplicationRequest findById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public void deleteAppById(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<ApplicationRequest> getNewApplicationRequestList() {
        return applicationRepository.findAllByHandledFalse();
    }

    public List<ApplicationRequest> getHandledApplicationRequestList() {
        return applicationRepository.findAllByHandledTrue();
    }
}
