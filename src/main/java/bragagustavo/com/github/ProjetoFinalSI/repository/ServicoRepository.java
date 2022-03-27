package bragagustavo.com.github.ProjetoFinalSI.repository;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Programador;
import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Servicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servicos, Integer> {

}
