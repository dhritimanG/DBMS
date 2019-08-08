
package edu.neu.cs5200.orm.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MovieDao {
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public void addActorToMovie(int aId, int mId) {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, aId);
		Movie movie = em.find(Movie.class, mId);
		movie.getActors().add(actor);
		actor.getMoviesActed().add(movie);
		em.persist(movie);
		em.persist(actor);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void addDirectorToMovie(int dId, int mId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Director director = em.find(Director.class, dId);
		Movie movie = em.find(Movie.class, mId);
		movie.getDirectors().add(director);
		director.getMoviesDirected().add(movie);
		em.persist(movie);
		em.persist(director);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	
	public int createMovie(Movie movie) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(movie);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		return movie.getId();
	}
	
	public Movie findMovieById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	public void deleteMovie(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		em.remove(movie);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteAllMovies() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Movie");
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Movie> findAllMovies() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m");
		List<Movie> movies = (List<Movie>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movies;
	}

	
	public Movie findMovieByTitle(String title) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m where m.title=:title");
		query.setParameter("title", title);
		Movie movie = (Movie)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	public void renameMovie(int id, String newTitle) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		movie.setTitle(newTitle);
		em.getTransaction().commit();
	}

	public void test() {

		MovieDao mdao = new MovieDao();
		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		
		mdao.deleteAllMovies();
		
//		mdao.renameMovie(3, "Terminator 2");
		
//		List<Movie> movies = mdao.findAllMovies();
//		for(Movie m : movies) {
//			System.out.println(m.getTitle());
//		}
		
//		Movie movie = mdao.findMovieByTitle("Titanic");
//		System.out.println(movie.getTitle());

		
//		Movie titanic = new Movie("Titanic");
		
		
//		..........................................................
		Movie movie = new Movie("Blade Runner");
		Actor actor1, actor2; 
		int aid1 = 0, aid2= 0, did=0;
		String actorOneName;
		String actorTwoName;
		String directorName;
		boolean isActorPresent = false;
		boolean isDirectorPresent = false;
		
		int mid = mdao.createMovie(movie);
		
//		if(!adao.IsActorPresent("Harrison")){
//			actor1 = new Actor("Harrison", "Ford");
//			aid1 = adao.createActor(actor1);
//		}
//		if(!adao.IsActorPresent("Rutger")){
//		actor2 = new Actor("Rutger", "Scott");
//		aid2 = adao.createActor(actor2);
//	}
		
		actorOneName = "Harrison";
		actorTwoName = "Rutger";
		directorName = "Ridly";
		
		//  Check if actor1 is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorOneName)){
			isActorPresent = true;
			aid1 = adao.findActorByName(actorOneName).getId();
		}
		else {
			isActorPresent = false;
			actor1 = new Actor("Harrison", "Ford");
			aid1 = adao.createActor(actor1);
		}
		
		
//		Check if actor2 is already there in db. If yes, then return that id else create new actor
		if(adao.IsActorPresent(actorTwoName)){
			isActorPresent = true;
			aid2 = adao.findActorByName(actorTwoName).getId();
		}
		else {
			isActorPresent = false;
			actor2 = new Actor("Rutger", "Hauer");
			aid2 = adao.createActor(actor2);
		}
		
		mdao.addActorToMovie(aid1, mid);
		mdao.addActorToMovie(aid2, mid);
		
//		Check if director is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorName)) {
			isDirectorPresent = true;
			did = ddao.findDirectorByName(directorName).getId();
		}
		else {
			Director director1 = new Director("Ridly", "Scott");
			did = ddao.createDirector(director1);
		}
		
		mdao.addDirectorToMovie(did, mid);
		
		
// ****************************************************************************************
		
		actorOneName = "Harrison";
		actorTwoName = "Karen";
		directorName = "Steven";
		movie = new Movie("Raiders of the Lost Ark");
		mid = mdao.createMovie(movie);
		
		//  Check if actor1 is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorOneName)){
			isActorPresent = true;
			aid1 = adao.findActorByName(actorOneName).getId();
		}
		else {
			isActorPresent = false;
			actor1 = new Actor("Harrison", "Ford");
			aid1 = adao.createActor(actor1);
		}
		
		
//		Check if actor2 is already there in db. If yes, then return that id else create new actor
		if(adao.IsActorPresent(actorTwoName)){
			isActorPresent = true;
			aid2 = adao.findActorByName(actorTwoName).getId();
		}
		else {
			isActorPresent = false;
			actor2 = new Actor("Karen", "Allen");
			aid2 = adao.createActor(actor2);
		}
		
		mdao.addActorToMovie(aid1, mid);
		mdao.addActorToMovie(aid2, mid);
		
