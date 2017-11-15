package org.acme.corp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
}
