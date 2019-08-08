package edu.neu.cs5200.orm.jpa;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity
@XmlRootElement
public class Movie implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@ManyToOne
	private MovieLibrary library;
	
	@ManyToMany
	@JoinTable(name="MOVIE2ACTOR")
	private List<Actor> actors = null;
	
	@ManyToMany
	@JoinTable(name="MOVIE2DIRECTOR")
	private List<Director> directors = null;

	private static final long serialVersionUID = 1L;

	public Movie() {
		super();
	}   
	public Movie(String title) {
		this.title = title;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public MovieLibrary getLibrary() {
		return library;
	}
	public void setLibrary(MovieLibrary library) {
		this.library = library;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
		for(Actor actor: actors) {
			actor.getMoviesActed().add(this);
		}
	}
	public List<Director> getDirectors() {
		return directors;
	}
	public void setDirectors(List<Director> directors) {
		this.directors = directors;
		for(Director director: directors) {
			director.getMoviesDirected().add(this);
		}
	}
   
}