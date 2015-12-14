package modelservice;

import model.Client;
import service.DataEntityService;

public class ClientEntityService extends DataEntityService<Client>
{
	public ClientEntityService()
	{
		super("Client", Client.class);
	}

	@Override
	protected int getId(Client entity)
	{
		return entity.getNumCli();
	}
}
