package edu.neu.cs5200.orm.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MovieLibraryDao {
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
		Persistence.createEntityManagerFactory(UNIT);

	
	
	public void createMovieLibrary(MovieLibrary library) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(library);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void addMovieToLibrary(int mId, int lId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, mId);
		MovieLibrary library = em.find(MovieLibrary.class, lId);
		library.getMovies().add(movie);
		movie.setLibrary(library);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void test() {
		MovieLibrary favoriteMovies = new MovieLibrary("Favorite Movies");
		MovieLibraryDao dao = new MovieLibraryDao();
		dao.createMovieLibrary(favoriteMovies);
		
		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		MovieDao mdao = new MovieDao();
		
		int aidA, aidB, aidC, aidD;
		int didA, didB;
		
		boolean isActorPresent = false;
		boolean isDirectorPresent = false;
		
		// Movie: Star Wars A New Hope
		Movie movie1 = new Movie("Star Wars A New Hope");
		Actor actorA = new Actor("Mark", "Hamill");
		Actor actorB = new Actor("Carrie", "Fisher");
		Director directorA = new Director("George", "Lucas");
		
		
		// Movie: The Revanant
		Movie movie2 = new Movie("The Revanant");
		Actor actorC = new Actor("Leonardo", "DiCaprio");
		Actor actorD = new Actor("Tom", "Hardy");
		Director directorB = new Director("Alejandro", "Inarritu");	
		
		int mid1 = mdao.createMovie(movie1);
		int mid2 = mdao.createMovie(movie2);
		
		// Search database for actors else create new **********************************************
		
		//  Check if actorA is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorA.getFirstName())){
			isActorPresent = true;
			aidA = adao.findActorByName(actorA.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidA = adao.createActor(actorA);
		}
		
		//  Check if actorB is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorB.getFirstName())){
			isActorPresent = true;
			aidB = adao.findActorByName(actorB.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidB = adao.createActor(actorB);
		}
			
		//  Check if actorC is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorC.getFirstName())){
			isActorPresent = true;
			aidC = adao.findActorByName(actorC.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidC = adao.createActor(actorC);
		}
		
		//  Check if actorD is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorD.getFirstName())){
			isActorPresent = true;
			aidD = adao.findActorByName(actorD.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidD = adao.createActor(actorD);
		}
		
		//	Check if directorA is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorA.getFirstName())) {
			isDirectorPresent = true;
			didA = ddao.findDirectorByName(directorA.getFirstName()).getId();
		}
		else {
			isDirectorPresent = false;
			didA = ddao.createDirector(directorA);
		}
		
		//	Check if directorB is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorB.getFirstName())) {
			isDirectorPresent = true;
			didB = ddao.findDirectorByName(directorB.getFirstName()).getId();
		}
		else {
			isDirectorPresent = false;
			didB = ddao.createDirector(directorB);
		}
		
		// ***************************************** // Search database for actors else create new
		
		
		// Add actors and directors to movies
		mdao.addActorToMovie(aidA, mid1);
		mdao.addActorToMovie(aidB, mid1);
		mdao.addDirectorToMovie(didA, mid1);
		
		mdao.addActorToMovie(aidC, mid2);
		mdao.addActorToMovie(aidD, mid2);
		mdao.addDirectorToMovie(didB, mid2);
		
		// Add movies to movie library
		dao.addMovieToLibrary(mid1, favoriteMovies.getId());
		dao.addMovieToLibrary(mid2, favoriteMovies.getId());
		
		List<Movie> moviesInLibrary = new ArrayList<>();
		moviesInLibrary.add(movie1);
		moviesInLibrary.add(movie2);
		favoriteMovies.setMovies(moviesInLibrary);
		
		List<Actor> actorsInMovie1 = new ArrayList<>();
		actorsInMovie1.add(actorA);
		actorsInMovie1.add(actorB);
		
		List<Director> directorsInMovie1 = new ArrayList<>();
		directorsInMovie1.add(directorA);
		
		movie1.setActors(actorsInMovie1);
		
		List<Actor> actorsInMovie2 = new ArrayList<>();
		actorsInMovie2.add(actorC);
		actorsInMovie2.add(actorD);
		
		List<Director> directorsInMovie2 = new ArrayList<>();
		directorsInMovie2.add(directorB);
		
		movie2.setActors(actorsInMovie2);

		
		for(Movie movie : favoriteMovies.getMovies()) {
			System.out.println(movie.getTitle());
			for(Actor actor : movie.getActors()) {
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			}
		}
	}
	
	public static void main(String[] args) {
		MovieLibrary favoriteMovies = new MovieLibrary("Favorite Movies");
		MovieLibraryDao dao = new MovieLibraryDao();
		dao.createMovieLibrary(favoriteMovies);
		
		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		MovieDao mdao = new MovieDao();
		
		int aidA, aidB, aidC, aidD;
		int didA, didB;
		
		boolean isActorPresent = false;
		boolean isDirectorPresent = false;
		
		// Movie: Star Wars A New Hope
		Movie movie1 = new Movie("Star Wars A New Hope");
		Actor actorA = new Actor("Mark", "Hamill");
		Actor actorB = new Actor("Carrie", "Fisher");
		Director directorA = new Director("George", "Lucas");
		
		
		// Movie: The Revanant
		Movie movie2 = new Movie("The Revanant");
		Actor actorC = new Actor("Leonardo", "DiCaprio");
		Actor actorD = new Actor("Tom", "Hardy");
		Director directorB = new Director("Alejandro", "Inarritu");	
		
		int mid1 = mdao.createMovie(movie1);
		int mid2 = mdao.createMovie(movie2);
		
		// Search database for actors else create new **********************************************
		
		//  Check if actorA is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorA.getFirstName())){
			isActorPresent = true;
			aidA = adao.findActorByName(actorA.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidA = adao.createActor(actorA);
		}
		
		//  Check if actorB is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorB.getFirstName())){
			isActorPresent = true;
			aidB = adao.findActorByName(actorB.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidB = adao.createActor(actorB);
		}
			
		//  Check if actorC is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorC.getFirstName())){
			isActorPresent = true;
			aidC = adao.findActorByName(actorC.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidC = adao.createActor(actorC);
		}
		
		//  Check if actorD is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorD.getFirstName())){
			isActorPresent = true;
			aidD = adao.findActorByName(actorD.getFirstName()).getId();
		}
		else {
			isActorPresent = false;
			aidD = adao.createActor(actorD);
		}
		
		//	Check if directorA is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorA.getFirstName())) {
			isDirectorPresent = true;
			didA = ddao.findDirectorByName(directorA.getFirstName()).getId();
		}
		else {
			isDirectorPresent = false;
			didA = ddao.createDirector(directorA);
		}
		
		//	Check if directorB is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorB.getFirstName())) {
			isDirectorPresent = true;
			didB = ddao.findDirectorByName(directorB.getFirstName()).getId();
		}
		else {
			isDirectorPresent = false;
			didB = ddao.createDirector(directorB);
		}
		
		// ***************************************** // Search database for actors else create new
		
		
		// Add actors and directors to movies
		mdao.addActorToMovie(aidA, mid1);
		mdao.addActorToMovie(aidB, mid1);
		mdao.addDirectorToMovie(didA, mid1);
		
		mdao.addActorToMovie(aidC, mid2);
		mdao.addActorToMovie(aidD, mid2);
		mdao.addDirectorToMovie(didB, mid2);
		
		// Add movies to movie library
		dao.addMovieToLibrary(mid1, favoriteMovies.getId());
		dao.addMovieToLibrary(mid2, favoriteMovies.getId());
		
		List<Movie> moviesInLibrary = new ArrayList<>();
		moviesInLibrary.add(movie1);
		moviesInLibrary.add(movie2);
		favoriteMovies.setMovies(moviesInLibrary);
		
		List<Actor> actorsInMovie1 = new ArrayList<>();
		actorsInMovie1.add(actorA);
		actorsInMovie1.add(actorB);
		
		List<Director> directorsInMovie1 = new ArrayList<>();
		directorsInMovie1.add(directorA);
		
		movie1.setActors(actorsInMovie1);
		
		List<Actor> actorsInMovie2 = new ArrayList<>();
		actorsInMovie2.add(actorC);
		actorsInMovie2.add(actorD);
		
		List<Director> directorsInMovie2 = new ArrayList<>();
		directorsInMovie2.add(directorB);
		
		movie2.setActors(actorsInMovie2);

		
		for(Movie movie : favoriteMovies.getMovies()) {
			System.out.println(movie.getTitle());
			for(Actor actor : movie.getActors()) {
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			}
		}
		
	}

}