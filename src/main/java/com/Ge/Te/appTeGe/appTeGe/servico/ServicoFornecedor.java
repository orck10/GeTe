package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.List;

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;

public interface ServicoFornecedor {
	Fornecedor salvaFornecedor(Fornecedor fornecedor);
	
	void removeFornecedor(Fornecedor fornecedor);
	
	void addFornecedor(String cnpj , String nome);
	
	Fornecedor buscaPorId(int id);
	
	Fornecedor buscaPorNome(String nome);
	
	List<Fornecedor> listaFornecedor();
}
