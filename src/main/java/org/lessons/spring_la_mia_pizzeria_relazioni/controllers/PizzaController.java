package org.lessons.spring_la_mia_pizzeria_relazioni.controllers;

import java.util.List;

import org.lessons.spring_la_mia_pizzeria_relazioni.models.Discount;
import org.lessons.spring_la_mia_pizzeria_relazioni.models.Pizza;
import org.lessons.spring_la_mia_pizzeria_relazioni.repos.IngredientRepo;
import org.lessons.spring_la_mia_pizzeria_relazioni.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Autowired
    private IngredientRepo ingredientRepo;

    @GetMapping
    public String index(@RequestParam(required = false) String search, Model model) {
        List<Pizza> pizzas;
        if (search == null || search.isBlank()) {
            pizzas = pizzaRepo.findAll();
        } else {
            pizzas = pizzaRepo.findByNameContainingIgnoreCaseOrBioContainingIgnoreCase(search, search);
        }
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("search", search);

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza pizza = pizzaRepo.findById(id).get();
        model.addAttribute("discounts", pizza.getDiscounts());
        model.addAttribute("pizza", pizza);

        return "/pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepo.findAll());

        return "pizzas/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza newPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());
            return "pizzas/create-edit";
        }

        pizzaRepo.save(newPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepo.findById(id).get());
        model.addAttribute("ingredients", ingredientRepo.findAll());
        model.addAttribute("edit", true);

        return "pizzas/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepo.findAll());
            return "pizzas/create-edit";
        }

        pizzaRepo.save(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        pizzaRepo.deleteById(id);

        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/discount")
    public String discocunt(@PathVariable Integer id, Model model) {
        Discount newDiscount = new Discount();
        newDiscount.setPizza(pizzaRepo.findById(id).get());
        model.addAttribute("discount", newDiscount);

        return new String("discounts/create-edit");
    }

}
