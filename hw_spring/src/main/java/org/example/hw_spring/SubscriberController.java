package org.example.hw_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubscriberController {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @GetMapping("/subscriber")
    public String showForm(Model model) {
        model.addAttribute("subscriber", new Subscriber());
        return "subscriberForm";
    }

    @PostMapping("/subscriber")
    public String submitForm(@ModelAttribute Subscriber subscriber) {
        subscriberRepository.save(subscriber);
        return "result";
    }
}
