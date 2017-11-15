package org.acme.corp.dao;

import javax.persistence.EntityManager;

import org.acme.corp.modelo.Cliente;

public class ClienteDAO {

	private EntityManager em;

	public ClienteDAO(EntityManager em){
		this.em = em;
	}
	
	
	public void inserir(Cliente cliente) {

		em.persist(cliente);
	}
}
