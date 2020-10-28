package apap.tugas.sipes.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("pesawat", pesawat);
        return "form-tambah-pesawat";
    }
    
    @PostMapping(value = "/pesawat/tambah/", params = "save")
    private String savePesawat(
        @ModelAttribute PesawatModel pesawat,
        Model model
    ) {
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

    @GetMapping("/pesawat/{idPesawat}")
    private String viewDetailPesawat(
        @PathVariable Long idPesawat,
        Model model
    ) {
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("pesawat", pesawat);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        model.addAttribute("formatter", formatter);
        
        List<TeknisiModel> listTeknisi = new ArrayList<TeknisiModel>();
        List<PesawatTeknisiModel> listPesawatTeknisi = pesawat.getListPesawatTeknisi();
        for (PesawatTeknisiModel pesawatTeknisi : listPesawatTeknisi) {
            listTeknisi.add(pesawatTeknisi.getTeknisiModel());
        }

        model.addAttribute("listTeknisi", listTeknisi);
        return "view-pesawat";
    }

}
