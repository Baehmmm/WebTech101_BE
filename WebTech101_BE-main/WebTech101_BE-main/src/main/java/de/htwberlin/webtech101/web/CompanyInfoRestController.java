package de.htwberlin.webtech101.web;

import de.htwberlin.webtech101.persistence.CompanyInfoEntity;
import de.htwberlin.webtech101.service.CompanyInfoService;
import de.htwberlin.webtech101.web.api.CompanyInfo;
import de.htwberlin.webtech101.web.api.CompanyInfoManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/companyinfo")
public class CompanyInfoRestController {

    private final CompanyInfoService service;

    public CompanyInfoRestController(CompanyInfoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CompanyInfo> getCompanyInfo() {
        Optional<CompanyInfoEntity> entityOpt = service.findCompanyInfo();
        return entityOpt.map(entity -> ResponseEntity.ok(toDto(entity)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping
    public ResponseEntity<CompanyInfo> updateCompanyInfo(@RequestBody CompanyInfoManipulationRequest request) {
        CompanyInfo updated = service.updateCompanyInfo(request);
        return ResponseEntity.ok(updated);
    }

    private CompanyInfo toDto(CompanyInfoEntity e) {
        return new CompanyInfo(
                e.getPublicId(),
                e.getCompanyName(),
                e.getStreet(),
                e.getHouseNumber(),
                e.getPostalCode(),
                e.getCity(),
                e.getCountry(),
                e.getRegistrationNumber(),
                e.getVatId(),
                e.getIban(),
                e.getBankName(),
                e.getBic(),
                e.getEmail(),
                e.getPhone()
        );

    }
}

