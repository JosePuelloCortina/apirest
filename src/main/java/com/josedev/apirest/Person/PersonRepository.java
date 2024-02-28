package com.josedev.apirest.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository <Person, Integer> {

    List<Person> findByFirstname(@Param("name")String firstname);

}
