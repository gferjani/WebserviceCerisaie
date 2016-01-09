package metier;

import model.Activite;
import model.Emplacement;
import model.Sejour;
import model.Sport;
import model.TypeEmplacement;

import java.text.SimpleDateFormat;
import java.util.List;

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
		
		Period period = new Period(new DateTime(sejour.getDatedebSej()), new DateTime(sejour.getDateFinSej()));
		
		double nbSecJour = 60 * 60 * 24;
		double diffTime = (sejour.getDateFinSej().getTime() - sejour.getDatedebSej().getTime())/1000;
		double nbJour = Math.ceil(diffTime / nbSecJour)+1;

		return (float)nbJour * sejour.getNbPersonnes() * emp.getTypeEmplacement().getTariftypepl();
	}
	
	/**
	 * Calculer le prix total des prestations sportives pour un sejour
	 * @param sejour 
	 * @return prix total
	 */
	public static float calculPrixPrestationsSportives(Sejour s)
	{
		List<Activite> activites = s.getActivites();
		float prixTotal = 0;
		
		for (Activite a : activites)
			prixTotal += ((float)a.getNbloc() * a.getSport().getTarifUnite());
		
		return prixTotal;		
	}
}
