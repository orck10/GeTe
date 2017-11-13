package com.Ge.Te.appTeGe.appTeGe.web.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Ge.Te.appTeGe.appTeGe.modelo.Produto;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoProduto;
import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	ServicoProduto servicoProduto;
	
	@RequestMapping(value = "/getByNome")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.All.class)
	public ResponseEntity<Produto> getByTipo(@RequestParam(value="nome", defaultValue="caneta") String nome){
		Produto p = servicoProduto.buscaPorNome(nome);
		if(p == null){
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(p, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getById")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Produto> getById(@RequestParam(value="id", defaultValue="1") int id){
		Produto p = servicoProduto.buscaPorId(id);
		if(p == null){
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(servicoProduto.buscaPorId(id), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/deleteById")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Produto> deleteById(@RequestParam(value="id", defaultValue="1") int id){
		servicoProduto.removeProduto(id);
		Produto p = servicoProduto.buscaPorId(id);
		return new ResponseEntity<Produto>(p, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getAll")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Collection<Produto>> getAll() {
		return new ResponseEntity<Collection<Produto>>(servicoProduto.listaDeProduto(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAlerta")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Collection<Produto>> getAlerta() {
		return new ResponseEntity<Collection<Produto>>(servicoProduto.verificaMinio(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@JsonView(View.Alternative.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Produto saveProduto(@RequestBody Produto p, HttpServletRequest request, HttpServletResponse response) {
		p = servicoProduto.salvaProduto(p); 
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/produto/getById?id=" + p.getId());
		return p;
	}
}
