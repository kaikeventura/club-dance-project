package br.com.cng12.clubdance.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.dao.RoleDAO;
import br.com.cng12.clubdance.entity.RoleEntity;
import br.com.cng12.clubdance.entity.UsuarioEntity;
import br.com.cng12.clubdance.service.impl.UsuarioServiceImpl;
import br.com.cng12.clubdance.utils.dto.Role;

@Controller
public class UsuarioController {

	private static final String SALVAR_USUARIO = "/administracao/salvar-usuario";
	private static final String EDITAR_USUARIO_ATIVO = "/administracao/editar-usuario-ativo/{id}";
	private static final String EDITAR_USUARIO_INATIVO = "/administracao/editar-usuario-inativo/{id}";
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@PostMapping(SALVAR_USUARIO)
	public String registrarUsuario(@Valid UsuarioEntity usuarioEntity, Role role, RedirectAttributes attr) {
		
		switch (role.getNomeRole()) {
		
			case "ADMINISTRADOR":
				RoleEntity roleEntity0 = roleDAO.findByNomeRole("ROLE_ADMIN");
				List<RoleEntity> roles0 = Arrays.asList(roleEntity0);
				usuarioEntity.setRoles(roles0);
				usuarioEntity.setSenha(usuarioService.encodeSenha(usuarioEntity.getSenha()));
				
				usuarioService.salvar(usuarioEntity);
				
				attr.addFlashAttribute("success", "Usuário registrado com sucesso.");
				return "redirect:/administracao/inicio";
				
			case "RECEPCIONISTA":
				RoleEntity roleEntity1 = roleDAO.findByNomeRole("ROLE_RECEP");
				List<RoleEntity> roles1 = Arrays.asList(roleEntity1);
				usuarioEntity.setRoles(roles1);
				usuarioEntity.setSenha(usuarioService.encodeSenha(usuarioEntity.getSenha()));
				
				usuarioService.salvar(usuarioEntity);
				
				attr.addFlashAttribute("success", "Usuário registrado com sucesso.");
				return "redirect:/administracao/inicio";
				
			case "VENDEDOR":
				RoleEntity roleEntity2 = roleDAO.findByNomeRole("ROLE_BAR");
				List<RoleEntity> roles2 = Arrays.asList(roleEntity2);
				usuarioEntity.setRoles(roles2);
				usuarioEntity.setSenha(usuarioService.encodeSenha(usuarioEntity.getSenha()));
				
				usuarioService.salvar(usuarioEntity);
				
				attr.addFlashAttribute("success", "Usuário registrado com sucesso.");
				return "redirect:/administracao/inicio";
				
			case "CAIXA":
				RoleEntity roleEntity3 = roleDAO.findByNomeRole("ROLE_CAIXA");
				List<RoleEntity> roles3 = Arrays.asList(roleEntity3);
				usuarioEntity.setRoles(roles3);
				usuarioEntity.setSenha(usuarioService.encodeSenha(usuarioEntity.getSenha()));
				
				usuarioService.salvar(usuarioEntity);
				
				attr.addFlashAttribute("success", "Usuário registrado com sucesso.");
				return "redirect:/administracao/inicio";
				
			case "ESTOQUISTA":
				RoleEntity roleEntity4 = roleDAO.findByNomeRole("ROLE_ESTOQ");
				List<RoleEntity> roles4 = Arrays.asList(roleEntity4);
				usuarioEntity.setRoles(roles4);
				usuarioEntity.setSenha(usuarioService.encodeSenha(usuarioEntity.getSenha()));
				
				usuarioService.salvar(usuarioEntity);
				
				attr.addFlashAttribute("success", "Usuário registrado com sucesso.");
				return "redirect:/administracao/inicio";
		}
		
		return "redirect:/administracao/inicio";
	}
	
	@GetMapping(EDITAR_USUARIO_ATIVO)
	public String editarUsuarioAtivo(@PathVariable("id") Long id, RedirectAttributes attr) {
	
		UsuarioEntity usuario = usuarioService.buscarPorId(id);
		usuarioService.alterarStatusParaInativo("INATIVO", usuario.getUsuario_id());
		
		attr.addFlashAttribute("success", "Usuário editado com sucesso.");
		return "redirect:/administracao/inicio";
	}
	
	@GetMapping(EDITAR_USUARIO_INATIVO)
	public String editarUsuarioInativo(@PathVariable("id") Long id, RedirectAttributes attr) {
	
		UsuarioEntity usuario = usuarioService.buscarPorId(id);
		usuarioService.alterarStatusParaAtivo("ATIVO", usuario.getUsuario_id());
		
		attr.addFlashAttribute("success", "Usuário editado com sucesso.");
		return "redirect:/administracao/inicio";
	}
	
}
