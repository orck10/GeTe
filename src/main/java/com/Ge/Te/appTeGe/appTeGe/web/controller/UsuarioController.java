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

import com.Ge.Te.appTeGe.appTeGe.modelo.Usuario;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoUsuario;
import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	ServicoUsuario servicoUsuario;
	
	@RequestMapping(value = "/getByNome")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Usuario> getByTipo(@RequestParam(value="nome", defaultValue="jose") String nome){
		Usuario u = servicoUsuario.buscaPorNome(nome);
		if(u == null){
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(u, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getById")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Usuario> getById(@RequestParam(value="id", defaultValue="1") int id){
		Usuario u = servicoUsuario.buscaPorId(id);
		if(u == null){
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(u, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/deleteById")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Usuario> deleteById(@RequestParam(value="id", defaultValue="1") int id){
		servicoUsuario.removeUsuario(id);
		Usuario u = servicoUsuario.buscaPorId(id);
		return new ResponseEntity<Usuario>(u, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getAll")
	@PreAuthorize("isAuthenticated()")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Collection<Usuario>> getAll() {
		return new ResponseEntity<Collection<Usuario>>(servicoUsuario.listaDeUsuarios(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@JsonView(View.Alternative.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario saveUsuario(@RequestBody Usuario u, HttpServletRequest request, HttpServletResponse response) {
		u = servicoUsuario.salvaUsuario(u);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/usuario/getById?id=" + u.getId());
		return u;
	}
}
