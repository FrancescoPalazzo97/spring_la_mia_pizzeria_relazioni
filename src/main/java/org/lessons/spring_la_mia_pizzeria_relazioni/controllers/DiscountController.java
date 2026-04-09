package org.lessons.spring_la_mia_pizzeria_relazioni.controllers;

import org.lessons.spring_la_mia_pizzeria_relazioni.models.Discount;
import org.lessons.spring_la_mia_pizzeria_relazioni.repos.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountRepo repo;

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("discount", repo.findById(id).get());
        return new String("discounts/show");
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("discount") Discount newDiscount, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "discounts/crete";
        }

        repo.save(newDiscount);

        return "redirect:/pizzas/" + newDiscount.getPizza().getId();
    }

}
