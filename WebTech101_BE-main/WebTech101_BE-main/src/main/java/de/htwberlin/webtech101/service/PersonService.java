package de.htwberlin.webtech101.service;

import de.htwberlin.webtech101.persistence.*;
import de.htwberlin.webtech101.web.api.Person;
import de.htwberlin.webtech101.web.api.PersonManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final CustomerRepository customerRepository;

    public PersonService(PersonRepository personRepository, CustomerRepository customerRepository) {
        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll().stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Person findByID(Long id) {
        return personRepository.findById(id)
                .map(this::transformEntity)
                .orElse(null);
    }

    public Person create(PersonManipulationRequest request) {
        var title = request.getTitle() != null ? PersonTitle.valueOf(request.getTitle()) : PersonTitle.unknown;
        var type = PersonType.valueOf(request.getType());

        if (type == PersonType.COMPANY_CONTACT && request.getCompanyId() == null) {
            throw new IllegalArgumentException("companyId is required for type COMPANY_CONTACT");
        }

        var company = request.getCompanyId() != null
                ? customerRepository.findById(request.getCompanyId()).orElse(null)
                : null;

        var personEntity = new PersonEntity(
                request.getFirstName(),
                request.getLastName(),
                title,
                request.getEmail(),
                request.getPhone(),
                type,
                request.getRole(),
                company
        );

        personEntity = personRepository.save(personEntity);

        // 🔁 publicId basierend auf Typ generieren
        String prefix = (type == PersonType.COMPANY_CONTACT) ? "CC" : "PC";
        personEntity.setPublicId(String.format("%s-%04d", prefix, personEntity.getId()));

        personEntity = personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    public Person update(long id, PersonManipulationRequest request) {
        var optional = personRepository.findById(id);
        if (optional.isEmpty()) return null;

        var entity = optional.get();
        var type = PersonType.valueOf(request.getType());

        if (type == PersonType.COMPANY_CONTACT && request.getCompanyId() == null) {
            throw new IllegalArgumentException("companyId is required for type COMPANY_CONTACT");
        }

        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setTitle(PersonTitle.valueOf(request.getTitle()));
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setRole(request.getRole());
        entity.setType(type);

        if (request.getCompanyId() != null) {
            var company = customerRepository.findById(request.getCompanyId()).orElse(null);
            entity.setCompany(company);
        } else {
            entity.setCompany(null);
        }

        return transformEntity(personRepository.save(entity));
    }

    public boolean deleteById(long id) {
        if (!personRepository.existsById(id)) return false;
        personRepository.deleteById(id);
        return true;
    }

    private Person transformEntity(PersonEntity personEntity) {
        var title = personEntity.getTitle() != null ? personEntity.getTitle().name() : PersonTitle.unknown.name();
        var type = personEntity.getType() != null ? personEntity.getType().name() : null;
        var companyId = personEntity.getCompany() != null ? personEntity.getCompany().getId() : null;

        return new Person(
                personEntity.getId(),
                personEntity.getPublicId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                title,
                type,
                personEntity.getRole(),
                personEntity.getEmail(),
                personEntity.getPhone(),
                companyId
        );
    }
}
