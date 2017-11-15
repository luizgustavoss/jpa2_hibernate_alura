package org.acme.corp.modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	
	@Test
	public void testarRecuperarMovimentacoesDaConta() {

		
		EntityManager em = JPAUtil.getEntityManager();
		

		Conta conta = em.find(Conta.class, 8);
		Assert.assertNotNull(conta);
		
		for (Movimentacao m : conta.getMovimentacoes()) {
			System.out.println(m.getDescricao());
		}
		
		em.close();
	}
	
	
	@Test
	public void testarRecuperarMovimentacoesDeTodasAsContas() {

		
		EntityManager em = JPAUtil.getEntityManager();
		

		String jpql = " select distinct c from Conta c left join fetch c.movimentacoes ";
		
		List<Conta> contas = em.createQuery(jpql).getResultList();
		
		for (Conta c : contas) {
			System.out.println("\n\n");
			System.out.println("Banco: " + c.getBanco());
			System.out.println("Conta: " + c.getNumero());
			System.out.println("Agencia: " + c.getAgencia());
			
			for(Movimentacao m : c.getMovimentacoes()){
				
				System.out.println("    Descrição: " + m.getDescricao());
			}
		}
		
		em.close();
	}
	
	
	
	@Test
	public void testarRecuperacaoDeQuantidadeDeMovimentacoesPorConta() {

		
		EntityManager em = JPAUtil.getEntityManager();
		
		Conta conta = new Conta();
		conta.setId(8);

		String jpql = " select count(m) from Movimentacao m where m.conta = :pConta ";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		
		Long total = (Long) query.getSingleResult();
		
		System.out.println("Total: " + total);
		
		em.close();
	}

}
