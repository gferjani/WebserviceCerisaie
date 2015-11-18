package webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/json/product")
public class WebService
{
	@GET
	@Path("/get")
	@Produces("application/json")
	public String getProductInJSON() {

		String product = "Bonjour";
		
		return product; 

	}
}
