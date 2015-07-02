package info.rlira.bm.services.rest.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * {@link }
 * 
 * @author Raquel Lira (raquel.lira@gmail.com) - 27.06.2015
 * 
 */
@Provider
@PreMatching
public class ResponseFilter implements ContainerResponseFilter {
 
    private final static Logger log = Logger.getLogger( ResponseFilter.class.getName() );
 
    @Override
    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
        log.info( "Executing REST response filter" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );//TODO UPDATE AFTER DEFINITIVE URL FROM FRONT-END
        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
        responseCtx.getHeaders().add( "Access-Control-Allow-Headers", "Content-Type,Origin,Accept,Authorization");
        responseCtx.getHeaders().add("Access-Control-Max-Age", "120");
    }
}