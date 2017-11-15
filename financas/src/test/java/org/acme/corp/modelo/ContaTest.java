package org.acme.corp.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import org.acme.corp.dao.ContaDAO;
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
		
		new ContaDAO(em).inserir(conta);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	
	@Test
	public void testarBuscarConta() {

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		Conta conta = new ContaDAO(em).recuperarPorId(1);
		
		Assert.assertNotNull(conta);
		
		conta.setTitular("João");
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	@Test
	public void testarRecuperarMovimentacoesDaConta() {

		
		EntityManager em = JPAUtil.getEntityManager();
		
		Conta conta = new ContaDAO(em).recuperarPorId(8);
		
		Assert.assertNotNull(conta);
		
		for (Movimentacao m : conta.getMovimentacoes()) {
			System.out.println(m.getDescricao());
		}
		
		em.close();
	}
	
	
	@Test
	public void testarRecuperarMovimentacoesDeTodasAsContas() {

		
		EntityManager em = JPAUtil.getEntityManager();
		
		List<Conta> contas = new ContaDAO(em).recuperarContasComMovimentacoes();
		
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
		
		Long total = new ContaDAO(em).recuperarQuantidadeDeMovimentacoesDaConta(conta);
		
		System.out.println("Total: " + total);
		
		em.close();
	}

}
