package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;
import com.Ge.Te.appTeGe.appTeGe.repositorio.PerfilRepositorio;

@Service("servicoPerfil")
public class ServicoPerfilImplementacao implements ServicoPerfil{
	
	@Autowired
	PerfilRepositorio perfilRepositorio;
	
	@Transactional
	public Perfil salvaPerfil(Perfil perfil) {
		if(perfil != null){
			perfilRepositorio.save(perfil);
			return perfil;
		}
		return null;
	}
	
	@Transactional
	public void removePerfil(Perfil perfil) {
		if(perfil != null){
			perfilRepositorio.delete(perfil);
		}
	}
	
	@Transactional
	public void addPrefil(String tipo) {
		if(tipo != null){
			Perfil p = new Perfil();
			p.setTipo(tipo);
			perfilRepositorio.save(p);
		}
	}
	
	@Transactional
	public Perfil buscaPorId(int id) {
		return perfilRepositorio.findById(id);
	}
	
	@Transactional
	public Perfil buscaPorTipo(String tipo){
		return perfilRepositorio.findByTipo(tipo);
	}
	
	@Transactional
	public List<Perfil> listaPerfis() {
		List<Perfil> listaPerfil = new ArrayList<Perfil>();
		for(Perfil p: perfilRepositorio.findAll()){
			listaPerfil.add(p);
		}
		return listaPerfil;
	}
}
