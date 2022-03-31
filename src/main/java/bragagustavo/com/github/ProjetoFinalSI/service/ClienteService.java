package bragagustavo.com.github.ProjetoFinalSI.service;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
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
    ClienteRepository clienteRepository;

    @Transactional
    public Cliente inserirCliente(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    public Cliente updateCliente(Cliente cliente) {

        Cliente clienteToUpdate = findCliente(cliente.getId());
        updateData(clienteToUpdate, cliente);
        return clienteRepository.save(clienteToUpdate);

    }

    public void deleteCliente(Integer id) {
        findCliente(id);

        try {

            clienteRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {

            throw new ObjectNotFoundException("Cliente não existe para ser deletado");

        }

    }

    public List<Cliente> findAll(){ //busca todos os clientes da base

        return clienteRepository.findAll();
    }

    public Cliente findCliente(Integer id) { // Gerador de erro
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: "
                + Cliente.class.getName()));
    }

    public void updateData(Cliente clienteToUpdate, Cliente cliente) {

        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setEmail(cliente.getEmail());
        clienteToUpdate.setTelefone(cliente.getTelefone());
    }
}
