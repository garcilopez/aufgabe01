package httpserver;

import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("evaluation")
public class App {
	
	@Context
    private UriInfo info;

	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public String get() {
		// Take the query string and execute the evaluator method
		URI uri = info.getRequestUri();
		String query = uri.getQuery();		
        return Evaluator.doEvaluation(query);
	}
}
