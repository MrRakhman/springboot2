package kz.bitlab.techorda.sprintboot.controller;


import kz.bitlab.techorda.sprintboot.model.ApplicationRequest;
import kz.bitlab.techorda.sprintboot.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController{
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        List<ApplicationRequest> requests = requestRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("requests", requests);
        return "index";
    }

    @GetMapping(value = "/add-request")
    public String addRequestPage(){
        return "addrequest";
    }

    @PostMapping(value = "/add-request")
    public String addRequest(ApplicationRequest request){
        requestRepository.save(request);
        return "redirect:/";
    }

    @GetMapping(value = "/request-details/{requestId}")
    public String requestDetails(@PathVariable(name="requestId") Long id, Model model){
        ApplicationRequest request = requestRepository.findById(id).orElse(null);
        model.addAttribute("zapros", request);
        return "details";
    }

    @PostMapping(value = "/save-request")
    public String saveRequest(ApplicationRequest request){
        requestRepository.save(request);
        Long requestId = request.getId();
        return "redirect:/request-details/" + requestId;
    }

    @PostMapping(value = "/delete-request")
    public String deleteRequest(@RequestParam(name="id") Long id){
        requestRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/handled-requests")
    public String handledRequestPage(Model model){
        List<ApplicationRequest> requests = requestRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("requests", requests);
        return "handledrequest";
    }

    @GetMapping(value = "/unhandled-requests")
    public String unhandledRequestPage(Model model){
        List<ApplicationRequest> requests = requestRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("requests", requests);
        return "unhandledrequest";
    }
}
