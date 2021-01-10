/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Geocache;
import entity.Logbook;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
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

/**
 *
 * @author Noel
 */
@Stateless
@Path("entity.geocache")
public class GeocacheFacadeREST extends AbstractFacade<Geocache> {

    @PersistenceContext(unitName = "NoelServidorRESTPU")
    private EntityManager em;

    public GeocacheFacadeREST() {
        super(Geocache.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Geocache entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Geocache entity) {
        super.edit(entity);
    }

    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Geocache find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    //La Query no me reconoce el parametro
    @GET
    @Path("ByHint/{hint}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Geocache> geocacheByHint(@PathParam("hint") String hint) {
        Query q = em.createNamedQuery("Geocache.findAll");
        List<Geocache> lista = q.getResultList();
        List<Geocache> resultado = new ArrayList();
        for (Geocache g: lista) {
            if (g.getHint().contains(hint)) {
                resultado.add(g);
            }
        }
        return resultado;
    }
    
    //No terminado
    @GET
    @Path("NotFound")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Geocache> geocacheNotFound() {
        LogbookFacadeREST log = new LogbookFacadeREST();
        Query q = em.createNamedQuery("Geocache.findAll");
        List<Geocache> lista = q.getResultList();
        List<Logbook> lista2 = log.NotFound();
        List<Geocache> resultado = new ArrayList();
        for (Geocache g: lista) {
            for (Logbook l: lista2) {
               // if(){
                    
                //}
            }
        }
        return resultado;
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Geocache> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Geocache> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
