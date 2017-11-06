package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;
import com.Ge.Te.appTeGe.appTeGe.modelo.Produto;
import com.Ge.Te.appTeGe.appTeGe.repositorio.FornecedorRepositorio;
import com.Ge.Te.appTeGe.appTeGe.repositorio.ProdutoRepositorio;

@Service("servicoProduto")
public class ServicoProdutoImplementacao implements ServicoProduto{
	
	@Autowired
	ProdutoRepositorio produtoRepositorio;
	
	@Autowired
	FornecedorRepositorio fornecedorRepositorio;
	
	@Transactional
	public Produto salvaProduto(Produto produto) {
		if(produto != null){
			produtoRepositorio.save(produto);
			return produto;
		}
		return produto;
	}
	
	@Transactional
	public void removeProduto(int id) {
		Produto produto = produtoRepositorio.findById(id);
		produto.setListFornecedor(null);
		produtoRepositorio.save(produto);
		produtoRepositorio.delete(produto);
	}
	
	@Transactional
	public void addFronecedor(int forId, int prodId) {
		Produto p = produtoRepositorio.findById(prodId);
		if(fornecedorRepositorio.findById(forId) != null){
			p.addFornecedor(fornecedorRepositorio.findById(forId));
			produtoRepositorio.save(p);
		}
	}
	
	@Transactional
	public Produto buscaPorId(int id) {
		return produtoRepositorio.findById(id);
	}
	
	@Transactional
	public Produto buscaPorNome(String nome) {
		return produtoRepositorio.findByDescricao(nome);
	}
	
	@Transactional
	public List<Produto> listaDeProduto() {
		List<Produto> listaProduto = new ArrayList<Produto>();
		for(Produto p: produtoRepositorio.findAll()){
			listaProduto.add(p);
		}
		return listaProduto;
	}
	
	@Transactional
	public List<Fornecedor> listaFornecedorProduto(int id) {
		Produto p = produtoRepositorio.findById(id);
		List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
		for(Fornecedor f: p.getListFornecedor()){
			listaFornecedor.add(f);
		}
		return listaFornecedor;
	}
	
	@Transactional
	public void addProduto(String descricao, Calendar validade, float precoVenda, float precoCompra, int quantidade) {
		Produto p = new Produto();
		p.setDescricao(descricao);
		p.setPrecoCompra(precoCompra);
		p.setPrecoVenda(precoVenda);
		p.setQuantidade(quantidade);
		p.setValidade(validade);
		produtoRepositorio.save(p);
	}
	
	@Transactional
	public List<Produto> verificaMinio() {
		List<Produto> listPro = new ArrayList<Produto>();
		for(Produto p: produtoRepositorio.findAll()){
			if(p.estaABaixo()){
				listPro.add(p);
			}
		}
		return listPro;
	}

}
