package bragagustavo.com.github.ProjetoFinalSI.service;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Servico;
import bragagustavo.com.github.ProjetoFinalSI.domains.enums.StatusServico;
import bragagustavo.com.github.ProjetoFinalSI.exceptions.ObjectNotFoundException;
import bragagustavo.com.github.ProjetoFinalSI.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Servico inserirServico(Servico servico) {
        servico.setId(null);
        servico.setCliente(clienteService.findCliente(servico.getCliente().getId()));
        servico.setTituloServico(servico.getTituloServico());
        servico.setDescricaoServico(servico.getDescricaoServico());
        servico.setDataServico(LocalDateTime.now());
        servico.setOrcamentoServico(servico.getOrcamentoServico());
        servico.setStatusServico(servico.getStatusServico());
        servico = servicoRepository.save(servico);
        return servico;
    }


    public Servico updateServico(Servico servico) {
        Servico servicoToUpdate = findServico(servico.getId());
        updateData(servicoToUpdate, servico);
        return servicoRepository.save(servicoToUpdate);
    }

    public void deleteServico(Integer id) {
        findServico(id);
        try {
            servicoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectNotFoundException("Servico não Existe para ser Deletado");
        }
    }

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }


    public Servico findServico(Integer id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        return servico.orElseThrow(() -> new ObjectNotFoundException("Serviço Não Encontrado! Id: " + id + ", tipo: "
                + Servico.class.getName()));
    }

    public void updateData(Servico servicoToUpdate, Servico servico) {
        servicoToUpdate.setTituloServico(servico.getTituloServico());
        servicoToUpdate.setDescricaoServico(servico.getDescricaoServico());
        servicoToUpdate.setOrcamentoServico(servico.getOrcamentoServico());
    }

    public Servico updatePrestador(Servico servico) {
        Servico servicoToUpdate = findServico(servico.getId());
        updateDataPrestador(servicoToUpdate, servico);
        updateStatusServico(servicoToUpdate);
        return servicoRepository.save(servicoToUpdate);
    }

    public Servico fechaPedido(Servico servico) {
        Servico servicoToUpdate = findServico(servico.getId());
        finalizaPedido(servicoToUpdate);
        updateAnotacao(servicoToUpdate, servico);
        return servicoRepository.save(servicoToUpdate);
    }

    public void updateDataPrestador(Servico servicoToUpdate, Servico servico) {
        servicoToUpdate.setPrestador(servico.getPrestador());
    }

    public void updateStatusServico(Servico servicoToUpdate) {
        servicoToUpdate.setStatusServico(StatusServico.EM_ANDAMENTO);
    }

    public void finalizaPedido(Servico servicoToUpdate) {
        servicoToUpdate.setStatusServico(StatusServico.FECHADO);
    }

    public void updateAnotacao(Servico servicoToUpdate, Servico servico) {
        servicoToUpdate.setAnotacao(servico.getAnotacao());
    }

    public void validaStatus(Integer id) throws Exception {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.get().getStatusServico()==StatusServico.FECHADO) {
            throw new Exception("Cliente já consta com Status Fechado! Não pode ser alterado!");
        }
    }


}