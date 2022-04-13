package bragagustavo.com.github.ProjetoFinalSI.service;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Prestador;
import bragagustavo.com.github.ProjetoFinalSI.dto.PrestadorDto;
import bragagustavo.com.github.ProjetoFinalSI.exceptions.ObjectNotFoundException;
import bragagustavo.com.github.ProjetoFinalSI.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    PrestadorRepository prestadorRepository;

    @Transactional
    public Prestador inserirPrestador(Prestador prestador) {
        prestador.setId(null);
        Prestador prestadorSalvo = prestadorRepository.save(prestador);
        return prestadorSalvo;
    }

    public Prestador updatePrestador(Prestador prestador) {

        Prestador prestadorToUpdate = findPrestador(prestador.getId());
        updateData(prestadorToUpdate, prestador);
        return prestadorRepository.save(prestadorToUpdate);

    }

    public void deletePrestador(Integer id) {
        findPrestador(id);
        try {

            prestadorRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {

            throw new ObjectNotFoundException("Prestador nao existe não existe para ser deletado");

        }
    }

    public List<Prestador> findAll() { //busca todos os prestadores da base

        return prestadorRepository.findAll();
    }

    public Prestador findPrestador(Integer id) { // Gerador de erro
        Optional<Prestador> prestador = prestadorRepository.findById(id);
        return prestador.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: "
                + Prestador.class.getName()));
    }

    public void updateData(Prestador prestadorUpdate, Prestador prestador) {

        prestadorUpdate.setNome(prestador.getNome());
        prestadorUpdate.setEmail(prestador.getEmail());
        prestadorUpdate.setTelefone(prestador.getTelefone());
    }

    public Prestador fromDto(PrestadorDto prestadorDto) {
        return new Prestador(prestadorDto.getId(),
                prestadorDto.getNome(),
                prestadorDto.getCpf(),
                prestadorDto.getEmail(),
                prestadorDto.getTelefone());
    }
}
