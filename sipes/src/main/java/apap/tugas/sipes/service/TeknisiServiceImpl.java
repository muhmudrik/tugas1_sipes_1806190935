package apap.tugas.sipes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.TeknisiDb;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService {
    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public List<TeknisiModel> getListTeknisi() {
        return teknisiDb.findAll();
    }
    
}
