package apap.tugas.sipes.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.service.PenerbanganService;
import apap.tugas.sipes.service.PesawatService;

@Controller
public class PenerbanganController {
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    private String getAllPesawat(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getAllPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "view-all-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    private String getFormTambahPenerbangan(Model model){
        PenerbanganModel penerbangan = new PenerbanganModel();
        model.addAttribute("penerbangan", penerbangan);
        return "form-tambah-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    private String tambahPenerbangan(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ){
        if(penerbangan.getNomor_penerbangan().length()!=16){
            model.addAttribute("kode16", "Kode penerbangan harus 16 karakter.");
            model.addAttribute("penerbangan", penerbangan);
            return "form-tambah-penerbangan";
        }
        try {
            penerbanganService.addPenerbangan(penerbangan);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("kodeunik", "Kode penerbangan harus unik.");
            model.addAttribute("penerbangan", penerbangan);
            return "form-tambah-penerbangan";
        }
        model.addAttribute("penerbangan", penerbangan);
        return "hasil-tambah-penerbangan";
    }

    @GetMapping("/penerbangan/{idPenerbangan}")
    private String viewDetailPenerbangan(
        @PathVariable Long idPenerbangan,
        Model model
    ) {
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        model.addAttribute("formatter", formatter);
        return "view-penerbangan";
    }
}
