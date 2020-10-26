package apap.tugas.sipes.repository;

import apap.tugas.sipes.model.PenerbanganModel;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PenerbanganDb extends JpaRepository<PenerbanganModel, Long>{
    
}
