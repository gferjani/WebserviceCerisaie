package webservice;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.Instant;

import model.Activite;
import model.Client;
import model.Emplacement;
import model.Sejour;
import service.ClientEntityService;
import service.SejourEntityService;

@Path("/")
public class WebService
{
	// http://localhost:8081/webserviceRest/webservice/client/get/1

	@GET
	@Path("/sejour/delete/{sid}")
	@Produces("application/json")
	public void sejour_del(@PathParam("sid") String sid)
	{
		SejourEntityService ses = new SejourEntityService();
		ses.supprimerSejour(Integer.parseInt(sid));
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
			s.setClient(new ClientEntityService().rechercheClient(Integer.parseInt(client)));
		if(!emplacement.isEmpty())
			; // TODO : Set emplacement
		
		ses.ajoutSejour(s);
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
		Sejour s = ses.rechercheSejour(Integer.parseInt(sid));
		
		s.setDatedebSej(org.joda.time.DateTime.parse(datedebSej).toDate());
		s.setDateFinSej(org.joda.time.DateTime.parse(dateFinSej).toDate());
		s.setNbPersonnes(Integer.parseInt(nbPersonnes));
		if(!client.isEmpty())
			s.setClient(new ClientEntityService().rechercheClient(Integer.parseInt(client)));
		if(!emplacement.isEmpty())
			; // TODO : Set emplacement
		
		ses.ajoutSejour(s);
	}

	@GET
	@Path("/sejour/get/{sid}")
	@Produces("application/json")
	public Sejour sejour_get(@PathParam("sid") String sid)
	{
		SejourEntityService ses = new SejourEntityService();
		return ses.rechercheSejour(Integer.parseInt(sid));
	}

	@GET
	@Path("/sejour/getall")
	@Produces("application/json")
	public List<Sejour> sejour_all()
	{
		SejourEntityService ses = new SejourEntityService();
		return ses.rechercheTousSejour();
	}
	
	
	
	

	@GET
	@Path("/client/delete/{sid}")
	@Produces("application/json")
	public void client_del(@PathParam("sid") String sid)
	{
		ClientEntityService ces = new ClientEntityService();
		ces.supprimerClient(Integer.parseInt(sid));
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
		ces.ajoutClient(c);
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
		Client c = ces.rechercheClient(Integer.parseInt(sid));
		c.setAdrRueCli(adrRueCli);
		c.setCpCli(cpCli);
		c.setNomCli(nomCli);
		c.setNumPieceCli(numPieceCli);
		c.setPieceCli(pieceCli);
		c.setVilleCli(villeCli);
		ces.modifierClient(c);
	}

	@GET
	@Path("/client/get/{sid}")
	@Produces("application/json")
	public Client client_get(@PathParam("sid") String sid)
	{
		ClientEntityService ces = new ClientEntityService();
		return ces.rechercheClient(Integer.parseInt(sid));
	}

	@GET
	@Path("/client/getall")
	@Produces("application/json")
	public List<Client> client_all()
	{
		ClientEntityService ces = new ClientEntityService();
		return ces.rechercheTousClient();
	}
}
