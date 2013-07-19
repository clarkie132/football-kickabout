package org.jboss.tools.examples.test;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dcsoft.football.model.Fixture;
import com.dcsoft.football.model.League;
import com.dcsoft.football.model.Team;
import com.dcsoft.football.service.FixtureService;

@RunWith(Arquillian.class)
public class FixtureServiceTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")        		
        		.addPackages(true, "com.dcsoft.football")                
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")             
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }
    
	@Inject 
	FixtureService fixtureService;
    
	@Test
	public void testCreateFixtures() {
		Team team1 = new Team("Team 1");
		Team team2 = new Team("Team 2");
		Team team3 = new Team("Team 3");
		Team team4 = new Team("Team 4");
		
		List<Team> teams = new ArrayList<Team>();
		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
		teams.add(team4);
		
		League league = new League();
		league.setTeams(teams);
		
	
		
		List<Fixture> fixtures = fixtureService.createFixtures(league);
		assertEquals(12, fixtures.size());
		for(Fixture fixture : fixtures) {
			System.out.println(fixture);
		}
		
		
		
		
	}

}
