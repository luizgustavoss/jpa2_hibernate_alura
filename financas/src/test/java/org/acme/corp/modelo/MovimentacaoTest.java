package org.acme.corp.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.acme.corp.util.JPAUtil;
import org.junit.Test;

public class MovimentacaoTest {

	@Test
	public void testarConsultaDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		String jpql = " select m from Movimentacao m where m.conta.id = :pId and m.tipo = :pTipo order by m.valor desc ";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", 7);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
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

		String jpql = " select m from Movimentacao m join m.categorias c where c.id = :pId order by m.valor desc ";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", 2);
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
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

		Movimentacao movimentacao = em.find(Movimentacao.class, 2);
		
		System.out.println("Titular da Conta: " + movimentacao.getConta().getTitular());
		
		em.close();
	}
	
	
	
	@Test
	public void testarRecuperacaoSomaDeValoresDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		String jpql = " select sum(m.valor) from Movimentacao m ";
		Query query = em.createQuery(jpql);
		
		BigDecimal soma = (BigDecimal) query.getSingleResult();
		
		System.out.println("A soma é: " + soma);
		
		em.getTransaction().commit();

		em.close();
	}
	
	
	
	@Test
	public void testarRecuperacaoMediaDeValoresDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		String jpql = " select avg(m.valor) from Movimentacao m ";
		Query query = em.createQuery(jpql);
		
		Double media = (Double) query.getSingleResult();
		
		System.out.println("A média é: " + media);
		
		em.getTransaction().commit();

		em.close();
	}
	
	
	@Test
	public void testarRecuperacaoDeValorMaximoDeMovimentacao() {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		String jpql = " select max(m.valor) from Movimentacao m ";
		Query query = em.createQuery(jpql);
		
		BigDecimal maximo = (BigDecimal) query.getSingleResult();
		
		System.out.println("O valor máximo é: " + maximo);
		
		em.getTransaction().commit();

		em.close();
	}
	
	
	
	@Test
	public void testarRecuperacaoMediaDeValoresDeMovimentacaoAgrupadosPorData() {

		EntityManager em = JPAUtil.getEntityManager();

		String jpql = " select avg(m.valor) from Movimentacao m group by truncate_date(m.data) ";
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		
		List<Double> medias = query.getResultList();
		
		for (Double media : medias) {
			System.out.println(media);
		}

		em.close();
	}

}
