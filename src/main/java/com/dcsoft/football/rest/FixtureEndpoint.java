package com.dcsoft.football.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.dcsoft.football.model.Fixture;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.Team;
import com.dcsoft.football.service.FixtureService;

/**
 * 
 */
@Stateless
@Path("/fixtures")
public class FixtureEndpoint
{
   @PersistenceContext(unitName = "primary")
   private EntityManager em;
   
   @Inject
   private FixtureService fixtureService;

   @POST
   @Consumes("application/json")
   public Response create(Fixture entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(FixtureEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Fixture entity = em.find(Fixture.class, id);
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
      TypedQuery<Fixture> findByIdQuery = em.createQuery("SELECT f FROM Fixture f LEFT JOIN FETCH f.homeTeam LEFT JOIN FETCH f.awayTeam LEFT JOIN FETCH f.result WHERE f.id = :entityId", Fixture.class);
      findByIdQuery.setParameter("entityId", id);
      Fixture entity = findByIdQuery.getSingleResult();
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Fixture> listAll()
   {
		List<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < 6; i++) {
			Team team = new Team("Team " + (i + 1));
			teams.add(team);
		}

		League league = new League();
		league.setTeams(teams);

		List<Fixture> fixtures = fixtureService.createFixtures(league);
	
//      final List<Fixture> results = em.createQuery("SELECT f FROM Fixture f LEFT JOIN FETCH f.homeTeam LEFT JOIN FETCH f.awayTeam LEFT JOIN FETCH f.result", Fixture.class).getResultList();
      return fixtures;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(@PathParam("id") Long id, Fixture entity)
   {
      entity.setId(id);
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}