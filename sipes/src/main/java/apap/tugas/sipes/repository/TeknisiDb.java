package apap.tugas.sipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipes.model.TeknisiModel;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long>{
    
}
