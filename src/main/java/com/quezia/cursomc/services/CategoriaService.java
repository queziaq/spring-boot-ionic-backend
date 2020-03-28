package com.quezia.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quezia.cursomc.domain.Categoria;
import com.quezia.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository cr;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = cr.findById(id);
		return obj.orElse(null); 
	}
}
