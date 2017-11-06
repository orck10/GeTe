package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.List;

import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;
import com.Ge.Te.appTeGe.appTeGe.modelo.Usuario;

public interface ServicoUsuario {
	void addUsuario(String nome, String senha);
	
	Usuario salvaUsuario(Usuario usuario);
	
	void removeUsuario(int id);
	
	void addPrefil(int perfilId, int usuarioId);
	
	Usuario buscaPorId(int id);
	
	Usuario buscaPorNome(String nome);
	
	List<Usuario> listaDeUsuarios();
	
	List<Perfil> listaPerfisDeUsuario(int id);
}
