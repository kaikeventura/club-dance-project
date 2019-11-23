package br.com.cng12.clubdance.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Table(name = "role")
@Entity
public class RoleEntity implements Serializable, GrantedAuthority {
	
	private static final long serialVersionUID = -2369360742923548934L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;
	
	private String nomeRole;
	
	@ManyToMany
	private List<UsuarioEntity> usuarios;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;
	}

}
