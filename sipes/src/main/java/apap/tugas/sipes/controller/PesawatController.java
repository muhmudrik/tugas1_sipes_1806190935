package apap.tugas.sipes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.PesawatTeknisiService;
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

    @Autowired
    private PesawatTeknisiService pesawatTeknisiService;

    @GetMapping("/")
    private String home() {
        return "home";
    }
    
    @GetMapping("/pesawat/tambah")
    private String getFormTambahPesawat(Model model){
        List<TipeModel> tipeList = tipeService.getListTipe();
        model.addAttribute("tipe", tipeList);
        
        List<TeknisiModel> teknisiList = teknisiService.getListTeknisi();
        model.addAttribute("teknisi", teknisiList);
        
        PesawatModel pesawat = new PesawatModel();
        model.addAttribute("pesawat", pesawat);

        PesawatTeknisiModel pesawatTeknisi = new PesawatTeknisiModel();
        List<PesawatTeknisiModel> pesawatTeknisiList = new ArrayList<PesawatTeknisiModel>();

        pesawatTeknisiList.add(pesawatTeknisi);
        pesawatTeknisi.setPesawatModel(pesawat);
        pesawat.setListPesawatTeknisi(pesawatTeknisiList);

        return "form-tambah-pesawat";
    }

    @PostMapping(value = "/pesawat/tambah/", params = "addrow")
    private String addRowBaruTeknisiPesawat(
        @ModelAttribute PesawatModel pesawat,
        Model model
    ) {
        List<TipeModel> tipeList = tipeService.getListTipe();
        model.addAttribute("tipe", tipeList);

        List<TeknisiModel> teknisiList = teknisiService.getListTeknisi();
        model.addAttribute("teknisi", teknisiList);

        PesawatTeknisiModel pesawatTeknisi = new PesawatTeknisiModel();
        pesawatTeknisi.setPesawatModel(pesawat);
        pesawat.getListPesawatTeknisi().add(pesawatTeknisi);
        System.out.println(pesawat.getListPesawatTeknisi().size());
        model.addAttribute("pesawat", pesawat);
        return "form-tambah-pesawat";
    }

    @PostMapping(value = "/pesawat/tambah/", params = "deleterow")
    private String deleteRowBaruTeknisiPesawat(
        @ModelAttribute PesawatModel pesawat,
        Model model
    ) {
        List<TipeModel> tipeList = tipeService.getListTipe();
        model.addAttribute("tipe", tipeList);

        List<TeknisiModel> teknisiList = teknisiService.getListTeknisi();
        model.addAttribute("teknisi", teknisiList);
        
        pesawat.getListPesawatTeknisi().remove(0);
        System.out.println(pesawat.getListPesawatTeknisi().size());
        model.addAttribute("pesawat", pesawat);
        return "form-tambah-pesawat";
    }
    
    @PostMapping(value = "/pesawat/tambah/", params = "save")
    private String savePesawat(
        @ModelAttribute PesawatModel pesawat,
        Model model
    ) {
        // System.out.println("SAVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        // System.out.println(pesawat.getId());
        List<PesawatTeknisiModel> temp = pesawat.getListPesawatTeknisi();
        pesawat.setListPesawatTeknisi(null);
        while (true) {
            try {
                pesawatService.addPesawat(pesawat);
                break;
            } catch (DataIntegrityViolationException e) {
                continue;
            }
            
        }
        model.addAttribute("pesawat", pesawat);
        // System.out.println(pesawat.getId());
        for (PesawatTeknisiModel pesawatTeknisi : temp) {
            pesawatTeknisi.setPesawatModel(pesawat);
            pesawatTeknisiService.addPesawatTeknisi(pesawatTeknisi);
        }
        return "hasil-tambah-pesawat";
    }

    @GetMapping("/pesawat")
    private String getAllPesawat(Model model){
        List<PesawatModel> listPesawat = pesawatService.getAllPesawat();
        model.addAttribute("listPesawat", listPesawat);
        return "view-all-pesawat";
    }

}
