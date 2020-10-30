package apap.tugas.sipes.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.PenerbanganService;
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

    @Autowired
    private PenerbanganService penerbanganService;

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

    @GetMapping({"/pesawat/{idPesawat}", "/pesawat/{idPesawat}/tambah-penerbangan"})
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

        List<PenerbanganModel> penerbanganNull = penerbanganService.getPenerbanganNull();
        model.addAttribute("penerbanganNull", penerbanganNull);
        return "view-pesawat";
    }

    @GetMapping("/pesawat/ubah/{idPesawat}")
    private String getFormUbahPesawat(
        @PathVariable Long idPesawat,
        Model model
    ) {
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("pesawat", pesawat);
        return "form-update-pesawat";
    }

    @PostMapping(value = "/pesawat/ubah/{idPesawat}", params = "save")
    private String ubahPesawat(
        @PathVariable Long idPesawat,
        @ModelAttribute PesawatModel pesawat,
        Model model
    ) {
        pesawatService.updatePesawat(pesawat);
        model.addAttribute("pesawat", pesawat);
        return "hasil-ubah-pesawat";
    }

    @PostMapping(value = "/pesawat/ubah/{idPesawat}", params = "hapus")
    private String hapusPesawat(
        @PathVariable Long idPesawat,
        Model model
    ) {
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("maskapai", pesawat.getMaskapai());
        model.addAttribute("noseri", pesawat.getNomor_seri());
        pesawatService.hapusPesawat(pesawat);
        return "hasil-hapus-pesawat";
    }

    @PostMapping(value = "/pesawat/{idPesawat}/tambah-penerbangan", params = "tambah")
    private String tambahPenerbangan(
        @PathVariable Long idPesawat,
        @RequestParam Long idPenerbangan,
        Model model
    ) {
        try {
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
            penerbangan.setPesawatModel(pesawatService.getPesawatById(idPesawat));
            penerbanganService.addPenerbangan(penerbangan);
            model.addAttribute("sks", "Penerbangan dengan nomor " 
                + penerbangan.getNomor_penerbangan() + " berhasil ditambahkan!");
            return viewDetailPesawat(idPesawat, model);
        } catch (InvalidDataAccessApiUsageException e) {
            return "redirect:/pesawat/" + idPesawat;
        }
    }

    @GetMapping("/pesawat/pesawat-tua")
    private String viewPesawatTua(Model model) {
        List<PesawatModel> listPesawat = new ArrayList<PesawatModel>();
        List<Integer> indexUmur = new ArrayList<Integer>();
        LocalDate today = LocalDate.now();
        Period period;
        for (PesawatModel pswt : pesawatService.getAllPesawat()) {
            period = Period.between(pswt.getTanggal_dibuat(), today);
            if(period.getYears()>10){
                listPesawat.add(pswt);
                indexUmur.add(period.getYears());
            }
        }
        model.addAttribute("listPesawat", listPesawat);
        model.addAttribute("listUmur", indexUmur);
        return "view-pesawat-tua";
    }

}
