package bragagustavo.com.github.ProjetoFinalSI.repository;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

    @Transactional(readOnly = true)
    Prestador findByEmail(String email);
}
