/**
 *  Basic user class for all roles
 *  
 *  @author Qiwen Luo
 */

package roles;

import java.util.HashSet;

import courses.Course;

public abstract class User {
	/**
	 * user's real name
	 */
	private String name;
	
	/**
	 * user's user name
	 */
	private String username;
	
	/**
	 * user's password
	 */
	private String password;
	
	/**
	 * user's userID
	 */
	private String userID;
	
	/**
	 * initialization of user
	 * @param name
	 * @param username
	 * @param password
	 * @param userID
	 */
	public User(String name, String username, String password, String userID) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.userID = userID;
	}
	
	/**
	 * get user's real name
	 * @return user's real name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * get user's username
	 * @return user's username
	 */
	public String getUserName() {
		return this.username;
	}
	
	/**
	 * get user's ID
	 * @return user's ID
	 */
	public String getUserID() {
		return this.userID;
	}
	
	/**
	 * Only for test
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * user password validation
	 * @param password
	 * @return true if password is correct
	 */
	public boolean userLogin(String password) {
		// compare password
		// correct password
		if (this.password.equals(password)) {
			return true;
		// incorrect password
		} else {
			return false;
		}
	}
	
	/**
	 * Check whether two course has the same intersection
	 * @param coursetoAdd
	 * @param coursetoCompare
	 * @return
	 */
	protected boolean sameDayValidation(Course coursetoAdd, Course coursetoCompare) {
		// convert days string to hash set
		HashSet<Character> settoAdd = new HashSet<>();
		HashSet<Character> settoCompare = new HashSet<>();
		for (char c : coursetoAdd.getDays().toCharArray())
			settoAdd.add(c);
		for (char c : coursetoCompare.getDays().toCharArray())
			settoCompare.add(c);
		
		// check if there is intersection
		settoAdd.retainAll(settoCompare);
		
		return !settoAdd.isEmpty();
	}
	
	/**
	 * If two course has mutual days, this will check whether the course period is overlapped
	 * @param coursetoAdd
	 * @param coursetoCompare
	 * @return true if overlapped, false if ok
	 */
	protected boolean checkTimeConflict(Course coursetoAdd, Course coursetoCompare) {
		int time_start_toadd = coursetoAdd.getStartTimeInt();
		int time_end_toadd = coursetoAdd.getEndTimeInt();
		int time_start_tocompare = coursetoCompare.getStartTimeInt();
        int time_end_tocompare = coursetoCompare.getEndTimeInt();
        if (time_start_toadd < time_end_tocompare && time_start_tocompare < time_end_toadd)
        	return true;
        else
        	return false;
	}
	
}