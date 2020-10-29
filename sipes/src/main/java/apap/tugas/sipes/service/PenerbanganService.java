package apap.tugas.sipes.service;

import java.util.List;

import apap.tugas.sipes.model.PenerbanganModel;

public interface PenerbanganService {

    void addPenerbangan(PenerbanganModel penerbangan);

    void updatePenerbangan(PenerbanganModel penerbangan);
    
    void hapusPenerbangan(PenerbanganModel penerbangan);
    
    List<PenerbanganModel> getAllPenerbangan();

	PenerbanganModel getPenerbanganById(Long idPenerbangan);

}
