package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import model.Client;
import model.Sport;

/*
 * TO DELETE
 */
public class SportEntityService extends EntityService
{
	public List<Sport> rechercheTousSport()
	{
		List<Sport> sports=new ArrayList<Sport>();
		EntityTransaction transac = startTransaction();
		transac.begin();
		sports=entitymanager.createQuery("SELECT c FROM Sport c",Sport.class).getResultList();
		entitymanager.close();
		emf.close();
		return sports;
	}
}
