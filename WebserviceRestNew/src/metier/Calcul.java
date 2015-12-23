package metier;

import model.Sejour;
import model.TypeEmplacement;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Calcul
{
	/*
	 * TO DELETE
	 * On facture des séjours pas des emplacement :-)
	 * En plus les sejours possède une foreign key vers emplacement ...
	 */
	public static float calculFactureEmplacement(Sejour sejour,TypeEmplacement typeEmplacement)
	{
		float factureEmplacement;
		DateTime dateDebut=new DateTime(sejour.getDatedebSej());
		DateTime dateFin=new DateTime(sejour.getDateFinSej());
		Period period=new Period(dateDebut.toInstant(),dateFin.toInstant());
		int nbjour=period.getDays();
		factureEmplacement=nbjour*typeEmplacement.getTariftypepl();
		return factureEmplacement;
	}
		
	/**
	 * Calculer le prix total pour un sejour en fonction de la durée et du tarif de l'emplacement
	 * @param sejour 
	 * @return prix total
	 */
	public static float calculPrixSejour(Sejour sejour)
	{
		if (sejour.getEmplacement() == null) return 0;
		if (sejour.getEmplacement().getTypeEmplacement() == null) return 0;
		
		Period period = new Period(new DateTime(sejour.getDatedebSej()).toInstant(), new DateTime(sejour.getDateFinSej()));
		
		return period.getDays() * sejour.getNbPersonnes() * sejour.getEmplacement().getTypeEmplacement().getTariftypepl();
	}
}
