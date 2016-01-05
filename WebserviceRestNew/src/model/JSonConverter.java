package model;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public abstract class JSonConverter<T> extends JsonSerializer<T>
{
	@Override
	public void serialize(T element, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
	{
		jgen.writeNumber(getID(element));
	}
	
	protected abstract int getID(T element);
}
