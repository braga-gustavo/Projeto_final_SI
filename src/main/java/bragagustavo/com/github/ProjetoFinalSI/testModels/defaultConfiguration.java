/* package bragagustavo.com.github.ProjetoFinalSI.testModels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("default")
public class defaultConfiguration {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean //Roda o codigo que vem depois, antes do sistema iniciar
    public boolean instantiateDatabase() throws ParseException{
        if (!"create".equals(strategy)){
            return false;
        }
        dbService.instantiateTestDatabase();
        return false;
    }
} */
