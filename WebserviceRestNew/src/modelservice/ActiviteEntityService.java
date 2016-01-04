package modelservice;

import model.Activite;
import model.ActivitePK;
import service.CompositeDataEntityService;

public class ActiviteEntityService extends CompositeDataEntityService<Activite, ActivitePK>
{
	public ActiviteEntityService()
	{
		super("Activite", Activite.class);
	}

	@Override
	protected ActivitePK getId(Activite entity)
	{
		return entity.getId();
	}
}
