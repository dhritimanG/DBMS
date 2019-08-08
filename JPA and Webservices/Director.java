package edu.neu.cs5200.orm.jpa;

import edu.neu.cs5200.orm.jpa.Person;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Director
 *
 */
@Entity

public class Director extends Person implements Serializable {

	@ManyToMany(mappedBy="directors", cascade=CascadeType.ALL)
	private List<Movie> moviesDirected;
	private int oscarWins;
	private static final long serialVersionUID = 1L;
	
	public Director(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public Director() {
		super();
	}   
	public List<Movie> getMoviesDirected() {
		return this.moviesDirected;
	}

	public void setMoviesDirected(List<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
		for(Movie movie: moviesDirected) {
			movie.getDirectors().add(this);
		}
	}   
	public int getOscarWins() {
		return this.oscarWins;
	}

	public void setOscarWins(int oscarWins) {
		this.oscarWins = oscarWins;
	}
   
}