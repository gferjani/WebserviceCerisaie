package metier;

import model.Emplacement;
import model.Sejour;
import model.TypeEmplacement;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Calcul
{
	/**
	 * Calculer le prix total pour un sejour en fonction de la durée et du tarif de l'emplacement
	 * @param sejour 
	 * @return prix total
	 */
	public static float calculPrixSejour(Sejour sejour)
	{
		Emplacement emp = sejour.getEmplacement();
		
		if(emp == null || emp.getTypeEmplacement() == null)
			return 0;
		
		Period period = new Period(new DateTime(sejour.getDatedebSej()).toInstant(), new DateTime(sejour.getDateFinSej()));
		
		return period.getDays() * sejour.getNbPersonnes() * emp.getTypeEmplacement().getTariftypepl();
	}
}
