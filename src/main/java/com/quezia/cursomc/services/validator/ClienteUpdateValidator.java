package com.quezia.cursomc.services.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.dto.ClienteDTO;
import com.quezia.cursomc.repositories.ClienteRepository;
import com.quezia.cursomc.resources.FieldMessage;

 
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> { 
 
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository ci;
	
    @Override 
    public void initialize(ClienteUpdate ann) {    
    	
    }  
    
    @Override     
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) { 
       
    	@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    	Integer uriID = Integer.parseInt(map.get("id"));
    	
    	List<FieldMessage> list = new ArrayList<>(); 
             
        Cliente cl = ci.findByEmail(objDto.getEmail());
        
        if(cl!= null && !cl.getId().equals(uriID)) {
        	
        	list.add(new FieldMessage("email","Email Já Existente"));
        }
        
        // inclua os testes aqui, inserindo erros na lista               
        for (FieldMessage e : list) {             
        	context.disableDefaultConstraintViolation();             
        	context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }         
        
        return list.isEmpty();     
        
    }

    
} 