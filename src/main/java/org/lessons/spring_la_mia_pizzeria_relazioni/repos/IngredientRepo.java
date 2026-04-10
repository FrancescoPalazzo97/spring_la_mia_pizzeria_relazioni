package org.lessons.spring_la_mia_pizzeria_relazioni.repos;

import org.lessons.spring_la_mia_pizzeria_relazioni.models.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredients, Integer> {

}