//		Check if director is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorName)) {
			isDirectorPresent = true;
			did = ddao.findDirectorByName(directorName).getId();
		}
		else {
			Director director1 = new Director("Steven", "Spielberg");
			did = ddao.createDirector(director1);
		}
		
		
		mdao.addDirectorToMovie(did, mid);
		
// ************************************************************************************
		actorOneName = "Richard";
		actorTwoName = "Melinda";
		directorName = "Steven";
		movie = new Movie("Close Encounters of the Third Kind");
		mid = mdao.createMovie(movie);
		
		//  Check if actor1 is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorOneName)){
			isActorPresent = true;
			aid1 = adao.findActorByName(actorOneName).getId();
		}
		else {
			isActorPresent = false;
			actor1 = new Actor("Richard", "Dreyfus");
			aid1 = adao.createActor(actor1);
		}
		
		
//		Check if actor2 is already there in db. If yes, then return that id else create new actor
		if(adao.IsActorPresent(actorTwoName)){
			isActorPresent = true;
			aid2 = adao.findActorByName(actorTwoName).getId();
		}
		else {
			isActorPresent = false;
			actor2 = new Actor("Melinda", "Dillon");
			aid2 = adao.createActor(actor2);
		}
		
		mdao.addActorToMovie(aid1, mid);
		mdao.addActorToMovie(aid2, mid);
		
//		Check if director is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorName)) {
			isDirectorPresent = true;
			did = ddao.findDirectorByName(directorName).getId();
		}
		else {
			Director director1 = new Director("Steven", "Spielberg");
			did = ddao.createDirector(director1);
		}
		
		
		mdao.addDirectorToMovie(did, mid);
		
		
// *********************************************************************************
		
		List<Movie> movieGetAll = mdao.findAllMovies();
		for(Movie movieEach : movieGetAll) {
			System.out.println(movieEach.getTitle());
			for(Actor actorEach : movieEach.getActors()) {
				System.out.println(actorEach.getFirstName() + " " + actorEach.getLastName());
			}
			for(Director directorEach : movieEach.getDirectors()) {
				System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
			}
		}
		
		
		
		
		
//		System.out.println(adao.findActorByName("Harrison").getLastName());
//		System.out.println(adao.IsActorPresent("Rutger"));
		
		
//		..............................................................
//		movie = new Movie("Avatar");
//		mdao.createMovie(movie);
		
//		Movie movie = mdao.findMovieById(2);
//		System.out.println(movie.getTitle());
		
//		mdao.deleteMovie(2);
	
	}
	
	public static void main(String[] args) {
		MovieDao mdao = new MovieDao();
		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		
		mdao.deleteAllMovies();
		
//		mdao.renameMovie(3, "Terminator 2");
		
//		List<Movie> movies = mdao.findAllMovies();
//		for(Movie m : movies) {
//			System.out.println(m.getTitle());
//		}
		
//		Movie movie = mdao.findMovieByTitle("Titanic");
//		System.out.println(movie.getTitle());

		
//		Movie titanic = new Movie("Titanic");
		
		
//		..........................................................
		Movie movie = new Movie("Blade Runner");
		Actor actor1, actor2; 
		int aid1 = 0, aid2= 0, did=0;
		String actorOneName;
		String actorTwoName;
		String directorName;
		boolean isActorPresent = false;
		boolean isDirectorPresent = false;
		
		int mid = mdao.createMovie(movie);
		
//		if(!adao.IsActorPresent("Harrison")){
//			actor1 = new Actor("Harrison", "Ford");
//			aid1 = adao.createActor(actor1);
//		}
//		if(!adao.IsActorPresent("Rutger")){
//		actor2 = new Actor("Rutger", "Scott");
//		aid2 = adao.createActor(actor2);
//	}
		
		actorOneName = "Harrison";
		actorTwoName = "Rutger";
		directorName = "Ridly";
		
		//  Check if actor1 is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorOneName)){
			isActorPresent = true;
			aid1 = adao.findActorByName(actorOneName).getId();
		}
		else {
			isActorPresent = false;
			actor1 = new Actor("Harrison", "Ford");
			aid1 = adao.createActor(actor1);
		}
		
		
