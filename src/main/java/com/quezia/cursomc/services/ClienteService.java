package com.quezia.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.dto.ClienteDTO;
import com.quezia.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cr;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = cr.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " 
		+ id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj){
		obj.setId(null);
		return cr.save(obj);
	}
	
	public Cliente update(Cliente obj) throws ObjectNotFoundException{
		Cliente objCli = buscar(obj.getId());
		updateData(objCli,obj);
		return cr.save(objCli);
		
	}
	
	public void deletar(Integer id) {
		try {
			buscar(id);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated objch block
			e.printStackTrace();
		}
		try {
			cr.deleteById(id);
		}catch(DataIntegrityViolationException ex){
			throw new DataIntegrityExcpetion("Não é possível exluir um objeto associado a outro");
		}
		
	}
	
	public List<Cliente> listarTudo(){
		return cr.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cr.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(),obj.getEmail(), null, null);
	}
	
	private void updateData(Cliente cliObj, Cliente obj) {
		cliObj.setNome(obj.getNome());
		cliObj.setEmail(obj.getEmail());
	}
}
