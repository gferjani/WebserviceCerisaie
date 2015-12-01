package model;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public abstract class JSonListConverter<T> extends JsonSerializer<List<T>>
{
	@Override
	public void serialize(List<T> elements, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
	{
		jgen.writeStartArray();
		for(T es : elements)
			jgen.writeNumber(getID(es));
		jgen.writeEndArray();
	}
	
	protected abstract int getID(T element);
}
