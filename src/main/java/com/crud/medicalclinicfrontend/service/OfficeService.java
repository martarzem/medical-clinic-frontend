package com.crud.medicalclinicfrontend.service;

import com.crud.medicalclinicfrontend.domain.Office;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Data
@Service
public class OfficeService {
    private static OfficeService officeService;

    private RestTemplate restTemplate = new RestTemplate();

    public static OfficeService getInstance() {
        if (officeService == null) {
            officeService = new OfficeService();
        }
        return officeService;
    }

    private OfficeService() {
    }

    public List<Office> getAll() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/office")
                .build().encode().toUri();
        Office[] offices = restTemplate.getForObject(url, Office[].class);
        return Arrays.asList(ofNullable(offices).orElse(new Office[0]));
    }

    public Set findByDescription(String description) {
        return getAll().stream()
                .filter(office -> office.getDescription().contains(description))
                .collect(Collectors.toSet());
    }

    public void updateOffice(Office office) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/office")
                .build().encode().toUri();
        restTemplate.put(String.valueOf(url), office, Office.class);
    }

    public void deleteOffice(Office office) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/medical_clinic/office")
                .build().encode().toUri();
        restTemplate.delete(url + "/" + office.getId(), Office.class);
    }
}
