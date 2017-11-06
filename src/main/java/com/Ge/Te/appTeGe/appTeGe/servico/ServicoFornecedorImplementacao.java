package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;
import com.Ge.Te.appTeGe.appTeGe.repositorio.FornecedorRepositorio;

@Service("servicoFornecedor")
public class ServicoFornecedorImplementacao implements ServicoFornecedor{
	
	@Autowired
	FornecedorRepositorio fornecedorRepositorio;
	
	@Transactional
	public Fornecedor salvaFornecedor(Fornecedor fornecedor) {
		if(fornecedor != null){
			return fornecedorRepositorio.save(fornecedor);
		}
		return null;
	}
	
	@Transactional
	public void removeFornecedor(Fornecedor fornecedor) {
		if(fornecedor != null){
			fornecedorRepositorio.delete(fornecedor);
		}
	}
	
	@Transactional
	public void addFornecedor(String cnpj, String nome){
		if(cnpj != null && nome != null){
			Fornecedor f = new Fornecedor();
			f.setCnpj(cnpj);
			f.setNome(nome);
			fornecedorRepositorio.save(f);
		}
	}
	
	@Transactional
	public Fornecedor buscaPorId(int id) {
		return fornecedorRepositorio.findById(id);
	}
	
	@Transactional
	public Fornecedor buscaPorNome(String nome) {
		return fornecedorRepositorio.findByNome(nome);
	}
	
	@Transactional
	public List<Fornecedor> listaFornecedor() {
		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
		for(Fornecedor f: fornecedorRepositorio.findAll()){
			listaFornecedor.add(f);
		}
		return listaFornecedor;
	}
}
