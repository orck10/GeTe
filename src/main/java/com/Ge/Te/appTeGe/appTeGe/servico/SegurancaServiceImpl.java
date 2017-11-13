package com.Ge.Te.appTeGe.appTeGe.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ge.Te.appTeGe.appTeGe.modelo.Usuario;
import com.Ge.Te.appTeGe.appTeGe.repositorio.UsuarioRepositorio;

@Service("segurancaService")
public class SegurancaServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	public void setUsuarioRepository(UsuarioRepositorio usuarioDao) {
	this.usuarioRepository = usuarioDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNome(userName);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return usuario;
	}
}