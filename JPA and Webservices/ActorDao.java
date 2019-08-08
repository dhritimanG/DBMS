
package edu.neu.cs5200.orm.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ActorDao {
	private static final String UNIT = "JPAWeb";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public int createActor(Actor actor) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(actor);
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		
		return actor.getId();
	}
	
	public Actor findActorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return actor;
	}
	
//	public Actor findFirstActor() {
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		
//		Query query = em.createQuery("select a from Actor a where a.id=:id");
//		query.setParameter("id", 1);
//		Actor actor = (Actor)query.getSingleResult();
//		
//		em.getTransaction().commit();
//		em.close();
//		
//		return actor;
//	}
	
//	public Actor findFirstActor() {
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		
//		Query query = em.createQuery("select a from Actor a where a.id=:id limit 1");
//		Actor actor = (Actor)query.getSingleResult();
//		
//		em.getTransaction().commit();
//		em.close();
//		
//		return actor;
//	}
	
	public List<Actor> findFirstActor() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a");
		query.setMaxResults(1);
		List<Actor> actors = (List<Actor>)query.setMaxResults(1).getResultList();
		
		em.getTransaction().commit();
		em.close();
		return actors;
	}

	
	public Actor findActorByName(String firstName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = null;
		
		try {
		Query query = em.createQuery("select a from Actor a where a.firstName=:firstName");
		query.setParameter("firstName", firstName);
		actor = (Actor)query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Actor not found! Sorry!");
		}
		
		em.getTransaction().commit();
		em.close();
		
		return actor;
	}
	
	public boolean IsActorPresent(String firstName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = null;
		boolean isPresent = true;
		
		try {
		Query query = em.createQuery("select a from Actor a where a.firstName=:firstName");
		query.setParameter("firstName", firstName);
		actor = (Actor)query.getSingleResult();
		} catch (Exception e) {
			isPresent = false;
			return isPresent;
		}
		
		em.getTransaction().commit();
		em.close();
		
		return isPresent;
	}
	
	
//	public void deleteActor(int id) {
//		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		
//		Actor actor = em.find(Actor.class, id);
//		em.remove(actor);
//		
//		em.getTransaction().commit();
//		em.close();
//	}
	
	public void deleteActor(String oldFirstName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a where a.firstName=:firstName");
		query.setParameter("firstName", oldFirstName);
		Actor actor = (Actor)query.getSingleResult();
		em.remove(actor);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Actor> findAllActors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a");
		List<Actor> actors = (List<Actor>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return actors;
	}
	
	public void renameActor(String oldFirstName, String newFirstName, String newLastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select a from Actor a where a.firstName=:firstName");
		query.setParameter("firstName", oldFirstName);
		Actor actor = (Actor)query.getSingleResult();
		actor.setFirstName(newFirstName);
		actor.setLastName(newLastName);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteAllActors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Actor");
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	public void test() {

		
		ActorDao dao = new ActorDao();
		
		//1
		dao.deleteAllActors();
		
		//2
		Actor actor1 = new Actor("Sigorney", "Weaver");
		actor1.setOscarNominations(3);
		dao.createActor(actor1);
		
		Actor actor2 = new Actor("Dan", "Creg");
		actor2.setOscarNominations(3);
		dao.createActor(actor2);
		
		Actor actor3 = new Actor("Jim", "Carrey");
		actor3.setOscarNominations(3);
		dao.createActor(actor3);
		
		// 3
		List<Actor> actorsGet = dao.findFirstActor();
		for(Actor actorGet : actorsGet) {
			System.out.println(actorGet.getFirstName() + " " + actorGet.getLastName());
		}
		
		
		// 4
		List<Actor> actorGetAll = dao.findAllActors();
		for(Actor actorEach : actorGetAll) {
			System.out.println(actorEach.getFirstName() + " " + actorEach.getLastName());
		}
		
		//5
		dao.renameActor("Dan", "Daniel", "Craig");
//		Actor findActor = dao.findActorByName("Daniel");
//		System.out.println(findActor.getFirstName() + " " + findActor.getLastName());
		
		//6
		dao.deleteActor("Jim");
		
		// 7
		List<Actor> actorGetAllFinal = dao.findAllActors();
		for(Actor actorEachFinal : actorGetAllFinal) {
			System.out.println(actorEachFinal.getFirstName() + " " + actorEachFinal.getLastName());
		}
		
		
		
		
//		Actor actorGet = dao.findActorById(1);
//		System.out.println(actorGet.getFirstName() + " " + actorGet.getLastName());
		
//		Actor actorGet = dao.findFirstActor();
//		System.out.println(actorGet.getFirstName() + " " + actorGet.getLastName());
		
//		dao.renameActor("Dan", "Daniel", "Craig");
//		Actor findActor = dao.findActorByName("Dan");
//		System.out.println(findActor.getFirstName() + " " + findActor.getLastName());
		
//		dao.deleteActor("Jim");
		
//		dao.deleteAllActors();
		
//		List<Actor> actorGetAll = dao.findAllActors();
//		for(Actor actorEach : actorGetAll) {
//			System.out.println(actorEach.getFirstName() + " " + actorEach.getLastName());
//		}
				
//		dao.deleteActor(1);
		
//		dao.createActor(actor);
	
	}
	
	public static void main(String[] args) {
		
		ActorDao dao = new ActorDao();
		
		//1
		dao.deleteAllActors();
		
		//2
		Actor actor1 = new Actor("Sigorney", "Weaver");
		actor1.setOscarNominations(3);
		dao.createActor(actor1);
		
		Actor actor2 = new Actor("Dan", "Creg");
		actor2.setOscarNominations(3);
		dao.createActor(actor2);
		
		Actor actor3 = new Actor("Jim", "Carrey");
		actor3.setOscarNominations(3);
		dao.createActor(actor3);
		
		// 3
		List<Actor> actorsGet = dao.findFirstActor();
		for(Actor actorGet : actorsGet) {
			System.out.println(actorGet.getFirstName() + " " + actorGet.getLastName());
		}
		
		
		// 4
		List<Actor> actorGetAll = dao.findAllActors();
		for(Actor actorEach : actorGetAll) {
			System.out.println(actorEach.getFirstName() + " " + actorEach.getLastName());
		}
		
		//5
		dao.renameActor("Dan", "Daniel", "Craig");
//		Actor findActor = dao.findActorByName("Daniel");
//		System.out.println(findActor.getFirstName() + " " + findActor.getLastName());
		
		//6
		dao.deleteActor("Jim");
		
		// 7
		List<Actor> actorGetAllFinal = dao.findAllActors();
		for(Actor actorEachFinal : actorGetAllFinal) {
			System.out.println(actorEachFinal.getFirstName() + " " + actorEachFinal.getLastName());
		}
		
		
		
		
//		Actor actorGet = dao.findActorById(1);
//		System.out.println(actorGet.getFirstName() + " " + actorGet.getLastName());
		
//		Actor actorGet = dao.findFirstActor();
//		System.out.println(actorGet.getFirstName() + " " + actorGet.getLastName());
		
//		dao.renameActor("Dan", "Daniel", "Craig");
//		Actor findActor = dao.findActorByName("Dan");
//		System.out.println(findActor.getFirstName() + " " + findActor.getLastName());
		
//		dao.deleteActor("Jim");
		
//		dao.deleteAllActors();
		
//		List<Actor> actorGetAll = dao.findAllActors();
//		for(Actor actorEach : actorGetAll) {
//			System.out.println(actorEach.getFirstName() + " " + actorEach.getLastName());
//		}
				
//		dao.deleteActor(1);
		
//		dao.createActor(actor);
	}

}