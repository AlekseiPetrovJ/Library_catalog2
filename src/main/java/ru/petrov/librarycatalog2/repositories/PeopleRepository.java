package ru.petrov.librarycatalog2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petrov.librarycatalog2.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
