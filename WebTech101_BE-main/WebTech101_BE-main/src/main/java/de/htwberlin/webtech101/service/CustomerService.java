package de.htwberlin.webtech101.service;

import de.htwberlin.webtech101.persistence.CustomerEntity;
import de.htwberlin.webtech101.persistence.CustomerRepository;
import de.htwberlin.webtech101.persistence.CustomerType;
import de.htwberlin.webtech101.web.api.ContactInfo;
import de.htwberlin.webtech101.web.api.Customer;
import de.htwberlin.webtech101.web.api.CustomerManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll().stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .map(this::transformEntity)
                .orElse(null);
    }

    public Customer create(CustomerManipulationRequest request) {
        validatePrivateCustomer(request);
        validateCompanyCustomer(request);

        var type = CustomerType.valueOf(request.getType());

        var entity = new CustomerEntity(
                type,
                request.getCompanyName(),
                request.getStreet(),
                request.getHouseNumber(),
                request.getPostalCode(),
                request.getCity(),
                request.getEmail(),
                request.getPhone(),
                request.getPrivateFirstName(),
                request.getPrivateLastName()
        );

        entity.setPublicId(generatePublicId(type));
        var saved = customerRepository.save(entity);
        return transformEntity(saved);
    }

    public Customer update(Long id, CustomerManipulationRequest request) {
        var optional = customerRepository.findById(id);
        if (optional.isEmpty()) return null;

        validatePrivateCustomer(request);
        validateCompanyCustomer(request);

        var customer = optional.get();
        customer.setType(CustomerType.valueOf(request.getType()));
        customer.setCompanyName(request.getCompanyName());
        customer.setStreet(request.getStreet());
        customer.setHouseNumber(request.getHouseNumber());
        customer.setPostalCode(request.getPostalCode());
        customer.setCity(request.getCity());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setPrivateFirstName(request.getPrivateFirstName());
        customer.setPrivateLastName(request.getPrivateLastName());

        return transformEntity(customerRepository.save(customer));
    }

    public boolean deleteById(Long id) {
        if (!customerRepository.existsById(id)) return false;
        customerRepository.deleteById(id);
        return true;
    }

    private Customer transformEntity(CustomerEntity entity) {
        List<ContactInfo> contacts = entity.getContacts() != null
                ? entity.getContacts().stream()
                .map(person -> new ContactInfo(
                        person.getFirstName(),
                        person.getLastName(),
                        person.getRole()
                ))
                .collect(Collectors.toList())
                : List.of();

        return new Customer(
                entity.getId(),
                entity.getPublicId(),
                entity.getType().name(),
                entity.getCompanyName(),
                entity.getPrivateFirstName(),
                entity.getPrivateLastName(),
                entity.getStreet(),
                entity.getHouseNumber(),
                entity.getPostalCode(),
                entity.getCity(),
                entity.getEmail(),
                entity.getPhone(),
                contacts
        );
    }

    private String generatePublicId(CustomerType type) {
        long count = customerRepository.countByType(type);
        String prefix = (type == CustomerType.COMPANY) ? "COMP" : "PRIV";
        return String.format("%s-%04d", prefix, count + 1);
    }

    private void validatePrivateCustomer(CustomerManipulationRequest request) {
        if (CustomerType.valueOf(request.getType()) == CustomerType.PRIVATE) {
            if (!isNullOrBlank(request.getCompanyName())) {
                throw new IllegalArgumentException("Privatkunden dürfen keinen Firmennamen haben.");
            }
            if (isNullOrBlank(request.getPrivateFirstName()) || isNullOrBlank(request.getPrivateLastName())) {
                throw new IllegalArgumentException("Privatkunden müssen Vor- und Nachnamen haben.");
            }
            if (isNullOrBlank(request.getStreet()) ||
                    isNullOrBlank(request.getHouseNumber()) ||
                    isNullOrBlank(request.getPostalCode()) ||
                    isNullOrBlank(request.getCity())) {
                throw new IllegalArgumentException("Für Privatkunden müssen Straße, Hausnummer, PLZ und Ort angegeben sein.");
            }
        }
    }

    private void validateCompanyCustomer(CustomerManipulationRequest request) {
        if (CustomerType.valueOf(request.getType()) == CustomerType.COMPANY) {
            if (isNullOrBlank(request.getCompanyName())) {
                throw new IllegalArgumentException("Für Firmenkunden muss ein Firmenname angegeben werden.");
            }
            if (!isNullOrBlank(request.getPrivateFirstName()) || !isNullOrBlank(request.getPrivateLastName())) {
                throw new IllegalArgumentException("Firmenkunden dürfen keinen Vornamen oder Nachnamen enthalten.");
            }
            if (isNullOrBlank(request.getStreet()) ||
                    isNullOrBlank(request.getHouseNumber()) ||
                    isNullOrBlank(request.getPostalCode()) ||
                    isNullOrBlank(request.getCity())) {
                throw new IllegalArgumentException("Für Firmenkunden müssen Straße, Hausnummer, PLZ und Ort angegeben sein.");
            }
        }
    }

    private boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

