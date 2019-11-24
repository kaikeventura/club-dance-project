package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.UsuarioDAO;
import br.com.cng12.clubdance.entity.UsuarioEntity;
import br.com.cng12.clubdance.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO dao;

	@Override
	public void salvar(UsuarioEntity usuarioEntity) {
				
		dao.save(usuarioEntity);
	}

	@Override
	public String encodeSenha(String senha) {
			
		String senhaEncode = new BCryptPasswordEncoder().encode(senha);
		
		return senhaEncode;
	}

	@Override
	public List<UsuarioEntity> listarUsuariosAtivos() {
		
		return dao.listarUsuariosAtivos();
	}

	@Override
	public List<UsuarioEntity> listarUsuariosInativos() {
		
		return dao.listarUsuariosInativos();
	}

	@Override
	public void alterarStatusParaAtivo(String status, Long id) {

		dao.alterarStatusParaAtivo(status, id);		
	}

	@Override
	public void alterarStatusParaInativo(String status, Long id) {

		dao.alterarStatusParaInativo(status, id);
	}

	@Override
	public UsuarioEntity buscarPorId(Long id) {

		return dao.buscarPorId(id);
	}
	
}