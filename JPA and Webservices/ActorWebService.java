package edu.neu.cs5200.orm.jpa;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("actor")
public class ActorWebService {
	MovieDao mdao = new MovieDao();
	ActorDao adao = new ActorDao();
	
	@Path("json") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAllActorsAsJson() {
		List<Actor> actors = adao.findAllActors();
		return actors;
	}
	
	@Path("actor/{aid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Actor findActorById (@PathParam("id") int id) {
		Actor actor = adao.findActorById(id);
		return actor;
	}
	
	@Path("actor/{aid}/movie")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> findActorByMovieId (@PathParam("id") int id) {
		Actor actor = adao.findActorById(id);
		List<Movie> movies = actor.getMoviesActed();
		return movies;
	}
}
