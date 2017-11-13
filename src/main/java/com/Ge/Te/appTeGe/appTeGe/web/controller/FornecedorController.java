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

import com.Ge.Te.appTeGe.appTeGe.modelo.Fornecedor;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoFornecedor;
import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorController {
	
	@Autowired
	ServicoFornecedor servicoFornecedor;

	public ServicoFornecedor getServicoFornecedor() {
		return servicoFornecedor;
	}
	
	@RequestMapping(value = "/getByName")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.All.class)
	public ResponseEntity<Fornecedor> getByName(@RequestParam(value="name", defaultValue="Tabajara LTDA") String name){
		Fornecedor f = servicoFornecedor.buscaPorNome(name);
		if(f == null){
			return new ResponseEntity<Fornecedor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Fornecedor>(f, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getById")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Fornecedor> getById(@RequestParam(value="id", defaultValue="1") int id){
		Fornecedor f = servicoFornecedor.buscaPorId(id);
		if(f == null){
			return new ResponseEntity<Fornecedor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Fornecedor>(servicoFornecedor.buscaPorId(id), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getAll")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Collection<Fornecedor>> getAll() {
		return new ResponseEntity<Collection<Fornecedor>>(servicoFornecedor.listaFornecedor(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@JsonView(View.Alternative.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Fornecedor saveFornecedor(@RequestBody Fornecedor f, HttpServletRequest request, HttpServletResponse response) {
		f = servicoFornecedor.salvaFornecedor(f);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/fornecedor/getById?id=" + f.getCnpj());
		return f;
	}
	
}