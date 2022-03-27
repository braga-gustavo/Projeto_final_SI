package bragagustavo.com.github.ProjetoFinalSI.service;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public Cliente inserirCliente(Cliente cliente){
        cliente.setId(null);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

}
