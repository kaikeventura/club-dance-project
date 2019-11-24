package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.entity.UsuarioEntity;

public interface UsuarioService {

	void salvar(UsuarioEntity usuarioEntity);
	
	List<ProdutoEntity> listar();
	
	String encodeSenha(String senha);
	
}
