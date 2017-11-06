package com.Ge.Te.appTeGe.appTeGe.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;

public interface PerfilRepositorio extends CrudRepository<Perfil, Long>{
	
	public Perfil findById(int id);
	
	public Perfil findByTipo(String id);
}
