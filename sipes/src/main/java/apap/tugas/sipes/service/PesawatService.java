package apap.tugas.sipes.service;

import java.util.List;

import apap.tugas.sipes.model.PesawatModel;

public interface PesawatService {

	void addPesawat(PesawatModel pesawat);
   
	List<PesawatModel> getAllPesawat();
}
