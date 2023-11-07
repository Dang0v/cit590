import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 * @author Qiwen Luo
 * @pennid 28188168
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	/**
	 * Inserts given actor and his/her movies into database
	 * @param actor
	 * @param movies
	 * @param actorsInfo
	 */
	public void insertActor (String actor, String [] movies, ArrayList<Actor> actorsInfo) {
		// formatting actor's name
		String actor_formatted = actor.trim().toLowerCase();
		// formatting movies
		String [] movies_formatted = new String[movies.length];
		for (int i = 0; i < movies.length; i++) {
            String str_formatted = movies[i].trim().toLowerCase();
            movies_formatted[i] = str_formatted;
        }
		
		// create a empty Actor to insert or update
		Actor actor_insert = null;
		
		// search for actor
		for(Actor actor_current : actorsInfo) {
			// if exist, we set this to actor_insert
			if (actor_current.getName().contains(actor_formatted)) {
				actor_insert = actor_current;
				break;
			}
		}
		
		// if not exist, create a new Actor
		if (actor_insert == null) {
			actor_insert = new Actor(actor_formatted);
			// add movies to this new Actor
			for (String movie : movies) {
				actor_insert.getMoviesCast().add(movie);
			}
			// add new Actor to actorsInfo
			actorsInfo.add(actor_insert);
		
		// if exist, we add new movies
		} else {
			// check every new movies to add
			for (String movie_to_add : movies_formatted) {
				// if new movie not exist, then add
				if (actor_insert.getMoviesCast().contains(movie_to_add) == false) {
					actor_insert.getMoviesCast().add(movie_to_add);
				}
			}
		}
	}
	
	/**
	 * Inserts given ratings for given movie into database
	 * @param movie
	 * @param ratings
	 * @param moviesInfo
	 */
	public void insertRating (String movie, int [] ratings, ArrayList<Movie> moviesInfo) {
		// formatting movie
		String movie_formatted = movie.trim().toLowerCase();
		// check ratings
		// invalid rating number do nothing
		if (ratings.length != 2) return;
		// invalid rating value do nothing
		for (int elements : ratings) {
			if (elements < 0 || elements > 100) return;
		}
		
		// create an empty movie
		Movie movie_insert = null;
		
		// search for movie
		for (Movie movie_current : moviesInfo) {
			if (movie_current.getName().equals(movie_formatted)) {
				// if exist, we set this to movie_insert
				movie_insert = movie_current;
				break;
			}
		}
		
		// if not exist
		if (movie_insert == null) {
			// create a new movie
			movie_insert = new Movie(movie_formatted, ratings[0], ratings[1]);
			// add to moviesInfo
			moviesInfo.add(movie_insert);
		// if movie exist, update scores
		} else {
			movie_insert.setCriticRating(ratings[0]);
			movie_insert.setAudienceRating(ratings[1]);
		}
	}

	/**
	 * Given an actor, returns the list of all movies
	 * Given a non-existent actor, this method should return an empty list
	 * 
	 * @param actor
	 * @param actorsInfo
	 * @return ArrayList String of movies
	 */
	public ArrayList<String> selectWhereActorIs (String actor, ArrayList<Actor> actorsInfo) {
		// formatting actor's name
		String actor_formatted = actor.trim().toLowerCase();
		// create an ArrayList to store movies
		ArrayList <String> movies_with_actor = new ArrayList <String> ();
		
		// searching for all elements
		for (Actor element : actorsInfo) {
			// if actor exist
			if (element.getName().contains(actor_formatted)) {
				movies_with_actor.addAll(element.getMoviesCast());
			}
		}
		// not exist
		return movies_with_actor;
	}
	
	/**
	 * Given a movie, returns the list of all actors in that movie
	 * Given a non-existent movie, this method should return an empty list
	 * 
	 * @param movie
	 * @param actorsInfo
	 * @return ArrayList String of actors
	 */
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo) {
		// formatting movie
		String movie_formatted = movie.trim().toLowerCase();
		// create an ArrayList to store the actors
		ArrayList <String> actors_in_movie = new ArrayList <String> ();
		
		// searching for all elements
		for (Actor element : actorsInfo ) {
			if (element.getMoviesCast().contains(movie_formatted)) {
				actors_in_movie.add(element.getName());
			}
		}
		return actors_in_movie;
	}
	
	/**
	 * This useful method returns a list of movies that satisfy an inequality or equality, based on the 
	 * comparison argument and the targeted rating argument.
	 * comparison is either ‘=’, ‘>’, or ‘< ‘ and is passed in as a char
	 * isCritic is a boolean that represents whether we are interested in the critics rating or the audience rating.
	 * true = critic ratings, false = audience ratings.
	 * targetRating is an integer
	 *  
	 * @param comparison
	 * @param targetRating
	 * @param isCritic
	 * @param moviesInfo
	 * @return ArrayList String of movies
	 */
	public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo) {
		// create an empty ArrayList to store the movies
		ArrayList <String> movies_result = new ArrayList <String> ();
		
		// target rating validation
		if (targetRating < 0 || targetRating > 100) return movies_result;
		
		
		// comparison validation and classification
		if (comparison == '<') {
			for (Movie movie_current : moviesInfo) {
				if (isCritic) {
					if (movie_current.getCriticRating() < targetRating) movies_result.add(movie_current.getName());
				} else {
					if (movie_current.getAudienceRating() < targetRating) movies_result.add(movie_current.getName());
				}
			}
		} else if (comparison == '=') {
			for (Movie movie_current : moviesInfo) {
				if (isCritic) {
					if (movie_current.getCriticRating() == targetRating) movies_result.add(movie_current.getName());
				} else {
					if (movie_current.getAudienceRating() == targetRating) movies_result.add(movie_current.getName());
				}
			}
		} else if (comparison == '>') {
			for (Movie movie_current : moviesInfo) {
				if (isCritic) {
					if (movie_current.getCriticRating() > targetRating) movies_result.add(movie_current.getName());
				} else {
					if (movie_current.getAudienceRating() > targetRating) movies_result.add(movie_current.getName());
				}
			}
		} else {
			// invalid comparison
			// nothing will be done, the result will be empty
		}	
		
		return movies_result;
	}
	
	/**
	 * Returns a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself.
	 * @param actor
	 * @param actorsInfo
	 * @return ArrayList String of actors
	 */
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo){
		// formatting actor's name
		String actor_formatted = actor.trim().toLowerCase();
		
		// create a CoActors list
		ArrayList <String> coactors = new ArrayList <String>();
		
		// there is no need to check actor existence, if not exist, the return list will be empty
		
		// create a movie list
		ArrayList <String> movies = new ArrayList <String>();
		movies.addAll(this.selectWhereActorIs(actor_formatted, actorsInfo));
		
		// add all actors 
		for (String movie : movies) {
			ArrayList <String> movie_actors = this.selectWhereMovieIs(movie, actorsInfo);
			// remove the actor himself/herself
			movie_actors.remove(actor_formatted);
			// add to ArrayList
			coactors.addAll(movie_actors);
		}

		return coactors;
	}
	
	/**
	 * Returns a list of movie names where both actors were cast.
	 * @param actor1
	 * @param actor2
	 * @param actorsInfo
	 * @return ArrayList String of Movie
	 */
	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo){
		// formatting actor's name
		String actor1_formatted = actor1.trim().toLowerCase();
		String actor2_formatted = actor2.trim().toLowerCase();
		
		// there is no need to check actor existence, if not exist, the return list will be empty
		
		// get movies of this 2 actors
		ArrayList <String> movies1 = this.selectWhereActorIs(actor1_formatted, actorsInfo);
		ArrayList <String> movies2 = this.selectWhereActorIs(actor2_formatted, actorsInfo);
		
		// create an empty ArrayList
		ArrayList <String> comovies = new ArrayList <String> ();
		
		// compare 2 list and add the common movie into common movie list
		for (String movie1 : movies1) {
			if (movies2.contains(movie1)) {
				comovies.add(movie1);
			}
		}
		
		return comovies;
	}
	
	/**
	 * Returns a list of movie names that both critics and the audience have rated above 85 (>= 85).
	 * @param moviesInfo
	 * @return ArrayList String of Movie
	 */
	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
		// create an empty ArrayList
		ArrayList <String> goodmovies = new ArrayList <String> ();
		
		// get goodMovies of critical score
		ArrayList <String> goodmovies_c = this.selectWhereRatingIs('>', 84, true, moviesInfo);
		ArrayList <String> goodmovies_a = this.selectWhereRatingIs('>', 84, false, moviesInfo);
		
		// compare 2 list and add the common movie into common movie list
		for (String movie_c : goodmovies_c) {
			if (goodmovies_a.contains(movie_c)) {
				goodmovies.add(movie_c);
			}
		}
		for (String element : goodmovies) {
			System.out.println(element);
		}
		
		return goodmovies;
	}
	
	/**
	 * Given a pair of movies, this method returns a list of actors that acted in both movies.
	 * @param movie1
	 * @param movie2
	 * @param actorsInfo
	 * @return ArrayList String of actors
	 */
	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo) {
		// formatting actor's name
		String movie1_formatted = movie1.trim().toLowerCase();
		String movie2_formatted = movie2.trim().toLowerCase();
		
		// there is no need to check movie existence, if not exist, the return list will be empty
		
		// get movies of this 2 actors
		ArrayList <String> actors1 = this.selectWhereMovieIs(movie1_formatted, actorsInfo);
		ArrayList <String> actors2 = this.selectWhereMovieIs(movie2_formatted, actorsInfo);
		
		// create an empty ArrayList
		ArrayList <String> coactors = new ArrayList <String> ();
		
		// compare 2 list and add the common movie into common movie list
		for (String actor1 : actors1) {
			if (actors2.contains(actor1)) {
				coactors.add(actor1);
			}
		}
		
		return coactors;
	}
	
	
	/**
	 * Given the moviesInfo DB, this static method returns the mean value of the critics ratings and the audience ratings.
	 * @param moviesInfo
	 * @return critics ratings and audience ratings
	 */
	public static double [] getMean (ArrayList <Movie> moviesInfo) {
		// create a double array
		double [] mean = new double[2];
		
		// create 2 sum variables
		int sum_c = 0;
		int sum_a = 0;
		
		// get sum of all score
		for (Movie movie : moviesInfo) {
			sum_c += movie.getCriticRating();
			sum_a += movie.getAudienceRating();
		}
		
		// get mean
		mean[0] = (double) sum_c / moviesInfo.size();
		mean[1] = (double) sum_a / moviesInfo.size();
		
		return mean;
	}
}
