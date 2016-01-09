package webservice;
	
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import metier.Calcul;
import model.Activite;
import model.Client;
import model.Emplacement;
import model.Sejour;
import model.Sport;
import model.TypeEmplacement;
import modelservice.ActiviteEntityService;
import modelservice.ClientEntityService;
import modelservice.EmplacementEntityService;
import modelservice.SejourEntityService;
import modelservice.SportEntityService;
import modelservice.TypeEmplacementEntityService;

@Path("/")
public class WebService
{	
	// http://localhost:8080/webserviceRest/webservice/client/get/1
	
	/***************************************************
	 * ACTIVITE
	 ***************************************************/

	@GET
	@Path("/activite/delete/{sid}")
	@Produces("application/json")
	public WebServiceAnswer activite_del(
			@PathParam("sid") 	String sid)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			ses.supprimer(sid);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/activite/add")
	@Produces("application/json")
	public WebServiceAnswer activite_add(
			@FormParam("sejour") 		String sejour,
			@FormParam("sport") 		String sport,
			@FormParam("date") 			String date,
			@FormParam("nbUnite") 			String nbUnite)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			
			boolean modif = false;
			int nblock = Integer.valueOf(nbUnite);
	
			DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.FRANCE);
			Date d = format.parse(date);
			
			Activite s;
			try {
				//on essaye de trouver l'activité pour incrementer le nombre
				s = ses.findBySportSejDate(Integer.valueOf(sport).intValue(), Integer.valueOf(sejour).intValue(), d);
				
				nblock += s.getNbloc();
				modif = true;
			} catch (Exception e) {
				//sinon on en créé une nouvelle
				s = new Activite();
			}
			
			s.setDateJour(d);
			s.setNbloc(nblock);
			s.setSejour(new SejourEntityService().recherche(sejour));
			s.setSport(new SportEntityService().recherche(sport));
		
			if (!modif)
				ses.ajouter(s);
			else
				ses.modifier(s);
				
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/activite/edit/{sid}")
	@Produces("application/json")
	public WebServiceAnswer activite_edit(
			@PathParam("sid") 			String sid,
			@FormParam("scodeSport") 	String scodeSport,
			@FormParam("sdateJour") 	String sdateJour,
			@FormParam("snumSej") 		String snumSej,
			@FormParam("nbloc") 		String nbloc)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			Activite s = ses.recherche(sid);
	
			s.setNbloc(Integer.parseInt(nbloc));
			s.setSejour(new SejourEntityService().recherche(snumSej));
			s.setSport(new SportEntityService().recherche(scodeSport));
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.FRANCE);
			s.setDateJour(format.parse(sdateJour));
			
			ses.modifier(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}
	
	@GET
	@Path("/activite/get/bySejour/{snumSej}")
	@Produces("application/json")
	public WebServiceAnswer activite_getBySej(
			@PathParam("snumSej") 		String snumSej)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			return WebServiceAnswer.createValid(ses.findBySejour(Integer.valueOf(snumSej)));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/activite/get/{sid}")
	@Produces("application/json")
	public WebServiceAnswer activite_get(
			@PathParam("sid") 	String sid)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			return WebServiceAnswer.createValid(ses.recherche(sid));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/activite/getall")
	@Produces("application/json")
	public WebServiceAnswer activite_all()
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			return WebServiceAnswer.createValid(ses.rechercheTous());
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	/***************************************************
	 * TYPE EMPLACEMENT
	 ***************************************************/
	
	@GET
	@Path("/typeemplacement/delete/{sid}")
	@Produces("application/json")
	public WebServiceAnswer typeemplacement_del(@PathParam("sid") String sid)
	{
		try
		{
			TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
			ses.supprimer(sid);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/typeemplacement/add")
	@Produces("application/json")
	public WebServiceAnswer typeemplacement_add(
			@FormParam("libelle") 		String libelle,
			@FormParam("tariftypepl") 	String tariftypepl)
	{
		try
		{
			TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
			TypeEmplacement s = new TypeEmplacement();
			
			s.setLibtypepl(libelle);
			s.setTariftypepl(Float.parseFloat(tariftypepl));
			
			ses.ajouter(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/typeemplacement/edit/{sid}")
	@Produces("application/json")
	public WebServiceAnswer typeemplacement_edit(
			@PathParam("sid") 			String sid,
			@FormParam("libelle") 		String libelle,
			@FormParam("tariftypepl") 	String tariftypepl)
	{
		try
		{
			TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
			TypeEmplacement s = ses.recherche(sid);
	
			s.setLibtypepl(libelle);
			s.setTariftypepl(Float.parseFloat(tariftypepl));
			
			ses.modifier(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/typeemplacement/get/{sid}")
	@Produces("application/json")
	public WebServiceAnswer typeemplacement_get(@PathParam("sid") String sid)
	{
		try
		{
			TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
			return WebServiceAnswer.createValid(ses.recherche(sid));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/typeemplacement/getall")
	@Produces("application/json")
	public WebServiceAnswer typeemplacement_all()
	{
		try
		{
			TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
			return WebServiceAnswer.createValid(ses.rechercheTous());
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	/***************************************************
	 * EMPLACEMENT
	 ***************************************************/
	
	@GET
	@Path("/emplacement/delete/{sid}")
	@Produces("application/json")
	public WebServiceAnswer emplacement_del(@PathParam("sid") String sid)
	{
		try
		{
			EmplacementEntityService ses = new EmplacementEntityService();
			ses.supprimer(sid);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/emplacement/add")
	@Produces("application/json")
	public WebServiceAnswer emplacement_add(
			@FormParam("nbPersMaxEmpl") 	String nbPersMaxEmpl,
			@FormParam("surfaceEmpl") 		String surfaceEmpl,
			@FormParam("typeEmplacement") 	String typeEmplacement)
	{
		try
		{
			EmplacementEntityService ses = new EmplacementEntityService();
			Emplacement s = new Emplacement();
	
			s.setNbPersMaxEmpl(Integer.parseInt(nbPersMaxEmpl));
			s.setSurfaceEmpl(Float.parseFloat(surfaceEmpl));
			s.setTypeEmplacement(new TypeEmplacementEntityService().recherche(typeEmplacement));
			
			ses.ajouter(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/emplacement/edit/{sid}")
	@Produces("application/json")
	public WebServiceAnswer emplacement_edit(
			@PathParam("sid") 				String sid,
			@FormParam("nbPersMaxEmpl") 	String nbPersMaxEmpl,
			@FormParam("surfaceEmpl") 		String surfaceEmpl,
			@FormParam("typeEmplacement") 	String typeEmplacement)
	{
		try
		{
			EmplacementEntityService ses = new EmplacementEntityService();
			Emplacement s = ses.recherche(sid);
	
			s.setNbPersMaxEmpl(Integer.parseInt(nbPersMaxEmpl));
			s.setSurfaceEmpl(Float.parseFloat(surfaceEmpl));
			s.setTypeEmplacement(new TypeEmplacementEntityService().recherche(typeEmplacement));
			
			ses.modifier(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/emplacement/get/{sid}")
	@Produces("application/json")
	public WebServiceAnswer emplacement_get(@PathParam("sid") String sid)
	{
		try
		{
			EmplacementEntityService ses = new EmplacementEntityService();
			return WebServiceAnswer.createValid(ses.recherche(sid));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/emplacement/getall")
	@Produces("application/json")
	public WebServiceAnswer emplacement_all()
	{
		try
		{
			EmplacementEntityService ses = new EmplacementEntityService();
			return WebServiceAnswer.createValid(ses.rechercheTous());
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	/***************************************************
	 * SPORT
	 ***************************************************/
	
	@GET
	@Path("/sport/delete/{sid}")
	@Produces("application/json")
	public WebServiceAnswer sport_del(@PathParam("sid") String sid)
	{
		try
		{
			SportEntityService ses = new SportEntityService();
			ses.supprimer(sid);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/sport/add")
	@Produces("application/json")
	public WebServiceAnswer sport_add(
			@FormParam("libelle") 		String libelle,
			@FormParam("tarifUnite") 	String tarifUnite,
			@FormParam("uniteTpsSport") String uniteTpsSport)
	{
		try
		{
			SportEntityService ses = new SportEntityService();
			Sport s = new Sport();
	
			s.setLibelleSport(libelle);
			s.setTarifUnite(Float.parseFloat(tarifUnite));
			s.setUniteTpsSport(uniteTpsSport);
			
			ses.ajouter(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/sport/edit/{sid}")
	@Produces("application/json")
	public WebServiceAnswer sport_edit(
			@PathParam("sid") 			String sid,
			@FormParam("libelle") 		String libelle,
			@FormParam("tarifUnite") 	String tarifUnite,
			@FormParam("uniteTpsSport") String uniteTpsSport)
	{
		try
		{
			SportEntityService ses = new SportEntityService();
			Sport s = ses.recherche(sid);
	
			s.setLibelleSport(libelle);
			s.setTarifUnite(Float.parseFloat(tarifUnite));
			s.setUniteTpsSport(uniteTpsSport);
			
			ses.modifier(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/sport/get/{sid}")
	@Produces("application/json")
	public WebServiceAnswer sport_get(@PathParam("sid") String sid)
	{
		try
		{
			SportEntityService ses = new SportEntityService();
			return WebServiceAnswer.createValid(ses.recherche(sid));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/sport/getall")
	@Produces("application/json")
	public WebServiceAnswer sport_all()
	{
		try
		{
			SportEntityService ses = new SportEntityService();
			return WebServiceAnswer.createValid(ses.rechercheTous());
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	/***************************************************
	 * SEJOUR
	 ***************************************************/
	
	@GET
	@Path("/sejour/delete/{sid}")
	@Produces("application/json")
	public WebServiceAnswer sejour_del(@PathParam("sid") String sid)
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			ses.supprimer(sid);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/sejour/add")
	@Produces("application/json")
	public WebServiceAnswer sejour_add(
			@FormParam("datedebSej") 	String datedebSej,
			@FormParam("dateFinSej") 	String dateFinSej,
			@FormParam("nbPersonnes") 	String nbPersonnes,
			@FormParam("client") 		String client,
			@FormParam("emplacement") 	String emplacement)
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			Sejour s = new Sejour();
			
			s.setDatedebSej(org.joda.time.DateTime.parse(datedebSej).toDate());
			s.setDateFinSej(org.joda.time.DateTime.parse(dateFinSej).toDate());
			s.setNbPersonnes(Integer.parseInt(nbPersonnes));
			
			if(!client.isEmpty())
				s.setClient(new ClientEntityService().recherche(client));
			if(!emplacement.isEmpty())
				s.setEmplacement(new EmplacementEntityService().recherche(emplacement));
			
			ses.ajouter(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/sejour/edit/{sid}")
	@Produces("application/json")
	public WebServiceAnswer sejour_edit(
			@PathParam("sid") 			String sid,
			@FormParam("datedebSej") 	String datedebSej,
			@FormParam("dateFinSej") 	String dateFinSej,
			@FormParam("nbPersonnes") 	String nbPersonnes,
			@FormParam("client") 		String client,
			@FormParam("emplacement") 	String emplacement)
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			Sejour s = ses.recherche(sid);
			
			s.setDatedebSej(org.joda.time.DateTime.parse(datedebSej).toDate());
			s.setDateFinSej(org.joda.time.DateTime.parse(dateFinSej).toDate());
			s.setNbPersonnes(Integer.parseInt(nbPersonnes));
			
			if(!client.isEmpty())
				s.setClient(new ClientEntityService().recherche(client));
			if(!emplacement.isEmpty())
				s.setEmplacement(new EmplacementEntityService().recherche(emplacement));
			
			ses.modifier(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/sejour/get/{sid}")
	@Produces("application/json")
	public WebServiceAnswer sejour_get(@PathParam("sid") String sid)
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			return WebServiceAnswer.createValid(ses.recherche(sid));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/sejour/getall")
	@Produces("application/json")
	public WebServiceAnswer sejour_all()
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			return WebServiceAnswer.createValid(ses.rechercheTous());
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}
	
	
	
	/***************************************************
	 * CLIENT
	 ***************************************************/
	

	@GET
	@Path("/client/delete/{sid}")
	@Produces("application/json")
	public WebServiceAnswer client_del(@PathParam("sid") String sid)
	{
		try
		{
			ClientEntityService ces = new ClientEntityService();
			ces.supprimer(sid);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/client/add")
	@Produces("application/json")
	public WebServiceAnswer client_add(
			@FormParam("adrRueCli") 	String adrRueCli,
			@FormParam("cpCli") 		String cpCli,
			@FormParam("nomCli") 		String nomCli,
			@FormParam("numPieceCli") 	String numPieceCli,
			@FormParam("pieceCli") 		String pieceCli,
			@FormParam("villeCli") 		String villeCli)
	{
		try
		{
			ClientEntityService ces = new ClientEntityService();
			Client c = new Client();
			c.setAdrRueCli(adrRueCli);
			c.setCpCli(cpCli);
			c.setNomCli(nomCli);
			c.setNumPieceCli(numPieceCli);
			c.setPieceCli(pieceCli);
			c.setVilleCli(villeCli);
			ces.ajouter(c);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@POST
	@Path("/client/edit/{sid}")
	@Produces("application/json")
	public WebServiceAnswer client_edit(
			@PathParam("sid") 			String sid,
			@FormParam("adrRueCli") 	String adrRueCli,
			@FormParam("cpCli") 		String cpCli,
			@FormParam("nomCli") 		String nomCli,
			@FormParam("numPieceCli") 	String numPieceCli,
			@FormParam("pieceCli") 		String pieceCli,
			@FormParam("villeCli") 		String villeCli)
	{
		try
		{
			ClientEntityService ces = new ClientEntityService();
			Client c = ces.recherche(sid);
			c.setAdrRueCli(adrRueCli);
			c.setCpCli(cpCli);
			c.setNomCli(nomCli);
			c.setNumPieceCli(numPieceCli);
			c.setPieceCli(pieceCli);
			c.setVilleCli(villeCli);
			ces.modifier(c);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/client/get/{sid}")
	@Produces("application/json")
	public WebServiceAnswer client_get(@PathParam("sid") String sid)
	{
		try
		{
			ClientEntityService ces = new ClientEntityService();
			return WebServiceAnswer.createValid(ces.recherche(sid));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}
	
	@GET
	@Path("/client/find/{sname}")
	@Produces("application/json")
	public WebServiceAnswer client_find(@PathParam("sname") String sname)
	{
		try
		{
			ClientEntityService ces = new ClientEntityService();
			return WebServiceAnswer.createValid(ces.findByNom(sname));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}

	@GET
	@Path("/client/getall")
	@Produces("application/json")
	public WebServiceAnswer client_all()
	{
		try
		{
			ClientEntityService ces = new ClientEntityService();
			return WebServiceAnswer.createValid(ces.rechercheTous());
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}
	
	/***************************************************
	 * FACTURE
	 ***************************************************/
	class FactureValue implements Serializable {
		private static final long serialVersionUID = 1L;
		private float prixTotal;
		public FactureValue(float pt)
		{
			this.prixTotal = pt;
		}
		public float getPrixTotal()
		{
			return prixTotal;
		}
		public void setPrixTotal(float pt)
		{
			this.prixTotal = pt;
		}
	}
	
	@GET
	@Path("/facture/sejour/{sid}")
	@Produces("application/json")
	public WebServiceAnswer factureSejour(@PathParam("sid") String sid)
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			Sejour s = ses.recherche(sid);
			return WebServiceAnswer.createValid(new FactureValue(Calcul.calculPrixSejour(s)));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}
	
	@GET
	@Path("/facture/prestations/{sid}")
	@Produces("application/json")
	public WebServiceAnswer facturePrestations(@PathParam("sid") String sid)
	{
		try
		{
			SejourEntityService ses = new SejourEntityService();
			Sejour s = ses.recherche(sid);
			return WebServiceAnswer.createValid(new FactureValue(Calcul.calculPrixPrestationsSportives(s)));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid(ex);
		}
	}
}
