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
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "com.dcsoft.football")
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml");
	}

	@Inject
	FixtureService fixtureService;

	@Test
	public void testCreateFixtures() {

		List<Team> teams = new ArrayList<Team>();
		for (int i = 0; i < 16; i++) {
			Team team = new Team("Team " + (i + 1));
			teams.add(team);
		}

		League league = new League();
		league.setTeams(teams);

		List<Fixture> fixtures = fixtureService.createFixtures(league);
		for (Fixture fixture : fixtures) {
			System.out.println(fixture);
		}
		assertEquals(240, fixtures.size());

	}

}
