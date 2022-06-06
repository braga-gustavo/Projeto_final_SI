package bragagustavo.com.github.ProjetoFinalSI.controller;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Servico;
import bragagustavo.com.github.ProjetoFinalSI.repository.ServicoRepository;
import bragagustavo.com.github.ProjetoFinalSI.service.ServicoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private static final String SERVICO_NAO_ENCONTRADO = "Servico não localizado";

    @Autowired //Instancia o objeto na hora
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoService servicoService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirServico(@RequestBody @Valid Servico servico) {
        servico = servicoService.inserirServico(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateServico(@PathVariable Integer id, @Valid @RequestBody Servico servico) throws Exception{
        servico.setId(id);
        servicoService.validaStatus(id);
        servicoService.updateServico(servico);
        return ResponseEntity.noContent().build();
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteServico(@PathVariable Integer id){
        servicoService.deleteServico(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Servico> findServico(@PathVariable  Integer id) {
        Servico servico = servicoService.findServico(id);
        return ResponseEntity.ok().body(servico);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Servico> findAll() {
        return servicoService.findAll();

    }

    @RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePrestador(@PathVariable Integer id, @RequestBody Servico servico){
        servico.setId(id);
        servicoService.updatePrestador(servico);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/finaliza/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> fechaPedido(@PathVariable Integer id, @RequestBody Servico servico ) throws Exception{
        servico.setId(id);
        servicoService.validaStatus(id);
        servicoService.fechaPedido(servico);
        return ResponseEntity.noContent().build();
    }


}
