package modelservice;

import javax.persistence.EntityTransaction;

import metier.Calcul;
import model.Sejour;
import service.EntityService;

public class FactureEntityService extends EntityService
{
	public float facturationEmplacement(int numSej)
	{
		try
		{
			EntityTransaction transac = startTransaction();
			transac.begin();
			
			Sejour sejour = entitymanager.find(Sejour.class, numSej);
			return Calcul.calculPrixSejour(sejour);
		}
		finally
		{
			entitymanager.close();
			emf.close();
		}
	}
}
