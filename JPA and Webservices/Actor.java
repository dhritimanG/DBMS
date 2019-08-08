package edu.neu.cs5200.orm.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
/**
 * Entity implementation class for Entity: Actor
 *
 */
@Entity
public class Actor extends Person implements Serializable {
		
	private int oscarNominations;
	
	public int getOscarNominations() {
		return oscarNominations;
	}

	public void setOscarNominations(int oscarNominations) {
		this.oscarNominations = oscarNominations;
	}

	@ManyToMany(mappedBy="actors", cascade=CascadeType.ALL)
	private List<Movie> moviesActed = new ArrayList();
	public Actor(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public List<Movie> getMoviesActed() {
		return moviesActed;
	}

	public void setMoviesActed(List<Movie> movies) {
		this.moviesActed = movies;
		for(Movie movie: movies) {
			movie.getActors().add(this);
		}
	}

	private static final long serialVersionUID = 1L;

	public Actor() {
		super();
	}
   
}