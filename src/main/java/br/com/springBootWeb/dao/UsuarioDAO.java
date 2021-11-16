package br.com.springBootWeb.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.springBootWeb.beans.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
	public Usuario findByEmailAndSenha(String email, String senha);
}
