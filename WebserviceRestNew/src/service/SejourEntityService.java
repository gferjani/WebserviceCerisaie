package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import model.Client;
import model.Sejour;

/*
 * TO DELETE
 */
public class SejourEntityService extends EntityService
{
	public Sejour rechercheSejour(int id)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		Sejour sejour=entitymanager.find(Sejour.class, id);
		entitymanager.close();
		emf.close();
		return sejour;
	}
	public List<Sejour> rechercheTousSejour()
	{
		List<Sejour> sejours=new ArrayList<Sejour>();
		EntityTransaction transac = startTransaction();
		transac.begin();
		sejours=entitymanager.createQuery("SELECT s FROM Sejour s",Sejour.class).getResultList();
		entitymanager.close();
		emf.close();
		return sejours;
	}
	public int ajoutSejour(Sejour sejour)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.persist(sejour);
		transac.commit();
		entitymanager.close();
		emf.close();
		return sejour.getNumSej();
	}
	public void modifierSejour(Sejour sejour)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.merge(sejour);
		transac.commit();
		entitymanager.close();
		emf.close();
	}
	public void supprimerSejour(int id)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		Sejour sejour=entitymanager.find(Sejour.class, id);
		entitymanager.remove(sejour);
		transac.commit();
		entitymanager.close();
		emf.close();
	}
}
