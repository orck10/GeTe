package com.Ge.Te.appTeGe.appTeGe.servico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ge.Te.appTeGe.appTeGe.modelo.Perfil;
import com.Ge.Te.appTeGe.appTeGe.modelo.Usuario;
import com.Ge.Te.appTeGe.appTeGe.repositorio.PerfilRepositorio;
import com.Ge.Te.appTeGe.appTeGe.repositorio.UsuarioRepositorio;

@Service("servicoUsuario")
public class ServicoUsuarioImplementacao implements ServicoUsuario{
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	PerfilRepositorio perfilRepositorio;
	
	@Transactional
	public void addUsuario(String nome, String senha) {
		if(nome != null &&  senha != null){
			Usuario u = new Usuario();
			u.setNome(nome);
			u.setSenha(senha);
			usuarioRepositorio.save(u);
		}
		
	}
	
	@Transactional
	public Usuario salvaUsuario(Usuario usuario) {
		if(usuario != null){
			usuarioRepositorio.save(usuario);
			return usuario;
		}
		return null;
	}
	
	@Transactional
	public void addPrefil(int perfilId, int usuarioId) {
		Usuario u = usuarioRepositorio.findById(usuarioId);
		if(perfilRepositorio.findById(perfilId) != null){
			u.addPerfil(perfilRepositorio.findById(perfilId));
			usuarioRepositorio.save(u);
		}
	}
	
	@Transactional
	public Usuario buscaPorId(int id) {
		return usuarioRepositorio.findById(id);
	}
	
	@Transactional
	public Usuario buscaPorNome(String nome) {
		return usuarioRepositorio.findByNome(nome);
	}
	
	@Transactional
	public List<Usuario> listaDeUsuarios() {
		List<Usuario> listaUsuraio = new ArrayList<Usuario>();
		for(Usuario u: usuarioRepositorio.findAll()){
			listaUsuraio.add(u);
		}
		return listaUsuraio;
	}
	
	@Transactional
	public List<Perfil> listaPerfisDeUsuario(int id) {
		List<Perfil> listaPerfil = new ArrayList<Perfil>();
		for(Perfil p: usuarioRepositorio.findById(id).getPerfis()){
			listaPerfil.add(p);
		}
		return null;
	}

	public void removeUsuario(int id) {
		Usuario u = usuarioRepositorio.findById(id);
		u.setPerfis(null);
		usuarioRepositorio.save(u);
		usuarioRepositorio.delete(u);
	}
}
