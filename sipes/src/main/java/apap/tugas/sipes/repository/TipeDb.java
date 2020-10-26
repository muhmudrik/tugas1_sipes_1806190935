package apap.tugas.sipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipes.model.TipeModel;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long>{
    
}
