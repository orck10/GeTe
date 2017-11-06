package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.Calendar;
import java.util.List;

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;
import com.Ge.Te.appTeGe.appTeGe.modelo.Produto;


public interface ServicoProduto {
	
	void addProduto(String descricao, Calendar validade, float precoVenda, float precoCompra, int quantidade);
	
	Produto salvaProduto(Produto produto);
	
	List<Produto> verificaMinio();
	
	void removeProduto(int id);
	
	void addFronecedor(int id, int prodId);
	
	Produto buscaPorId(int id);
	
	Produto buscaPorNome(String nome);
	
	List<Produto> listaDeProduto();
	
	List<Fornecedor> listaFornecedorProduto(int id);
}
