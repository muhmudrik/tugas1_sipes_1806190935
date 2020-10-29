package apap.tugas.sipes.service;

import java.util.List;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;

public interface PenerbanganService {

    void addPenerbangan(PenerbanganModel penerbangan);

    void updatePenerbangan(PenerbanganModel penerbangan);
    
    void hapusPenerbangan(PenerbanganModel penerbangan);
    
    void setPesawatModel(PesawatModel pesawatById);
    
    List<PenerbanganModel> getAllPenerbangan();

	PenerbanganModel getPenerbanganById(Long idPenerbangan);

	List<PenerbanganModel> getPenerbanganNull();


}
