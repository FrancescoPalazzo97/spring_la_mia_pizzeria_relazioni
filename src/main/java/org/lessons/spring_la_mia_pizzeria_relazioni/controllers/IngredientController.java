package org.lessons.spring_la_mia_pizzeria_relazioni.controllers;

import org.lessons.spring_la_mia_pizzeria_relazioni.models.Ingredient;
import org.lessons.spring_la_mia_pizzeria_relazioni.repos.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepo ingredientRepo;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepo.findAll());
        return new String("ingredients/index");
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepo.findById(id).get());
        return new String("ingredients/show");
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute(new Ingredient());
        return new String("ingredients/create-edit");
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("ingredient") Ingredient newIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientRepo.save(newIngredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepo.findById(id).get());

        return new String("ingredients/create-edit");
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredientUpdate, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientRepo.save(ingredientUpdate);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ingredientRepo.deleteById(id);
        return "redirect:/ingredients";
    }

}
