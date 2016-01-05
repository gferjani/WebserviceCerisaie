package webservice;

import java.io.Serializable;

public class WebServiceAnswer<T> implements Serializable
{
	public WebServiceAnswer(T msg, boolean isValid)
	{
		this.msg = msg;
		this.isValid = isValid;
	}

	public static <T> WebServiceAnswer<T> createValid(T msg)
	{
		return new WebServiceAnswer<T>(msg, true);
	}
	public static WebServiceAnswer<Boolean> createValid()
	{
		return createValid(true);
	}
	public static WebServiceAnswer<Boolean> createInvalid()
	{
		return new WebServiceAnswer<Boolean>(false, false);
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
