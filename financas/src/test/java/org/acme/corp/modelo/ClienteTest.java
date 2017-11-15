package org.acme.corp.modelo;

import javax.persistence.EntityManager;

import org.acme.corp.dao.ClienteDAO;
import org.acme.corp.util.JPAUtil;
import org.junit.Test;

public class ClienteTest {

	@Test
	public void test() {
		
		Cliente luiz = new Cliente();
		luiz.setNome("Luiz");
		luiz.setProfissao("Analista de Sistemas");
		luiz.setEndereco("Rua Herminio Blafa, 234");

		Conta conta = new Conta();
		conta.setId(2);
		
		luiz.setConta(conta);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		new ClienteDAO(em).inserir(luiz);
		em.getTransaction().commit();
		
		em.close();
		
	}

}
