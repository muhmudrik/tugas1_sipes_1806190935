package apap.tugas.sipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import apap.tugas.sipes.service.PesawatService;

@Controller
public class PesawatController {
    @Autowired
    private PesawatService pesawatService;

    @GetMapping("/")
    private String home() {
        return "home";
    }
    
    

    @GetMapping("/pesawat")
    private String getAllPesawat(Model model){

        return "view-all-pesawat";
    }

}
