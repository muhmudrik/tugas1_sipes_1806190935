package apap.tugas.sipes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PenerbanganDb;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {
    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public void updatePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getAllPenerbangan() {
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long idPenerbangan) {
        return penerbanganDb.findById(idPenerbangan).get();
    }

    @Override
    public void hapusPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganNull() {
        List<PenerbanganModel> penerbanganNull = penerbanganDb.findByPesawatModelIsNull();
        return penerbanganNull;
    }

    @Override
    public void setPesawatModel(PesawatModel pesawatById) {
        
    }
    
}
