package modelservice;

import model.Sport;
import service.DataEntityService;

public class SportEntityService extends DataEntityService<Sport>
{
	public SportEntityService()
	{
		super("Sport", Sport.class);
	}

	@Override
	protected Integer getId(Sport entity)
	{
		return entity.getCodeSport();
	}
}
