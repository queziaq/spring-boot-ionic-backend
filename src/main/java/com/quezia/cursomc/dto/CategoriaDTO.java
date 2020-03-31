package com.quezia.cursomc.dto;

import java.io.Serializable;

import com.quezia.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String Nome;
	
	public CategoriaDTO(){
		
	}
	
	public CategoriaDTO(Categoria obj){
		Id =obj.getId();
		Nome = obj.getNome();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
}
