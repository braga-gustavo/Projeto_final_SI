package bragagustavo.com.github.ProjetoFinalSI.repository;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Programador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProgramadorRepository extends JpaRepository<Programador, Integer> {

    @Transactional(readOnly = true)
    Programador findByEmail(String email);
}
