package apap.tugas.sipes.service;

import java.util.List;

import apap.tugas.sipes.model.PesawatModel;

public interface PesawatService {

	void addPesawat(PesawatModel pesawat);

	void updatePesawat(PesawatModel pesawat);
   
	void hapusPesawat(PesawatModel pesawat);
	
	List<PesawatModel> getAllPesawat();

	PesawatModel getPesawatById(Long id);

	List<PesawatModel> filterIdPenerbangan(List<PesawatModel> listPesawat, Long idPenerbangan);

	List<PesawatModel> filterTipe(List<PesawatModel> listPesawat, Long idTipe);
	
	List<PesawatModel> filterTeknisi(List<PesawatModel> listPesawat, Long idTeknisi);

}
