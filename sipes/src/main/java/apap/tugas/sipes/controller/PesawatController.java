package apap.tugas.sipes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.TeknisiService;
import apap.tugas.sipes.service.TipeService;

@Controller
public class PesawatController {
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    @GetMapping("/")
    private String home() {
        return "home";
    }
    
    @GetMapping("/pesawat/tambah")
    private String getFormTambahPesawat(Model model){
        model.addAttribute("pesawat", new PesawatModel());

        List<TipeModel> tipeList = tipeService.getListTipe();
        model.addAttribute("tipe", tipeList);
        
        List<TeknisiModel> teknisiList = teknisiService.getListTeknisi();
        model.addAttribute("teknisi", teknisiList);
        return "form-tambah-pesawat";
    }

    @GetMapping("/pesawat")
    private String getAllPesawat(Model model){

        return "view-all-pesawat";
    }

}
