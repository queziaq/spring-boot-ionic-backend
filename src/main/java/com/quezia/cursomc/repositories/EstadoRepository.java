package com.quezia.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quezia.cursomc.domain.Estado;
import com.quezia.cursomc.dto.EstadoDTO;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
	
	List<EstadoDTO>findAllByOrderByNome();  

}
