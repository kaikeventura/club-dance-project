package br.com.cng12.clubdance.utils.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;

@Component
public class ComandaProdutoComponent {

	@Autowired
	private ComandaServiceImpl comandaService;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private EventoServiceImpl eventoService;

	public ComandaProdutoEntity novaComandaProdutoEntity(Long idCliente, Long idEvento, ProdutoEntity produtoEntity,
			int qtde) {

		ComandaProdutoEntity comandaProdutoEntity = new ComandaProdutoEntity();
		comandaProdutoEntity.setComandaEntity(retornaComanda(idCliente, idEvento));
		comandaProdutoEntity.setProdutoEntity(produtoEntity);
		comandaProdutoEntity.setValorUnitario(produtoEntity.getPreco());
		comandaProdutoEntity.setQtde(qtde);
		comandaProdutoEntity.setValorTotal(comandaProdutoEntity.getValorUnitario() * comandaProdutoEntity.getQtde());

		return comandaProdutoEntity;
	}

	public ComandaEntity retornaComanda(Long idCliente, Long idEvento) {

		ClienteEntity clienteEntity = clienteService.buscarPorId(idCliente);
		EventoEntity eventoEntity = eventoService.buscarPorId(idEvento);
		ComandaEntity comandaEntity = comandaService.buscarPorIdClienteIdEvento(clienteEntity, eventoEntity);

		return comandaEntity;
	}

}
