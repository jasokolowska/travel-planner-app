package com.sokolowska.travelplannerapi.controller;

import com.sokolowska.travelplannerapi.model.Destination;
import com.sokolowska.travelplannerapi.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
@RequestMapping("/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

//    TODO: Add mapping for "/list"
    @GetMapping
    public String getAll(Model model){
        //get destinations from db
        List<Destination> destinations = destinationService.findAll();
        //add to the spring model
        model.addAttribute("destinations", destinations);
        return "list-destinations";
    }

    @GetMapping("/add-form")
    private String showAddForm(Model model){
        Destination destination = new Destination();
        model.addAttribute("destination", destination);
        return "destination-form";
    }

    @PostMapping("/add")
    public String addDestination(@ModelAttribute("destination") Destination destination){
        destinationService.save(destination);
        return "redirect:/destinations";
    }

    @GetMapping("/delete")
    public String deleteDestination(@RequestParam("id") Long id){
        destinationService.deleteById(id);
        return "redirect:/destinations";
    }
}
