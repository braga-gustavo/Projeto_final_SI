package bragagustavo.com.github.ProjetoFinalSI.controller;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.repository.ClienteRepository;
import bragagustavo.com.github.ProjetoFinalSI.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não localizado";

    @Autowired //Instancia o objeto na hora
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirCliente(@RequestBody @Valid Cliente cliente){
        clienteService.inserirCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}