package de.htwberlin.webtech101.web;

import de.htwberlin.webtech101.service.PersonService;
import de.htwberlin.webtech101.web.api.Person;
import de.htwberlin.webtech101.web.api.PersonManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/api/v1/contacts")
    public ResponseEntity<List<Person>> fetchPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(path = "/api/v1/contacts/{id}")
    public ResponseEntity<Person> fetchPersonById(@PathVariable long id) {
        var person = personService.findByID(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/contacts")
    public ResponseEntity<Void> createPerson(@RequestBody PersonManipulationRequest request) throws URISyntaxException {
        var person = personService.create(request);
        URI uri = new URI("/api/v1/contacts/" + person.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/contacts/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody PersonManipulationRequest request) {
        var person = personService.update(id, request);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/contacts/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        boolean successful = personService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}


