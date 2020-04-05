package com.quezia.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.quezia.cursomc.domain.Categoria;
import com.quezia.cursomc.domain.Produto;
import com.quezia.cursomc.repositories.CategoriaRepository;
import com.quezia.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository cr;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto buscar(Integer id) throws ObjectNotFoundException {
		Optional<Produto> obj = cr.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias =  categoriaRepo.findAllById(ids);
		return cr.findDistinctByNomeContainingAndCategoriasIn(nome, categorias,pageRequest);
	}
	
	
}
