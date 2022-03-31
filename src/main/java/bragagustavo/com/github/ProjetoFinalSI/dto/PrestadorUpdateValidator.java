package bragagustavo.com.github.ProjetoFinalSI.dto;

import bragagustavo.com.github.ProjetoFinalSI.domains.entity.Prestador;
import bragagustavo.com.github.ProjetoFinalSI.exceptions.FieldMessage;
import bragagustavo.com.github.ProjetoFinalSI.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrestadorUpdateValidator implements ConstraintValidator<PrestadorUpdate, PrestadorDto> {


   @Autowired
   private PrestadorRepository prestadorRepository;

   @Autowired
   private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(PrestadorUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(PrestadorDto prestadorDto, ConstraintValidatorContext constraintValidatorContext) {

        Map<String, String> map =  (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Prestador prestador = prestadorRepository.findByEmail(prestadorDto.getEmail());
        if (prestador != null){
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
