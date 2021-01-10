/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Geocache;
import entity.Logbook;
import entity.LogbookPK;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Noel
 */
@Stateless
@Path("entity.logbook")
public class LogbookFacadeREST extends AbstractFacade<Logbook> {

    @PersistenceContext(unitName = "NoelServidorRESTPU")
    private EntityManager em;

    private LogbookPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;email=emailValue;geocache=geocacheValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entity.LogbookPK key = new entity.LogbookPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> email = map.get("email");
        if (email != null && !email.isEmpty()) {
            key.setEmail(email.get(0));
        }
        java.util.List<String> geocache = map.get("geocache");
        if (geocache != null && !geocache.isEmpty()) {
            key.setGeocache(new java.lang.Integer(geocache.get(0)));
        }
        return key;
    }

    public LogbookFacadeREST() {
        super(Logbook.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Logbook entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Logbook entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entity.LogbookPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Logbook find(@PathParam("id") PathSegment id) {
        entity.LogbookPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Path("ByEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Logbook> logbookByEmail(@PathParam("email") String email) {
        Query q = em.createNamedQuery("Logbook.findAllByEmailOrdered");
        q.setParameter("email", email);
        return q.getResultList();
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Logbook> findAll() {
        return super.findAll();
    }

    public List<Logbook> NotFound(){
        Query q = em.createNamedQuery("Logbook.findAll");
        List<Logbook> lista = q.getResultList();
        List<Logbook> resultado = new ArrayList();
        
        for (Logbook l: lista) {
            if (l.getLogbookPK().getEmail() == null) {
                resultado.add(l);
            }
        }
        return resultado;
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Logbook> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
