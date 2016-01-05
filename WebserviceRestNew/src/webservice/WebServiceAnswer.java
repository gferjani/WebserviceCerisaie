package webservice;

import java.io.Serializable;

import com.sun.jersey.api.NotFoundException;

public class WebServiceAnswer<T> implements Serializable
{
	public WebServiceAnswer(T msg, boolean isValid)
	{
		this.msg = msg;
		this.isValid = isValid;
	}

	public static <T> WebServiceAnswer createValid(T msg)
	{
		if(msg == null)
			return createInvalid(new NotFoundException());
		else
			return new WebServiceAnswer<T>(msg, true);
	}
	public static WebServiceAnswer<Boolean> createValid()
	{
		return createValid(true);
	}
	public static WebServiceAnswer<Throwable> createInvalid(Throwable ex)
	{
		return new WebServiceAnswer<Throwable>(ex, false);
	}
	
	private final T msg;
	private final boolean isValid;
	
	public T getMsg()
	{
		return msg;
	}
	
	public boolean isValid()
	{
		return isValid;
	}
}
