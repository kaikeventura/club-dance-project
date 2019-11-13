select * from cliente;

UPDATE cliente SET cpf = 'x', nome = 'y', tipo_ingresso = 'z' WHERE id = 87;

select * from cliente c WHERE c.cpf = 'TESTE' and c.evento_entity_id = 2;

select * from cliente c WHERE c.evento_entity_id = 2;

SELECT cliente.*
FROM comanda
INNER JOIN cliente ON cliente.id = comanda.cliente_entity_id
where comanda.status = 'FECHADO';