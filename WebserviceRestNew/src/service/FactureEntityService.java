package service;

import javax.persistence.EntityTransaction;

import metier.Calcul;
import model.Emplacement;
import model.Sejour;
import model.TypeEmplacement;

public class FactureEntityService extends EntityService
{
	public float facturationEmplacement(int numSej)
	{
		float factureEmplacement=0;
		EntityTransaction transac = startTransaction();
		transac.begin();
		Sejour sejour=entitymanager.find(Sejour.class, numSej);
		Emplacement emplacement=entitymanager.find(Emplacement.class, sejour.getEmplacement());
		TypeEmplacement typeEmplacement=entitymanager.find(TypeEmplacement.class, emplacement.getTypeEmplacement());
		Calcul.calculFactureEmplacement(sejour, typeEmplacement);
		entitymanager.close();
		emf.close();
		return factureEmplacement;
	}
}
