package edu.neu.cs5200.orm.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DirectorDao {
	
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);
	
	public int createDirector(Director director) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(director);
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		
		return director.getId();
	}
	
	public Director findDirectorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Director director = em.find(Director.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return director;
	}
	
//	public Director findFirstDirector() {
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		
//		Query query = em.createQuery("select d from Director d where d.id=:id");
//		query.setParameter("id", 1);
//		Director director = (Director)query.getSingleResult();
//		
//		em.getTransaction().commit();
//		em.close();
//		
//		return director;
//	}
	
	public List<Director> findFirstDirector() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select d from Director d");
		query.setMaxResults(1);
		List<Director> directors = (List<Director>)query.setMaxResults(1).getResultList();
		
		em.getTransaction().commit();
		em.close();
		return directors;
	}
	
	public boolean isDirectorPresent(String firstName) {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Director director = null;
		boolean isPresent = true;
		
		try {
		Query query = em.createQuery("select d from Director d where d.firstName=:firstName");
		query.setParameter("firstName", firstName);
		director = (Director)query.getSingleResult();
		String directorName = director.getFirstName();
		} catch (Exception e) {
			isPresent = false;
			return isPresent;
		}
		
		em.getTransaction().commit();
		em.close();
		
		return isPresent;
	
	}
	
	public Director findDirectorByName(String firstName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select d from Director d where d.firstName=:firstName");
		query.setParameter("firstName", firstName);
		Director director = (Director)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return director;
	}
	
	public void deleteDirector(String oldFirstName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select d from Director d where d.firstName=:firstName");
		query.setParameter("firstName", oldFirstName);
		Director director = (Director)query.getSingleResult();
		em.remove(director);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Director> findAllDirectors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select d from Director d");
		List<Director> directors = (List<Director>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return directors;
	}
	
	public void renameDirector(String oldFirstName, String newFirstName, String newLastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select d from Director d where d.firstName=:firstName");
		query.setParameter("firstName", oldFirstName);
		Director director = (Director)query.getSingleResult();
		director.setFirstName(newFirstName);
		director.setLastName(newLastName);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteAllDirectors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Director");
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public void test() {

		DirectorDao dao = new DirectorDao();
		
		// 1
		dao.deleteAllDirectors();
			
		// 2
		Director director1 = new Director("Jimmy", "Camaron");
		director1.setOscarWins(3);
		dao.createDirector(director1);
		
		Director director2 = new Director("Steven", "Spielberg");
		director2.setOscarWins(3);
		dao.createDirector(director2);
		
		Director director3 = new Director("Ron", "Howard");
		director3.setOscarWins(3);
		dao.createDirector(director3);
		
		// 3
		List<Director> directorsGet = dao.findFirstDirector();
		for(Director directorGet : directorsGet) {
			System.out.println(directorGet.getFirstName() + " " + directorGet.getLastName());
		}
		
		// 4
		List<Director> directorGetAll = dao.findAllDirectors();
		for(Director directorEach : directorGetAll) {
			System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
		}
		
		// 5
		dao.renameDirector("Jimmy", "James", "Cameron");
		
		// 6
		dao.deleteDirector("Ron");
		
		// 7
		List<Director> directorGetAllFinal = dao.findAllDirectors();
		for(Director directorEach : directorGetAllFinal) {
			System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
		}
		
//		dao.createDirector(director);
		
//		dao.renameDirector("Jimmy", "James", "Cameron");
		
//		dao.deleteDirector("Steven");
		
//		List<Director> directorGetAll = dao.findAllDirectors();
//		for(Director directorEach : directorGetAll) {
//			System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
//		}
	}

	public static void main(String[] args) {
		DirectorDao dao = new DirectorDao();
		
		// 1
		dao.deleteAllDirectors();
			
		// 2
		Director director1 = new Director("Jimmy", "Camaron");
		director1.setOscarWins(3);
		dao.createDirector(director1);
		
		Director director2 = new Director("Steven", "Spielberg");
		director2.setOscarWins(3);
		dao.createDirector(director2);
		
		Director director3 = new Director("Ron", "Howard");
		director3.setOscarWins(3);
		dao.createDirector(director3);
		
		// 3
		List<Director> directorsGet = dao.findFirstDirector();
		for(Director directorGet : directorsGet) {
			System.out.println(directorGet.getFirstName() + " " + directorGet.getLastName());
		}
		
		// 4
		List<Director> directorGetAll = dao.findAllDirectors();
		for(Director directorEach : directorGetAll) {
			System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
		}
		
		// 5
		dao.renameDirector("Jimmy", "James", "Cameron");
		
		// 6
		dao.deleteDirector("Ron");
		
		// 7
		List<Director> directorGetAllFinal = dao.findAllDirectors();
		for(Director directorEach : directorGetAllFinal) {
			System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
		}
		
//		dao.createDirector(director);
		
//		dao.renameDirector("Jimmy", "James", "Cameron");
		
//		dao.deleteDirector("Steven");
		
//		List<Director> directorGetAll = dao.findAllDirectors();
//		for(Director directorEach : directorGetAll) {
//			System.out.println(directorEach.getFirstName() + " " + directorEach.getLastName());
//		}
		
		
		
	}
}
