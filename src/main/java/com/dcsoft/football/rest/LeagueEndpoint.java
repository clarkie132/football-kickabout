package com.dcsoft.football.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.dozer.Mapper;

import com.dcsoft.football.model.DataTableWrapper;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.dto.LeagueDTO;

/**
 * 
 */
@Stateless
@Path("/leagues")
public class LeagueEndpoint
{
   @PersistenceContext(unitName = "primary")
   private EntityManager em;
   
   @Inject
   Mapper mapper;

   @POST   
   public Response create(League entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(LeagueEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      League entity = em.find(League.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<League> findByIdQuery = em.createQuery("SELECT l FROM League l LEFT JOIN FETCH l.teams LEFT JOIN FETCH l.fixtures WHERE l.id = :entityId", League.class);
      findByIdQuery.setParameter("entityId", id);
      League entity = findByIdQuery.getSingleResult();
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<LeagueDTO> listAll()
   {
      final List<League> results = em.createQuery("SELECT l FROM League l", League.class).getResultList();
      List<LeagueDTO> leagues = new ArrayList<LeagueDTO>();
      for(League league : results) {    	  
    	  LeagueDTO destObject =  
    			    mapper.map(league, LeagueDTO.class);
    	  leagues.add(destObject);
      }
      return leagues; 
   }
   
   @GET
   @Produces("application/json")
   @Path("/aadata")
   
   public DataTableWrapper listAllAaData(@QueryParam("_") String echo)
   {	  
	   List<League> results = new ArrayList<League>();
	   League league = new League();
	   league.setDivision(1);
	   league.setName("Premier");
	   league.setId(new Long(1));
	   results.add(league);
      //final List<League> results = em.createQuery("SELECT l FROM League l LEFT JOIN FETCH l.fixtures", League.class).getResultList();
	  DataTableWrapper dataTableWrapper = new DataTableWrapper();
	  dataTableWrapper.setAaData(results);
	  dataTableWrapper.setsEcho(echo);
      return dataTableWrapper;
   }


   @PUT
   @Path("/{id:[0-9][0-9]*}")   
   public Response update(@PathParam("id") Long id, League entity)
   {
      entity.setId(id);
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}