package com.quezia.cursomc.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quezia.cursomc.domain.Cidade;
import com.quezia.cursomc.domain.Estado;
import com.quezia.cursomc.dto.CidadeDTO;
import com.quezia.cursomc.dto.EstadoDTO;
import com.quezia.cursomc.repositories.CidadeRepository;
import com.quezia.cursomc.repositories.EstadoRepository;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoRepository serv;
	@Autowired
	private CidadeRepository cs;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> buscar(){
		List<Estado> list = serv.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cs.findCidades(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

}
