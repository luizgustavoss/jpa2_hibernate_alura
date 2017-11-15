package org.acme.corp.modelo;

import javax.persistence.EntityManager;

import org.acme.corp.util.JPAUtil;
import org.junit.Assert;
import org.junit.Test;

public class ContaTest {

	@Test
	public void testarInclusaoDeConta() {

		Conta conta = new Conta();
		conta.setTitular("Luiz");
		conta.setBanco("Bradesco");
		conta.setAgencia("0203-05");
		conta.setNumero("124323-3");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	
	@Test
	public void testarBuscarConta() {

		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 1);
		Assert.assertNotNull(conta);
		
		conta.setTitular("João");
		
		em.getTransaction().commit();
		
		em.close();
	}

}
