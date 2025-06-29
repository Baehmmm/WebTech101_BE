package de.htwberlin.webtech101.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfoEntity, Long> {

    Optional<CompanyInfoEntity> findByPublicId(String publicId);

}
