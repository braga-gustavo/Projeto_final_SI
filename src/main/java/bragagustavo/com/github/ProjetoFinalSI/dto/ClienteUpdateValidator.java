package bragagustavo.com.github.ProjetoFinalSI.dto;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Cliente;
import bragagustavo.com.github.ProjetoFinalSI.exceptions.FieldMessage;
import bragagustavo.com.github.ProjetoFinalSI.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {


   @Autowired
   private ClienteRepository clienteRepository;

   @Autowired
   private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteDto clienteDto, ConstraintValidatorContext constraintValidatorContext) {

        Map<String, String> map =  (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente cliente = clienteRepository.findByEmail(clienteDto.getEmail());
        if (cliente != null){
            list.add(new FieldMessage("email", "Email ja cadastrado"));
        }

        for (FieldMessage e : list){
            constraintValidatorContext
                    .disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate((e.getMessage())).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();

    }
}
