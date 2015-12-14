package modelservice;

import javax.persistence.EntityTransaction;

import metier.Calcul;
import model.Emplacement;
import model.Sejour;
import model.TypeEmplacement;
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
			Emplacement emplacement = entitymanager.find(Emplacement.class, sejour.getEmplacement());
			TypeEmplacement typeEmplacement = entitymanager.find(TypeEmplacement.class, emplacement.getTypeEmplacement());
			
			return Calcul.calculFactureEmplacement(sejour, typeEmplacement);
		}
		finally
		{
			entitymanager.close();
			emf.close();
		}
	}
}
