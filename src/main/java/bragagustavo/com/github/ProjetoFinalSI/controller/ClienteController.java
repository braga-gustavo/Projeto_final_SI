package bragagustavo.com.github.ProjetoFinalSI.controller;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.dto.ClienteDto;
import bragagustavo.com.github.ProjetoFinalSI.repository.ClienteRepository;
import bragagustavo.com.github.ProjetoFinalSI.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não localizado";

    @Autowired //Instancia o objeto na hora
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirCliente(@RequestBody @Valid Cliente cliente) {
        clienteService.inserirCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDto clienteDto) {
        //Path Variable pega o valor que for enviado no caminho ("id" nesse caso)
        Cliente cliente = clienteService.fromDto(clienteDto);
        cliente.setId(id);
        clienteService.updateCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> findCliente(@PathVariable  Integer id) {
        Cliente cliente = clienteService.findCliente(id);
        return ResponseEntity.ok().body(cliente);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Cliente> findAll() {
        return clienteService.findAll();

    }




}
