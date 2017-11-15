package org.acme.corp.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.acme.corp.dao.MovimentacaoDAO;
import org.acme.corp.util.JPAUtil;
import org.junit.Test;

public class MovimentacaoTest {

	@Test
	public void testarConsultaDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		List<Movimentacao> movimentacoes = new MovimentacaoDAO(em)
				.consultaDeMovimentacao(7, TipoMovimentacao.SAIDA);
		
		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println(movimentacao.getDescricao());
			System.out.println(movimentacao.getConta().getId());
		}
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	@Test
	public void testarConsultaDeMovimentacaoPorCategoria() {

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		List<Movimentacao> movimentacoes = new MovimentacaoDAO(em).consultaDeMovimentacaoPorCategoria(2);
		
		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println(movimentacao.getDescricao());
			System.out.println(movimentacao.getConta().getId());
		}
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	@Test
	public void testarRecuperarContaDaMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();
		
		Movimentacao movimentacao = new MovimentacaoDAO(em).recuperarContaDaMovimentacao(2);
		
		System.out.println("Titular da Conta: " + movimentacao.getConta().getTitular());
		
		em.close();
	}
	
	
	
	@Test
	public void testarRecuperacaoSomaDeValoresDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		BigDecimal soma = new MovimentacaoDAO(em).recuperacaoSomaDeValoresDeMovimentacao();
		
		System.out.println("A soma é: " + soma);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	
	@Test
	public void testarRecuperacaoMediaDeValoresDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		Double media = new MovimentacaoDAO(em).recuperacaoMediaDeValoresDeMovimentacao();
		
		System.out.println("A média é: " + media);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	@Test
	public void testarRecuperacaoDeValorMaximoDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		BigDecimal maximo = new MovimentacaoDAO(em).recuperacaoDeValorMaximoDeMovimentacao();
		
		System.out.println("O valor máximo é: " + maximo);
		
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	@Test
	public void testarRecuperacaoMediaDeValoresDeMovimentacaoAgrupadosPorData() {

		EntityManager em = JPAUtil.getEntityManager();
		
		List<Double> medias = new MovimentacaoDAO(em).recuperacaoMediaDeValoresDeMovimentacaoAgrupadosPorData();
		
		for (Double media : medias) {
			System.out.println(media);
		}
		
		em.close();
	}

}
