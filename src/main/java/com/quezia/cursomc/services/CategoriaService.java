package com.quezia.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.quezia.cursomc.domain.Categoria;
import com.quezia.cursomc.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository cr;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = cr.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria cat){
		cat.setId(null);
		return cr.save(cat);
	}
	
	public Categoria update(Categoria cat) throws ObjectNotFoundException{
		buscar(cat.getId());
		return cr.save(cat);
	}
	
	public void deletar(Integer id) {
		try {
			buscar(id);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cr.deleteById(id);
		}catch(DataIntegrityViolationException ex){
			throw new DataIntegrityExcpetion("Não é possível exluir um objeto associado a outro");
		}
		
	}
	
	public List<Categoria> listarTudo(){
		return cr.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cr.findAll(pageRequest);
	}
	
}
