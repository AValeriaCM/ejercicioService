package com.linea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.linea.entity.Usuario;
import com.linea.exception.BusinessLogicException;
import com.linea.exception.ModelNotFoundException;
import com.linea.exception.ObjectItsCreatedException;
import com.linea.repo.IUsuarioRepo;
import com.linea.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService{

	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void guardar(Usuario entidad) {
		
		if (repo.findByDocumento(entidad.getDocumento()) != null) {
			throw new ObjectItsCreatedException(entidad.getNombre() + "ya existe, ingrese otro");
		} else {
			entidad.setClave(encoder.encode(entidad.getClave()));
			repo.save(entidad);
		}
	}

	@Override
	public Usuario retornarId(Integer id) {
		Usuario usuario = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("-El usuario no existe"));
				return usuario;
	}

	@Override
	public Page<Usuario> listarPaginado(Integer page, Integer size, Boolean lazy) {
		Page<Usuario> listaUsuario = repo.findAll(PageRequest.of(page, size));
		return listaUsuario;
	}

	@Override
	public void editar(Usuario entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
		Usuario usuario = repo.findByNick(nick);
		if(usuario == null)
			throw new ModelNotFoundException("----Nick o password incorecto");
		if(usuario.isEstado() == false)
			throw new BusinessLogicException("----Usuario deshabilitado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);		
		return ud;
	}

}
