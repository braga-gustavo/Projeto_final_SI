package bragagustavo.com.github.ProjetoFinalSI.testModels;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Prestador;
import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Servico;
import bragagustavo.com.github.ProjetoFinalSI.domains.enums.StatusServico;
import bragagustavo.com.github.ProjetoFinalSI.repository.ClienteRepository;
import bragagustavo.com.github.ProjetoFinalSI.repository.PrestadorRepository;
import bragagustavo.com.github.ProjetoFinalSI.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository  clienteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private ServicoRepository servicoRepository;


    public void instantiateTestDatabase() throws ParseException {

        Cliente c1 = new Cliente(null, "Pedro solicitante","32271188075", "pedroteste@gmail.com", "123456789", "111111");
        Cliente c2 = new Cliente(null, "Gustavo solicitante","34933620059", "gustavoteste@gmail.com", "123456789", "222222");
        Cliente c3 = new Cliente(null, "Leo solicitante","63954546094", "leoteste@gmail.com", "123456789", "333333");

        Prestador p1 = new Prestador(null, "Prestador Bozena", "23798792062", "bozenateste@gmail.com", "123456789", "444444");
        Prestador p2 = new Prestador(null, "Prestador Judite", "36852210006", "juditeteste@gmail.com", "123456789", "555555");
        Prestador p3 = new Prestador(null, "Prestador Jurema", "51582818070", "juremateste@gmail.com", "123456789", "666666");

        Servico s1 = new Servico(null, c1, p1, "Criação de um APP", "App de Escola com Notas",
                LocalDateTime.now(), 15000.00 ,StatusServico.ABERTO);

        Servico s2 = new Servico(null, c2, p2, "Manutenção de Site", "Site de Banco",
                LocalDateTime.now(), 2500.00, StatusServico.ABERTO);

        Servico s3 = new Servico(null, c3, p3, "Gerenciar de Desktop", "Sistema de Cartórios",
                LocalDateTime.now(), 3000.00, StatusServico.ABERTO);

        Servico s4 = new Servico(null, c2, p2, "Criação de Site", "Criar site de e-commerc",
                LocalDateTime.now(), 10000.00, StatusServico.ABERTO);

        Servico s5 = new Servico(null, c3, p1, "Criação de App", "Criar um App de Alimentação",
                LocalDateTime.now(), 25000.00, StatusServico.ABERTO);

        clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
        prestadorRepository.saveAll(Arrays.asList(p1, p2, p3));
        servicoRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

    }


}
