package de.htwberlin.webtech101.service;

import de.htwberlin.webtech101.persistence.CompanyInfoEntity;
import de.htwberlin.webtech101.persistence.CompanyInfoRepository;
import de.htwberlin.webtech101.web.api.CompanyInfo;
import de.htwberlin.webtech101.web.api.CompanyInfoManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyInfoService {

    private final CompanyInfoRepository repository;

    public CompanyInfoService(CompanyInfoRepository repository) {
        this.repository = repository;
    }

    public Optional<CompanyInfoEntity> findCompanyInfo() {
        return repository.findAll().stream().findFirst();
    }

    public CompanyInfo updateCompanyInfo(CompanyInfoManipulationRequest request) {
        CompanyInfoEntity entity = findCompanyInfo().orElse(new CompanyInfoEntity());

        entity.setCompanyName(request.getCompanyName());
        entity.setStreet(request.getStreet());
        entity.setHouseNumber(request.getHouseNumber());
        entity.setPostalCode(request.getPostalCode());
        entity.setCity(request.getCity());
        entity.setCountry(request.getCountry());
        entity.setRegistrationNumber(request.getRegistrationNumber());
        entity.setVatId(request.getVatId());
        entity.setIban(request.getIban());
        entity.setBankName(request.getBankName());
        entity.setBic(request.getBic());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());

        CompanyInfoEntity saved = repository.save(entity);

        return new CompanyInfo(
                saved.getPublicId(),
                saved.getCompanyName(),
                saved.getStreet(),
                saved.getHouseNumber(),
                saved.getPostalCode(),
                saved.getCity(),
                saved.getCountry(),
                saved.getRegistrationNumber(),
                saved.getVatId(),
                saved.getIban(),
                saved.getBankName(),
                saved.getBic(),
                saved.getEmail(),
                saved.getPhone()
        );
    }
}


