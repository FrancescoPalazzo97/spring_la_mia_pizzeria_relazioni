package org.lessons.spring_la_mia_pizzeria_relazioni.repos;

import org.lessons.spring_la_mia_pizzeria_relazioni.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepo extends JpaRepository<Discount, Integer> {

}
