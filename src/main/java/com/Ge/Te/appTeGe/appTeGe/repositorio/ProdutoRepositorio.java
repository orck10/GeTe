package com.Ge.Te.appTeGe.appTeGe.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.Ge.Te.appTeGe.appTeGe.modelo.Produto;

public interface ProdutoRepositorio extends CrudRepository<Produto, Long>{
	
	public Produto findById(int id);
	
	public Produto findByDescricao (String descricao);
}
