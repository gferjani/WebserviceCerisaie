package modelservice;

import model.Emplacement;
import service.DataEntityService;

public class EmplacementEntityService extends DataEntityService<Emplacement>
{
	public EmplacementEntityService()
	{
		super("Emplacement", Emplacement.class);
	}

	@Override
	protected Integer getId(Emplacement entity)
	{
		return entity.getNumEmpl();
	}
}
