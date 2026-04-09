package org.lessons.spring_la_mia_pizzeria_relazioni.repos;

import java.util.List;

import org.lessons.spring_la_mia_pizzeria_relazioni.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

    public List<Pizza> findByNameContainingIgnoreCaseOrBioContainingIgnoreCase(String name, String bio);

}
