package com.example.Springapp.Controller;

import com.example.Springapp.Model.Patient;
import com.example.Springapp.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient")
    public String viewHomePage(Model model) {
        Patient patient = new Patient();
        model.addAttribute("listPatients",patientService.getAllPatients());
        return "patient" ;

    }


    @GetMapping("/showNewPatient")//showNewPatientForm
    public String showNewPatientForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "new_patient";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patient";
    }

    @GetMapping("/showUpdatePatient/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        Patient patient = patientService.getPatientById(id);

        model.addAttribute("patient", patient);
        return "update_patient";
    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable (value = "id") long id) {

        // call delete patient method
        this.patientService.deletePatientById(id);
        return "redirect:/patient";
    }
}
