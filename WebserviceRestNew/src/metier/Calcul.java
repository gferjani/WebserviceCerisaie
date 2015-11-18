package metier;

import model.Sejour;
import model.TypeEmplacement;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Calcul
{
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
}
