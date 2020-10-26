package apap.tugas.sipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.repository.TipeDb;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
    @Autowired
    TipeDb tipeDb;
    
}
