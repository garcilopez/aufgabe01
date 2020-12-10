package httpserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;


public class HttpServer {
	
	public static void main(String[] args) throws IOException {
		// Run a http server to execute the Application
        ResourceConfig packagesResourceConfig = new DefaultResourceConfig();
        Application app = new Application() {
            @Override
            public Set<Class<?>> getClasses() {
                Set<Class<?>> res = new HashSet<Class<?>>();
                res.add(httpserver.App.class);
                return res;
            }
        };
        packagesResourceConfig.add(app);

        HttpServerFactory.create("http://localhost:8087/", packagesResourceConfig).start();
    }

}
