package com.Ge.Te.appTeGe.appTeGe.web.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;
import com.Ge.Te.appTeGe.appTeGe.servico.ServicoPerfil;
import com.Ge.Te.appTeGe.appTeGe.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/perfil")
public class PerfilController {
	
	@Autowired
	ServicoPerfil servicoPerfil;

	public ServicoPerfil getServicoPerfil() {
		return servicoPerfil;
	}
	
	@RequestMapping(value = "/getByTipo")
	@JsonView(View.All.class)
	public ResponseEntity<Perfil> getByTipo(@RequestParam(value="tipo", defaultValue="simples") String tipo){
		Perfil p = servicoPerfil.buscaPorTipo(tipo);
		if(p == null){
			return new ResponseEntity<Perfil>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Perfil>(p, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Perfil> getById(@RequestParam(value="id", defaultValue="1") int id){
		Perfil p = servicoPerfil.buscaPorId(id);
		if(p == null){
			return new ResponseEntity<Perfil>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Perfil>(servicoPerfil.buscaPorId(id), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getAll")
	@JsonView(View.Alternative.class)
	public ResponseEntity<Collection<Perfil>> getAll() {
		return new ResponseEntity<Collection<Perfil>>(servicoPerfil.listaPerfis(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.Alternative.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Perfil savePerfil(@RequestBody Perfil p, HttpServletRequest request, HttpServletResponse response) {
		p = servicoPerfil.salvaPerfil(p);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/perfil/getById?id=" + p.getId());
		return p;
	}
}
