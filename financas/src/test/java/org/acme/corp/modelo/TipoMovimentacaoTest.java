package org.acme.corp.modelo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import org.acme.corp.util.JPAUtil;
import org.junit.Test;

public class TipoMovimentacaoTest {

	@Test
	public void salvarTipoDeMovimentacao() {
		
		Conta conta = new Conta();
		conta.setTitular("Luiz");
		conta.setBanco("Bradesco");
		conta.setAgencia("0203-78");
		conta.setNumero("124323-3");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("134.50"));
		
		movimentacao.setConta(conta);		
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		
		em.persist(movimentacao);
		em.getTransaction().commit();
		
		em.close();
	}
	
	
	@Test
	public void salvarTipoDeMovimentacaoComCategoria() {
		
		Categoria viagem = new Categoria("Viagem");
		Categoria negocios = new Categoria("Negócios");
		
		Conta conta = new Conta();
		conta.setTitular("Luiz");
		conta.setBanco("Bradesco");
		conta.setAgencia("0203-05");
		conta.setNumero("124323-3");
		
		Movimentacao viagemSP = new Movimentacao();
		viagemSP.setData(Calendar.getInstance());
		viagemSP.setDescricao("Viagem a Bauru");
		viagemSP.setTipo(TipoMovimentacao.SAIDA);
		viagemSP.setValor(new BigDecimal("134.50"));
		
		viagemSP.setConta(conta);		
		viagemSP.setCategorias(Arrays.asList(viagem, negocios));
		
		Movimentacao viagemRJ = new Movimentacao();
		
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		viagemRJ.setData(amanha);
		viagemRJ.setDescricao("Viagem a Assis");
		viagemRJ.setTipo(TipoMovimentacao.SAIDA);
		viagemRJ.setValor(new BigDecimal("198.50"));

		viagemRJ.setConta(conta);		
		viagemRJ.setCategorias(Arrays.asList(viagem, negocios));
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		
		em.persist(viagem);
		em.persist(negocios);
		
		em.persist(viagemSP);
		em.persist(viagemRJ);
		
		em.getTransaction().commit();
		
		em.close();
	}

}
