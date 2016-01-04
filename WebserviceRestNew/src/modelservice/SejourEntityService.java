package modelservice;

import model.Sejour;
import service.DataEntityService;

public class SejourEntityService extends DataEntityService<Sejour>
{
	public SejourEntityService()
	{
		super("Sejour", Sejour.class);
	}

	@Override
	protected Integer getId(Sejour entity)
	{
		return entity.getNumSej();
	}
}
