package edu.neu.cs5200.orm.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("movie")
public class MovieWebService {
	MovieDao mdao = new MovieDao();
	ActorDao adao = new ActorDao();
	
	@Path("json") 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAllMoviesAsJson() {
		List<Movie> movies = mdao.findAllMovies();
		return movies;
	}
	
	
	@Path("movie/{mid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Movie findMovieById (@PathParam("id") int id) {
		Movie movie = mdao.findMovieById(id);
		return movie;
	}
	
	@Path("movie/{mid}/actor")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> findActorByMovieId (@PathParam("id") int id) {
		Movie movie = mdao.findMovieById(id);
		List<Actor> actors = movie.getActors();
		return actors;
	}
	
}