//		Check if actor2 is already there in db. If yes, then return that id else create new actor
		if(adao.IsActorPresent(actorTwoName)){
			isActorPresent = true;
			aid2 = adao.findActorByName(actorTwoName).getId();
		}
		else {
			isActorPresent = false;
			actor2 = new Actor("Rutger", "Hauer");
			aid2 = adao.createActor(actor2);
		}
		
		mdao.addActorToMovie(aid1, mid);
		mdao.addActorToMovie(aid2, mid);
		
//		Check if director is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorName)) {
			isDirectorPresent = true;
			did = ddao.findDirectorByName(directorName).getId();
		}
		else {
			Director director1 = new Director("Ridly", "Scott");
			did = ddao.createDirector(director1);
		}
		
		mdao.addDirectorToMovie(did, mid);
		
		
// ****************************************************************************************
		
		actorOneName = "Harrison";
		actorTwoName = "Karen";
		directorName = "Steven";
		movie = new Movie("Raiders of the Lost Ark");
		mid = mdao.createMovie(movie);
		
		//  Check if actor1 is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorOneName)){
			isActorPresent = true;
			aid1 = adao.findActorByName(actorOneName).getId();
		}
		else {
			isActorPresent = false;
			actor1 = new Actor("Harrison", "Ford");
			aid1 = adao.createActor(actor1);
		}
		
		
//		Check if actor2 is already there in db. If yes, then return that id else create new actor
		if(adao.IsActorPresent(actorTwoName)){
			isActorPresent = true;
			aid2 = adao.findActorByName(actorTwoName).getId();
		}
		else {
			isActorPresent = false;
			actor2 = new Actor("Karen", "Allen");
			aid2 = adao.createActor(actor2);
		}
		
		mdao.addActorToMovie(aid1, mid);
		mdao.addActorToMovie(aid2, mid);
		
//		Check if director is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorName)) {
			isDirectorPresent = true;
			did = ddao.findDirectorByName(directorName).getId();
		}
		else {
			Director director1 = new Director("Steven", "Spielberg");
			did = ddao.createDirector(director1);
		}
		
		
		mdao.addDirectorToMovie(did, mid);
		
// ************************************************************************************
		actorOneName = "Richard";
		actorTwoName = "Melinda";
		directorName = "Steven";
		movie = new Movie("Close Encounters of the Third Kind");
		mid = mdao.createMovie(movie);
		
		//  Check if actor1 is already there in db. If yes, then return that id else create new actor	
		if(adao.IsActorPresent(actorOneName)){
			isActorPresent = true;
			aid1 = adao.findActorByName(actorOneName).getId();
		}
		else {
			isActorPresent = false;
			actor1 = new Actor("Richard", "Dreyfus");
			aid1 = adao.createActor(actor1);
		}
		
		
//		Check if actor2 is already there in db. If yes, then return that id else create new actor
		if(adao.IsActorPresent(actorTwoName)){
			isActorPresent = true;
			aid2 = adao.findActorByName(actorTwoName).getId();
		}
		else {
			isActorPresent = false;
			actor2 = new Actor("Melinda", "Dillon");
			aid2 = adao.createActor(actor2);
		}
		
		mdao.addActorToMovie(aid1, mid);
		mdao.addActorToMovie(aid2, mid);
		
//		Check if director is already there in db. If yes, then return that id else create new actor
		if(ddao.isDirectorPresent(directorName)) {
			isDirectorPresent = true;
			did = ddao.findDirectorByName(directorName).getId();
		}
		else {
			Director director1 = new Director("Steven", "Spielberg");
			did = ddao.createDirector(director1);
		}
		
		
		mdao.addDirectorToMovie(did, mid);
		
		
// *********************************************************************************
		
		List<Movie> movieGetAll = mdao.findAllMovies();
		for(Movie movieEach : movieGetAll) {
			System.out.println(movieEach.getTitle());
			for(Actor actorEach : movieEach.getActors()) {
				System.out.println(actorEach.getFirstName() + " " + actorEach.getLastName());
			}
			for(Director directorEach : movieEach.getDirectors()) {
				System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
			}
		}
		
		
		
		
		
//		System.out.println(adao.findActorByName("Harrison").getLastName());
//		System.out.println(adao.IsActorPresent("Rutger"));
		
		
//		..............................................................
//		movie = new Movie("Avatar");
//		mdao.createMovie(movie);
		
//		Movie movie = mdao.findMovieById(2);
//		System.out.println(movie.getTitle());
		
//		mdao.deleteMovie(2);
	}

}