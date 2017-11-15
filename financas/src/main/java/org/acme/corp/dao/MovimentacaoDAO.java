package org.acme.corp.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.acme.corp.modelo.Movimentacao;
import org.acme.corp.modelo.TipoMovimentacao;
import org.junit.Test;

public class MovimentacaoDAO {
	
	private EntityManager em;

	public MovimentacaoDAO(EntityManager em){
		this.em = em;
	}
	
	
	public List<Movimentacao> consultaDeMovimentacao(Integer pId, TipoMovimentacao tipo) {


		String jpql = " select m from Movimentacao m where m.conta.id = :pId and m.tipo = :pTipo order by m.valor desc ";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", pId);
		query.setParameter("pTipo", tipo);
		
		return query.getResultList();
	}
	
	
	public List<Movimentacao> consultaDeMovimentacaoPorCategoria(Integer pId) {

		String jpql = " select m from Movimentacao m join m.categorias c where c.id = :pId order by m.valor desc ";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", pId);
		
		return  query.getResultList();
	}
	
	
	public Movimentacao recuperarContaDaMovimentacao(Integer id) {

		return em.find(Movimentacao.class, id);
	}
	
	
	@Test
	public BigDecimal recuperacaoSomaDeValoresDeMovimentacao() {

		String jpql = " select sum(m.valor) from Movimentacao m ";
		Query query = em.createQuery(jpql);
		
		return (BigDecimal) query.getSingleResult();
	}

	
	public Double recuperacaoMediaDeValoresDeMovimentacao() {

		String jpql = " select avg(m.valor) from Movimentacao m ";
		Query query = em.createQuery(jpql);
		
		return (Double) query.getSingleResult();
	}
	
	
	public BigDecimal recuperacaoDeValorMaximoDeMovimentacao() {

		String jpql = " select max(m.valor) from Movimentacao m ";
		Query query = em.createQuery(jpql);
		
		return (BigDecimal) query.getSingleResult();
	}
	
	
	
	public List<Double> recuperacaoMediaDeValoresDeMovimentacaoAgrupadosPorData() {

		String jpql = " select avg(m.valor) from Movimentacao m group by truncate_date(m.data) ";
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		
		return query.getResultList();
	}

}
