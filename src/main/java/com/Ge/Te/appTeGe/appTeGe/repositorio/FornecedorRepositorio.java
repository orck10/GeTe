package com.Ge.Te.appTeGe.appTeGe.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;


public interface FornecedorRepositorio extends CrudRepository<Fornecedor, Long>{
	
	public Fornecedor findById(int id);
	
	public Fornecedor findByCnpj(String cnpj);
	
	public Fornecedor findByNome(String nome);
}
