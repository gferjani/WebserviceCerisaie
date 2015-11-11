package test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import model.Client;

import org.junit.Before;
import org.junit.Test;

import service.ClientEntityService;

 
public class TestClientEntityService
{
	private Client client;
	private EntityManager entitymanager;
	private ClientEntityService service;
	@Before
	public void initialize()
	{
		service = new ClientEntityService();
		client = new Client();
		client.setAdrRueCli("57 rue des marroniers");
		client.setCpCli("69100");
		client.setNomCli("GrosTas");
		client.setNumPieceCli("27");
		client.setVilleCli("Troupaumé");
		client.setPieceCli("PS");
	}
	public Client customRecherche(int id)
	{
		try{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("map");
		entitymanager=emf.createEntityManager();
		entitymanager.getTransaction().begin();
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Client> query = cb.createQuery(Client.class);
		Root<Client> c = query.from(Client.class);
		query.select(c);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		query.where(cb.equal(c.get("numCli"), p));
		Client resultat=entitymanager.createQuery(query).setParameter(p,id).getSingleResult();
		return resultat;
		}
		catch(javax.persistence.NoResultException e)
		{
			return null;
		}
	}
	@Test
	@Transactional
	public void ajoutClient()
	{
		//a test
		int id = service.ajoutClient(client);
		//pour tester
		Client resultat=customRecherche(id);
		//comparaison résultat
		assertEquals("Les client ne sont pas les même",client.getAdrRueCli(), resultat.getAdrRueCli());
		assertEquals("Les client ne sont pas les même",client.getCpCli(), resultat.getCpCli());
		assertEquals("Les client ne sont pas les même",client.getNomCli(), resultat.getNomCli());
		assertEquals("Les client ne sont pas les même",client.getNumCli(), resultat.getNumCli());
		assertEquals("Les client ne sont pas les même",client.getNumPieceCli(), resultat.getNumPieceCli());
		assertEquals("Les client ne sont pas les même",client.getPieceCli(), resultat.getPieceCli());
		assertEquals("Les client ne sont pas les même",client.getVilleCli(), resultat.getVilleCli());
	}
	@Test
	public void rechercheClient()
	{
		int id=13;
		Client resultat=service.rechercheClient(id);
		//comparaison résultat
		assertEquals("Les client ne sont pas les même",client.getAdrRueCli(), resultat.getAdrRueCli());
		assertEquals("Les client ne sont pas les même",client.getCpCli(), resultat.getCpCli());
		assertEquals("Les client ne sont pas les même",client.getNomCli(), resultat.getNomCli());
		assertEquals("Les client ne sont pas les même",13, resultat.getNumCli());
		assertEquals("Les client ne sont pas les même",client.getNumPieceCli(), resultat.getNumPieceCli());
		assertEquals("Les client ne sont pas les même",client.getPieceCli(), resultat.getPieceCli());
		assertEquals("Les client ne sont pas les même",client.getVilleCli(), resultat.getVilleCli());
		
	}
	@Test
	@Transactional
	public void modifierClient()
	{
		//a test
		int id = service.ajoutClient(client);
		client.setNomCli("Bob");
		service.modifierClient(client);
		//pour tester
		Client resultat=customRecherche(id);
		assertEquals("Les client ne sont pas les même","Bob", resultat.getNomCli());
		assertEquals("Les client ne sont pas les même",client.getNomCli(), resultat.getNomCli());
	}
	@Test
	@Transactional
	public void supprimerClient()
	{
		//a test
		int id = service.ajoutClient(client);
		service.supprimerClient(client.getNumCli());
		//pour tester
		Client resultat=customRecherche(id);
		assertEquals("Le client n'a pas été supprimé",null, resultat);
	}
	@Test
	public void rechercheTousClient()
	{
		//a test
		List<Client> clients=service.rechercheTousClient();
		Client resultat=clients.get(12);
		assertEquals("Les client ne sont pas les même",client.getAdrRueCli(), resultat.getAdrRueCli());
		assertEquals("Les client ne sont pas les même",client.getCpCli(), resultat.getCpCli());
		assertEquals("Les client ne sont pas les même",client.getNomCli(), resultat.getNomCli());
		assertEquals("Les client ne sont pas les même",13, resultat.getNumCli());
		assertEquals("Les client ne sont pas les même",client.getNumPieceCli(), resultat.getNumPieceCli());
		assertEquals("Les client ne sont pas les même",client.getPieceCli(), resultat.getPieceCli());
		assertEquals("Les client ne sont pas les même",client.getVilleCli(), resultat.getVilleCli());
	}
}
