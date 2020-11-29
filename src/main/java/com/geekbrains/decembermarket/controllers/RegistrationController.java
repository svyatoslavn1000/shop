package com.geekbrains.decembermarket.controllers;


import com.geekbrains.decembermarket.entities.User;
import com.geekbrains.decembermarket.services.RolesService;
import com.geekbrains.decembermarket.services.UserService;
import com.geekbrains.decembermarket.utils.SystemUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private RolesService rolesService;
    private UserService userService;

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showMyLoginPage(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }

    @PostMapping("/process")
    public String processRegistrationForm(@Validated @ModelAttribute("systemUser") SystemUser systemUser, BindingResult bindingResult, Model model) {
        String phone = systemUser.getPhone();
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        User existing = userService.findByPhone(phone);
        if (existing != null) {
            // theSystemUser.setUserName(null);
            model.addAttribute("systemUser", systemUser);
            model.addAttribute("registrationError", "User with current username is already exist");
            return "registration-form";
        }
        userService.save(systemUser);
        return "registration-confirmation";
    }
}
