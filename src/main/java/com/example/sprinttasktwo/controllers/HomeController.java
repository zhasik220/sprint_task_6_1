package com.example.sprinttasktwo.controllers;

import com.example.sprinttasktwo.models.ApplicationRequest;
import com.example.sprinttasktwo.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ApplicationService applicationService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("apps", applicationService.getApplicationRequestList());
        return "home";
    }

    @GetMapping(value = "/add_app")
    public String addAppPage() {
        return "add_app";
    }

    @PostMapping(value = "/add_app")
    public String addApp(ApplicationRequest applicationRequest) {
        applicationService.saveApp(applicationRequest);
        return "redirect:/";
    }

    //    public String appPage(@PathVariable(name = "id") Long id,Model model){
    @GetMapping(value = "app_page/{id}")
    public String appPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("app", applicationService.findById(id));
        return "detail_app_page";
    }

    @PostMapping(value = "/handleApp")
    private String handleApp(@RequestParam(name = "id") Long id) {
        ApplicationRequest applicationRequest = applicationService.findById(id);
        applicationRequest.setHandled(true);
        applicationService.saveApp(applicationRequest);
        return "redirect:/";
    }

    @PostMapping(value = "/deleteApp")
    public String deleteApp(@RequestParam(name = "id") Long id) {
        applicationService.deleteAppById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/new_apps")
    public String newsApps(Model model) {
        model.addAttribute("apps", applicationService.getNewApplicationRequestList());
        return "new_app";
    }

    @GetMapping(value = "/handled_apps")
    public String handledApps(Model model) {
        model.addAttribute("apps", applicationService.getHandledApplicationRequestList());
        return "handle_app";
    }


}
