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
    private PrestadorRepository prestadorRepository;

    @Transactional
    public Prestador inserirPrestador(Prestador prestador) {
        prestador.setId(null);
        Prestador prestadorSalvo = prestadorRepository.save(prestador);
        return prestadorSalvo;
    }

    public Prestador updatePrestador(Prestador prestador) {
        Prestador prestadorUpdate = findPrestador(prestador.getId());
        updateData(prestadorUpdate, prestador);
        return prestadorRepository.save(prestadorUpdate);
    }

    public void deletePrestador(Integer id) {
        findPrestador(id);
        try {
            prestadorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException("Prestador não Existe para ser Deletado");
        }
    }

    public List<Prestador> findAll() {
        return prestadorRepository.findAll();
    }


    public Prestador findPrestador(Integer id) {
        Optional<Prestador> prestador = prestadorRepository.findById(id);
        return prestador.orElseThrow(() -> new ObjectNotFoundException("Prestador Não Encontrado! Id: " + id + ", tipo: "
                + Prestador.class.getName()));
    }

    public void updateData(Prestador prestadorToUpdate, Prestador prestador) {
        prestadorToUpdate.setNome(prestador.getNome());
        prestadorToUpdate.setEmail(prestador.getEmail());
        prestadorToUpdate.setTelefone(prestador.getTelefone());
    }

    public Prestador fromDto(PrestadorDto prestadorDto) {
        return new Prestador(prestadorDto.getId(),
                prestadorDto.getNome(),
                prestadorDto.getCpf(),
                prestadorDto.getEmail(),
                prestadorDto.getTelefone());
    }


}