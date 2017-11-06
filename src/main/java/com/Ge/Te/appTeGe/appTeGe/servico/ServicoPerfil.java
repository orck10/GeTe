package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.List;

import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;

public interface ServicoPerfil {

	Perfil salvaPerfil(Perfil perfil);
	
	void removePerfil(Perfil perfil);
	
	void addPrefil(String tipo);
	
	Perfil buscaPorId(int id);
	
	Perfil buscaPorTipo(String tipo);
	
	List<Perfil> listaPerfis();
}
