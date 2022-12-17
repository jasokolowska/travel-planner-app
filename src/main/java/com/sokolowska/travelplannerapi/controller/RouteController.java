package com.sokolowska.travelplannerapi.controller;

import com.sokolowska.travelplannerapi.model.Route;
import com.sokolowska.travelplannerapi.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public String getAll(Model model){
        List<Route> routes = routeService.findAll();
        model.addAttribute("routes", routes);
        return "routes-list";
    }

    @GetMapping("/add-form")
    private String showAddForm(Model model){
        Route route = new Route();
        model.addAttribute("route", route);
        return "route-form";
    }

//    @PostMapping("/add")
//    public String addDestination(@ModelAttribute("route") Route route){
//        routeService.process(route);
//        return "redirect:/routes";
//    }

    @GetMapping("/delete")
    public String deleteDestination(@RequestParam("id") Long id){
        routeService.deleteById(id);
        return "redirect:/routes";
    }

    @GetMapping("/system")
    public String getSystemConfiguration(){
        return "routes-system";
    }
}
