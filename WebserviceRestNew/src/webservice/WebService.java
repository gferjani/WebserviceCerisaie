package webservice;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.joda.time.Instant;

import model.Activite;
import model.ActivitePK;
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
	// http://localhost:8081/webserviceRest/webservice/client/get/1

	/***************************************************
	 * ACTIVITE
	 ***************************************************/

	protected ActivitePK toActivitePK(
			@PathParam("scodeSport") 	String scodeSport,
			@PathParam("sdateJour") 	String sdateJour,
			@PathParam("snumSej") 		String snumSej)
	{
		ActivitePK key = new ActivitePK();

		key.setCodeSport(Integer.parseInt(scodeSport));
		key.setDateJour(Instant.parse(sdateJour).toDate());
		key.setNumSej(Integer.parseInt(snumSej));
		
		return key;
	}

	@GET
	@Path("/activite/delete/{scodeSport}/{sdateJour}/{snumSej}")
	@Produces("application/json")
	public WebServiceAnswer activite_del(
			@PathParam("scodeSport") 	String scodeSport,
			@PathParam("sdateJour") 	String sdateJour,
			@PathParam("snumSej") 		String snumSej)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			ses.supprimer(toActivitePK(scodeSport, sdateJour, snumSej));
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid();
		}
	}

	@POST
	@Path("/activite/add")
	@Produces("application/json")
	public WebServiceAnswer activite_add(
			@FormParam("nbloc") 		String nbloc,
			@FormParam("sejour") 		String sejour,
			@FormParam("sport") 		String sport)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			Activite s = new Activite();
	
			s.setNbloc(Integer.parseInt(nbloc));
			s.setSejour(new SejourEntityService().recherche(sejour));
			s.setSport(new SportEntityService().recherche(sport));
			
			ses.ajouter(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid();
		}
	}

	@POST
	@Path("/activite/edit/{scodeSport}/{sdateJour}/{snumSej}")
	@Produces("application/json")
	public WebServiceAnswer activite_edit(
			@PathParam("scodeSport") 	String scodeSport,
			@PathParam("sdateJour") 	String sdateJour,
			@PathParam("snumSej") 		String snumSej,
			@FormParam("nbloc") 		String nbloc,
			@FormParam("sejour") 		String sejour,
			@FormParam("sport") 		String sport)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			Activite s = ses.recherche(toActivitePK(scodeSport, sdateJour, snumSej));
	
			s.setNbloc(Integer.parseInt(nbloc));
			s.setSejour(new SejourEntityService().recherche(sejour));
			s.setSport(new SportEntityService().recherche(sport));
			
			ses.modifier(s);
			return WebServiceAnswer.createValid();
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid();
		}
	}

	@GET
	@Path("/activite/get/{scodeSport}/{sdateJour}/{snumSej}")
	@Produces("application/json")
	public WebServiceAnswer activite_get(
			@PathParam("scodeSport") 	String scodeSport,
			@PathParam("sdateJour") 	String sdateJour,
			@PathParam("snumSej") 		String snumSej)
	{
		try
		{
			ActiviteEntityService ses = new ActiviteEntityService();
			return WebServiceAnswer.createValid(ses.recherche(toActivitePK(scodeSport, sdateJour, snumSej)));
		}
		catch(Throwable ex)
		{
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
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
			return WebServiceAnswer.createInvalid();
		}
	}
}
