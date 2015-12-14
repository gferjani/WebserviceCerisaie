package service;

import java.util.List;

import javax.persistence.EntityTransaction;

public abstract class DataEntityService<T> extends EntityService
{
	public DataEntityService(String DataBaseEntityName, Class<T> classValue)
	{
		this.DataBaseEntityName = DataBaseEntityName;
		this.classValue = classValue;
	}
	
	protected final Class<T> classValue;
	protected final String DataBaseEntityName;
	
	protected abstract int getId(T entity);

	public T recherche(String id)
	{
		return recherche(Integer.parseInt(id));
	}
	public T recherche(int id)
	{
		startTransaction().begin();
		return entitymanager.find(classValue, id);
	}
	public List<T> rechercheTous()
	{
		startTransaction().begin();
		return entitymanager.createQuery("SELECT c FROM " + DataBaseEntityName + " c", classValue).getResultList();
	}
	public int ajouter(T entity)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.persist(entity);
		transac.commit();
		return getId(entity);
	}
	public void modifier(T entity)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.merge(entity);
		transac.commit();
	}
	public void supprimer(String id)
	{
		supprimer(Integer.parseInt(id));
	}
	public void supprimer(int id)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.remove(entitymanager.find(classValue, id));
		transac.commit();
	}
	
	public void close()
	{
		entitymanager.close();
		emf.close();
	}
}
