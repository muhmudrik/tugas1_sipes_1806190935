package apap.tugas.sipes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tugas.sipes.model.PesawatModel;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long> {
    Optional<PesawatModel> findById(Long id);
}
