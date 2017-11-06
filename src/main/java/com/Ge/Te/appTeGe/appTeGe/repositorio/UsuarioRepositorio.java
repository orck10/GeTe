package com.Ge.Te.appTeGe.appTeGe.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.Ge.Te.appTeGe.appTeGe.modelo.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{
	public Usuario findById(int id);
	
	public Usuario findByNome(String nome);
}
