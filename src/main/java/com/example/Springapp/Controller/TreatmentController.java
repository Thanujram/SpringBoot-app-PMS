package com.example.Springapp.Controller;

import com.example.Springapp.Model.Treatment;
import com.example.Springapp.Services.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TreatmentController {

    private TreatmentService treatmentService;

    @GetMapping("/treatment")
    public String viewHomePage(Model model) {
        Treatment treatment = new Treatment();
        model.addAttribute("listTreatment",treatmentService.getAllTreatment());
        return "treatment" ;


    }


    @GetMapping("/showNewTreatment")//showNewVaccineForm
    public String showNewTreatment(Model model) {
        Treatment treatment = new Treatment();
        model.addAttribute("treatment", treatment);
        return "new_treatment";
    }

    @PostMapping("/saveTreatment")
    public String saveTreatment(@ModelAttribute("treatment") Treatment treatment) {
        treatmentService.saveTreatment(treatment);
        return "redirect:/treatment";
    }

    @GetMapping("/showUpdateTreatment/{id}")
    public String showUpdateTreatment(@PathVariable( value = "id") long id, Model model) {

        Treatment treatment = treatmentService.getTreatmentById(id);
        model.addAttribute("treatment", treatment);
        return "update_treatment";
    }

    @GetMapping("/deleteTreatment/{id}")
    public String deleteTreatment(@PathVariable (value = "id") long id) {

        this.treatmentService.deleteTreatmentById(id);
        return "redirect:/treatment";
    }

}
