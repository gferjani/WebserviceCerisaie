package webservice;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Client;
import model.Emplacement;
import model.Sejour;
import model.Sport;
import model.TypeEmplacement;
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
	 * TYPE EMPLACEMENT
	 ***************************************************/
	
	@GET
	@Path("/typeemplacement/delete/{sid}")
	@Produces("application/json")
	public void typeemplacement_del(@PathParam("sid") String sid)
	{
		TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
		ses.supprimer(sid);
	}

	@POST
	@Path("/typeemplacement/add")
	@Produces("application/json")
	public void typeemplacement_add(
			@FormParam("libelle") 		String libelle,
			@FormParam("tariftypepl") 	String tariftypepl)
	{
		TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
		TypeEmplacement s = new TypeEmplacement();
		
		s.setLibtypepl(libelle);
		s.setTariftypepl(Float.parseFloat(tariftypepl));
		
		ses.ajouter(s);
	}

	@POST
	@Path("/typeemplacement/edit/{sid}")
	@Produces("application/json")
	public void typeemplacement_edit(
			@PathParam("sid") 			String sid,
			@FormParam("libelle") 		String libelle,
			@FormParam("tariftypepl") 	String tariftypepl)
	{
		TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
		TypeEmplacement s = ses.recherche(sid);

		s.setLibtypepl(libelle);
		s.setTariftypepl(Float.parseFloat(tariftypepl));
		
		ses.modifier(s);
	}

	@GET
	@Path("/typeemplacement/get/{sid}")
	@Produces("application/json")
	public TypeEmplacement typeemplacement_get(@PathParam("sid") String sid)
	{
		TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
		return ses.recherche(sid);
	}

	@GET
	@Path("/typeemplacement/getall")
	@Produces("application/json")
	public List<TypeEmplacement> typeemplacement_all()
	{
		TypeEmplacementEntityService ses = new TypeEmplacementEntityService();
		return ses.rechercheTous();
	}

	/***************************************************
	 * EMPLACEMENT
	 ***************************************************/
	
	@GET
	@Path("/emplacement/delete/{sid}")
	@Produces("application/json")
	public void emplacement_del(@PathParam("sid") String sid)
	{
		EmplacementEntityService ses = new EmplacementEntityService();
		ses.supprimer(sid);
	}

	@POST
	@Path("/emplacement/add")
	@Produces("application/json")
	public void emplacement_add(
			@FormParam("nbPersMaxEmpl") 	String nbPersMaxEmpl,
			@FormParam("surfaceEmpl") 		String surfaceEmpl,
			@FormParam("typeEmplacement") 	String typeEmplacement)
	{
		EmplacementEntityService ses = new EmplacementEntityService();
		Emplacement s = new Emplacement();

		s.setNbPersMaxEmpl(Integer.parseInt(nbPersMaxEmpl));
		s.setSurfaceEmpl(Float.parseFloat(surfaceEmpl));
		s.setTypeEmplacement(new TypeEmplacementEntityService().recherche(typeEmplacement));
		
		ses.ajouter(s);
	}

	@POST
	@Path("/emplacement/edit/{sid}")
	@Produces("application/json")
	public void emplacement_edit(
			@PathParam("sid") 				String sid,
			@FormParam("nbPersMaxEmpl") 	String nbPersMaxEmpl,
			@FormParam("surfaceEmpl") 		String surfaceEmpl,
			@FormParam("typeEmplacement") 	String typeEmplacement)
	{
		EmplacementEntityService ses = new EmplacementEntityService();
		Emplacement s = ses.recherche(sid);

		s.setNbPersMaxEmpl(Integer.parseInt(nbPersMaxEmpl));
		s.setSurfaceEmpl(Float.parseFloat(surfaceEmpl));
		s.setTypeEmplacement(new TypeEmplacementEntityService().recherche(typeEmplacement));
		
		ses.modifier(s);
	}

	@GET
	@Path("/emplacement/get/{sid}")
	@Produces("application/json")
	public Emplacement emplacement_get(@PathParam("sid") String sid)
	{
		EmplacementEntityService ses = new EmplacementEntityService();
		return ses.recherche(sid);
	}

	@GET
	@Path("/emplacement/getall")
	@Produces("application/json")
	public List<Emplacement> emplacement_all()
	{
		EmplacementEntityService ses = new EmplacementEntityService();
		return ses.rechercheTous();
	}

	/***************************************************
	 * SPORT
	 ***************************************************/
	
	@GET
	@Path("/sport/delete/{sid}")
	@Produces("application/json")
	public void sport_del(@PathParam("sid") String sid)
	{
		SportEntityService ses = new SportEntityService();
		ses.supprimer(sid);
	}

	@POST
	@Path("/sport/add")
	@Produces("application/json")
	public void sport_add(
			@FormParam("libelle") 		String libelle,
			@FormParam("tarifUnite") 	String tarifUnite,
			@FormParam("uniteTpsSport") String uniteTpsSport)
	{
		SportEntityService ses = new SportEntityService();
		Sport s = new Sport();

		s.setLibelleSport(libelle);
		s.setTarifUnite(Float.parseFloat(tarifUnite));
		s.setUniteTpsSport(uniteTpsSport);
		
		ses.ajouter(s);
	}

	@POST
	@Path("/sport/edit/{sid}")
	@Produces("application/json")
	public void sport_edit(
			@PathParam("sid") 			String sid,
			@FormParam("libelle") 		String libelle,
			@FormParam("tarifUnite") 	String tarifUnite,
			@FormParam("uniteTpsSport") String uniteTpsSport)
	{
		SportEntityService ses = new SportEntityService();
		Sport s = ses.recherche(sid);

		s.setLibelleSport(libelle);
		s.setTarifUnite(Float.parseFloat(tarifUnite));
		s.setUniteTpsSport(uniteTpsSport);
		
		ses.modifier(s);
	}

	@GET
	@Path("/sport/get/{sid}")
	@Produces("application/json")
	public Sport sport_get(@PathParam("sid") String sid)
	{
		SportEntityService ses = new SportEntityService();
		return ses.recherche(sid);
	}

	@GET
	@Path("/sport/getall")
	@Produces("application/json")
	public List<Sport> sport_all()
	{
		SportEntityService ses = new SportEntityService();
		return ses.rechercheTous();
	}

	/***************************************************
	 * SEJOUR
	 ***************************************************/
	
	@GET
	@Path("/sejour/delete/{sid}")
	@Produces("application/json")
	public void sejour_del(@PathParam("sid") String sid)
	{
		SejourEntityService ses = new SejourEntityService();
		ses.supprimer(sid);
	}

	@POST
	@Path("/sejour/add")
	@Produces("application/json")
	public void sejour_add(
			@FormParam("datedebSej") 	String datedebSej,
			@FormParam("dateFinSej") 	String dateFinSej,
			@FormParam("nbPersonnes") 	String nbPersonnes,
			@FormParam("client") 		String client,
			@FormParam("emplacement") 	String emplacement)
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
	}

	@POST
	@Path("/sejour/edit/{sid}")
	@Produces("application/json")
	public void sejour_edit(
			@PathParam("sid") 			String sid,
			@FormParam("datedebSej") 	String datedebSej,
			@FormParam("dateFinSej") 	String dateFinSej,
			@FormParam("nbPersonnes") 	String nbPersonnes,
			@FormParam("client") 		String client,
			@FormParam("emplacement") 	String emplacement)
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
	}

	@GET
	@Path("/sejour/get/{sid}")
	@Produces("application/json")
	public Sejour sejour_get(@PathParam("sid") String sid)
	{
		SejourEntityService ses = new SejourEntityService();
		return ses.recherche(sid);
	}

	@GET
	@Path("/sejour/getall")
	@Produces("application/json")
	public List<Sejour> sejour_all()
	{
		SejourEntityService ses = new SejourEntityService();
		return ses.rechercheTous();
	}
	
	
	
	/***************************************************
	 * CLIENT
	 ***************************************************/
	

	@GET
	@Path("/client/delete/{sid}")
	@Produces("application/json")
	public void client_del(@PathParam("sid") String sid)
	{
		ClientEntityService ces = new ClientEntityService();
		ces.supprimer(sid);
	}

	@POST
	@Path("/client/add")
	@Produces("application/json")
	public void client_add(
			@FormParam("adrRueCli") 	String adrRueCli,
			@FormParam("cpCli") 		String cpCli,
			@FormParam("nomCli") 		String nomCli,
			@FormParam("numPieceCli") 	String numPieceCli,
			@FormParam("pieceCli") 		String pieceCli,
			@FormParam("villeCli") 		String villeCli)
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
	}

	@POST
	@Path("/client/edit/{sid}")
	@Produces("application/json")
	public void client_edit(
			@PathParam("sid") 			String sid,
			@FormParam("adrRueCli") 	String adrRueCli,
			@FormParam("cpCli") 		String cpCli,
			@FormParam("nomCli") 		String nomCli,
			@FormParam("numPieceCli") 	String numPieceCli,
			@FormParam("pieceCli") 		String pieceCli,
			@FormParam("villeCli") 		String villeCli)
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
	}

	@GET
	@Path("/client/get/{sid}")
	@Produces("application/json")
	public Client client_get(@PathParam("sid") String sid)
	{
		ClientEntityService ces = new ClientEntityService();
		return ces.recherche(sid);
	}
	
	@GET
	@Path("/client/find/{sname}")
	@Produces("application/json")
	public List<Client> client_find(@PathParam("sname") String sname)
	{
		ClientEntityService ces = new ClientEntityService();
		return ces.findByNom(sname);
	}

	@GET
	@Path("/client/getall")
	@Produces("application/json")
	public List<Client> client_all()
	{
		ClientEntityService ces = new ClientEntityService();
		return ces.rechercheTous();
	}
}
