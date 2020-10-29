package apap.tugas.sipes.service;

import java.util.List;

import apap.tugas.sipes.model.PenerbanganModel;

public interface PenerbanganService {

    void addPenerbangan(PenerbanganModel penerbangan);
    
    List<PenerbanganModel> getAllPenerbangan();
    
}
