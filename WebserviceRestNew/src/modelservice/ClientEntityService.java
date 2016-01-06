package modelservice;

import java.util.List;

import javax.persistence.EntityTransaction;

import model.Client;
import service.DataEntityService;

public class ClientEntityService extends DataEntityService<Client>
{
	public ClientEntityService()
	{
		super("Client", Client.class);
	}

	@Override
	protected Integer getId(Client entity)
	{
		return entity.getNumCli();
	}
	
	public List<Client> findByNom(String nom)
	{
		startTransaction().begin();
		List<Client> clients = entitymanager
					.createQuery("SELECT c FROM Client c WHERE c.nomCli LIKE :name", Client.class)
					.setParameter("name", "%"+nom+"%")
					.getResultList();
		return clients;
	}
}
