package com.example.Springapp.Controller;

import com.example.Springapp.Model.Staff;
import com.example.Springapp.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    public String HomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/staff")
    public String viewHomePage(Model model) {
        Staff staff = new Staff();
        model.addAttribute("listStaff",staffService.getAllStaffs());
        return "staff" ;


    }


    @GetMapping("/showNewStaff")//showNewEmployeeForm
    public String showNewStaff(Model model) {
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        return "new_staff";
    }

    @PostMapping("/saveStaff")
    public String saveStaff(@ModelAttribute("staff") Staff staff) {
        staffService.saveStaff(staff);
        return "redirect:/staff";
    }

    @GetMapping("/showUpdate/{id}")//showFormForUpdate
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        Staff staff = staffService.getStaffById(id);

        model.addAttribute("staff", staff);
        return "update_staff";
    }

    @GetMapping("/deleteStaff/{id}")
    public String deleteStaff(@PathVariable (value = "id") long id) {

        this.staffService.deleteStaffById(id);
        return "redirect:/staff";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Staff> page = staffService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Staff> listStaff = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listStaff);
        return "index";
    }
}
