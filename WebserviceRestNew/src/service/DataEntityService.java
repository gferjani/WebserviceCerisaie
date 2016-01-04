package service;

import java.util.List;

import javax.persistence.EntityTransaction;

public abstract class DataEntityService<T> extends CompositeDataEntityService<T, Integer>
{
	public DataEntityService(String DataBaseEntityName, Class<T> classValue)
	{
		super(DataBaseEntityName, classValue);
	}
	
	public T recherche(String id)
	{
		return recherche(Integer.parseInt(id));
	}
	public void supprimer(String id)
	{
		supprimer(Integer.parseInt(id));
	}
}
