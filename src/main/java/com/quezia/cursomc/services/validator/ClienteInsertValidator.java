package com.quezia.cursomc.services.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.quezia.cursomc.domain.enums.TipoCliente;
import com.quezia.cursomc.dto.ClienteNewDTO;
import com.quezia.cursomc.resources.FieldMessage;

 
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> { 
 
    @Override 
    public void initialize(ClienteInsert ann) {    
    	
    }  
    
    @Override     
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) { 
        List<FieldMessage> list = new ArrayList<>(); 
        
        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()) ) {
        	
        	list.add(new FieldMessage("cpfOuCnpj","CPF Inválido"));
        	
        }
        
        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()) ) {
        	
        	list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido"));
        	
        }
        
        // inclua os testes aqui, inserindo erros na lista               
        for (FieldMessage e : list) {             
        	context.disableDefaultConstraintViolation();             
        	context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }         
        
        return list.isEmpty();     
        
    }
    
} 