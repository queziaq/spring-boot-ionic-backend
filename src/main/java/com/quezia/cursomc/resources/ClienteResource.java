package com.quezia.cursomc.resources;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quezia.cursomc.domain.Cliente;
import com.quezia.cursomc.dto.ClienteDTO;
import com.quezia.cursomc.dto.ClienteNewDTO;
import com.quezia.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService serv;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Cliente cli = serv.buscar(id);
		
		return ResponseEntity.ok().body(cli);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = serv.fromDTO(objDto);
		obj = serv.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> Update(@Valid @RequestBody ClienteDTO cli, @PathVariable Integer id) throws ObjectNotFoundException{
		Cliente obj = serv.fromDTO(cli);
		obj.setId(id);
		obj = serv.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> Delete(@PathVariable Integer id) {
		serv.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> FindAll() {
		List<Cliente> list = new ArrayList<>();
		list = serv.listarTudo();
		List<ClienteDTO> cDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cDTO);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> FindPage(
			@RequestParam(value="page" ,defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage" ,defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue ="nome")String orderBy, 
			@RequestParam(value="direction" ,defaultValue="ASC")String direction) {
		Page<Cliente> list = serv.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> cDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(cDTO);
	}

}
