package edu.neu.cs5200.orm.jpa;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		MovieDao mdao = new MovieDao();
		MovieLibraryDao mldao = new MovieLibraryDao();

		adao.test();
		ddao.test();
		mdao.test();
		mldao.test();
	}

}
