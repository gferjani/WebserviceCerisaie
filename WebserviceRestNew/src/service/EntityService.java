package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class EntityService
{
	protected EntityManager entitymanager;
	protected EntityManagerFactory emf;	
	public EntityTransaction startTransaction()
	{
		emf=Persistence.createEntityManagerFactory("map");
		entitymanager=emf.createEntityManager();
		return entitymanager.getTransaction();
	}
}
