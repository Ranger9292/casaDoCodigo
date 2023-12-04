package br.com.casadocodigo.loja.beans;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Compra;

public class CompraDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Compra compra) {
		manager.persist(compra);
	}
	
	public Compra buscaPorUuid(String uuid) {
		return manager
			.createQuery("select c form Compra c where c.uuid = :uuid", Compra.class)
			.setParameter("uuid", uuid).getSingleResult();
	}
}
