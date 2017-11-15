package org.acme.corp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.acme.corp.modelo.Conta;

public class ContaDAO {

	private EntityManager em;

	public ContaDAO(EntityManager em){
		this.em = em;
	}
	
	
	public void inserir(Conta conta) {
		em.persist(conta);
	}
	
	
	public Conta recuperarPorId(Integer id){
		return em.find(Conta.class, id);
	}
	
	
	public List<Conta> recuperarContasComMovimentacoes(){
		
		String jpql = " select distinct c from Conta c left join fetch c.movimentacoes ";
		
		return em.createQuery(jpql).getResultList();
	}
	
	
	public Long recuperarQuantidadeDeMovimentacoesDaConta(Conta conta){
		
		String jpql = " select count(m) from Movimentacao m where m.conta = :pConta ";
		
		Query query = em.createQuery(jpql);
		
		query.setParameter("pConta", conta);
		
		return (Long) query.getSingleResult();
	}
}
