package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Client;

public class ClientEntityService extends EntityService
{
	public Client rechercheClient(int id)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		Client client=entitymanager.find(Client.class, id);
		entitymanager.close();
		emf.close();
		return client;
	}
	public List<Client> rechercheTousClient()
	{
		List<Client> clients=new ArrayList<Client>();
		EntityTransaction transac = startTransaction();
		transac.begin();
		clients=entitymanager.createQuery("SELECT c FROM Client c",Client.class).getResultList();
		entitymanager.close();
		emf.close();
		return clients;
	}
	public int ajoutClient(Client client)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.persist(client);
		transac.commit();
		entitymanager.close();
		emf.close();
		return client.getNumCli();
	}
	public void modifierClient(Client client)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		entitymanager.merge(client);
		transac.commit();
		entitymanager.close();
		emf.close();
	}
	public void supprimerClient(int id)
	{
		EntityTransaction transac = startTransaction();
		transac.begin();
		Client client=entitymanager.find(Client.class, id);
		entitymanager.remove(client);
		transac.commit();
		entitymanager.close();
		emf.close();
	}
}
