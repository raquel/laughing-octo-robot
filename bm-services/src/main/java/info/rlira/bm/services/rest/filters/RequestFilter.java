package info.rlira.bm.services.rest.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Provider
@PreMatching
public class RequestFilter implements ContainerRequestFilter {
 
    private final static Logger log = Logger.getLogger( RequestFilter.class.getName() );
 
    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {
        log.info( "Executing REST request filter" );
        
        String path = requestCtx.getUriInfo().getPath();
        log.info( "Filtering request path: " + path );
        
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            requestCtx.abortWith( Response.status( Response.Status.OK ).build() );
            return;
        }
    }
 }