package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.UsuarioEntity;

public interface UsuarioService {

	void salvar(UsuarioEntity usuarioEntity);
	
	String encodeSenha(String senha);
	
	List<UsuarioEntity> listarUsuariosAtivos();
	
	List<UsuarioEntity> listarUsuariosInativos();
	
	UsuarioEntity buscarPorId(Long id);
	
	void alterarStatusParaAtivo(String status, Long id);
	
	void alterarStatusParaInativo(String status, Long id);
	
}
