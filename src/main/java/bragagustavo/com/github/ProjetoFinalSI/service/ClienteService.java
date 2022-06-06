package bragagustavo.com.github.ProjetoFinalSI.service;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.dto.ClienteDto;
import bragagustavo.com.github.ProjetoFinalSI.exceptions.ObjectNotFoundException;
import bragagustavo.com.github.ProjetoFinalSI.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente inserirCliente(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    public Cliente updateCliente(Cliente cliente){
        Cliente clienteToUpdate = findCliente(cliente.getId());
        updateData(clienteToUpdate, cliente);
        return clienteRepository.save(clienteToUpdate);
    }

    public void deleteCliente(Integer id){
        findCliente(id);
        try{
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new ObjectNotFoundException("Cliente não Existe para ser Deletado");
        }
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }


    public Cliente findCliente(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente Não Encontrado! Id: " + id + ", tipo: "
                + Cliente.class.getName()));
    }

    public void updateData(Cliente clienteToUpdate, Cliente cliente) {
        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setEmail(cliente.getEmail());
        clienteToUpdate.setTelefone(cliente.getTelefone());
    }

    public Cliente fromDto(ClienteDto clienteDto){
        return new Cliente(clienteDto.getId(),
                clienteDto.getNome(),
                clienteDto.getCpf(),
                clienteDto.getEmail(),
                clienteDto.getTelefone());
    }


}
