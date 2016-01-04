package service;

import java.util.List;

import javax.persistence.EntityTransaction;

public abstract class CompositeDataEntityService<T, K> extends EntityService
{
	public CompositeDataEntityService(String DataBaseEntityName, Class<T> classValue)
	{
		this.DataBaseEntityName = DataBaseEntityName;
		this.classValue = classValue;
	}
	
	protected final Class<T> classValue;
	protected final String DataBaseEntityName;
	
	protected abstract K getId(T entity);

	public T recherche(K key)
	{
		startTransaction().begin();
		return entitymanager.find(classValue, key);
	}
	public List<T> rechercheTous()
	{
		startTransaction().begin();
		return entitymanager.createQuery("SELECT c FROM " + DataBaseEntityName + " c", classValue).getResultList();
	}
	public K ajouter(T entity)
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
	public void supprimer(K key)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.remove(entitymanager.find(classValue, key));
		transac.commit();
	}
}
