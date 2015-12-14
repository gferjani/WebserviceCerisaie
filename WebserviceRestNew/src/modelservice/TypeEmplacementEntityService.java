package modelservice;

import model.TypeEmplacement;
import service.DataEntityService;

public class TypeEmplacementEntityService extends DataEntityService<TypeEmplacement>
{
	public TypeEmplacementEntityService()
	{
		super("TypeEmplacement", TypeEmplacement.class);
	}

	@Override
	protected int getId(TypeEmplacement entity)
	{
		return entity.getCodeTypeE();
	}
}
