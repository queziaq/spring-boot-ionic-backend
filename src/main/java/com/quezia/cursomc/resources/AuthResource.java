package com.quezia.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quezia.cursomc.dto.EmailDTO;
import com.quezia.cursomc.security.JWTUtil;
import com.quezia.cursomc.security.UserSS;
import com.quezia.cursomc.services.AuthService;
import com.quezia.cursomc.services.UserService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value ="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) { 
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("access-control-expose-headers", "Authorization");
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build(); 
	}
	
	@RequestMapping(value="/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO obj) throws ObjectNotFoundException { 
		service.sendNewPassword(obj.getEmail());
		return ResponseEntity.noContent().build(); 
	}

}
