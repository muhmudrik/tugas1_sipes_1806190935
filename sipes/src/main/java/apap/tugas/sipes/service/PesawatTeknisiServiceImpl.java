package apap.tugas.sipes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.repository.PesawatTeknisiDb;

@Service
@Transactional
public class PesawatTeknisiServiceImpl implements PesawatTeknisiService {
    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Override
    public void addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisi) {
        pesawatTeknisiDb.save(pesawatTeknisi);
    }

}
