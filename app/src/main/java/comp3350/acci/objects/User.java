package comp3350.acci.objects;

import java.util.Objects;

/*
    Name:           User
    Description:    This class represents User DSO.
    Author/Version: Ivory Lyons: 02/07/23

 */
public class User
{

	private static int count = 0;

	private int userID;

	private String userName;

	private String bio;

	public User(){
		this.userID = count;
		userName = null;
		this.bio = null;
		count++;
	}

	public User(int userID, String username, String bio){
		this.userID = userID;
		userName = username;
		this.bio = bio;
		count++;
	}

	public User(String username, String bio){
		this.userID = count;
		userName = username;
		this.bio = bio;
		count++;
	}

	public String getBio() {
		return bio;
	}

	public String getUserName() {
		return userName;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean equals(Object other) {
		boolean equals = false;

		if (other instanceof User)
		{
			final User otherUser = (User) other;
			equals = Objects.equals(this.userID, otherUser.userID);
		}

		return equals;
	}

	public String toString(){
		return "user: {userName: " + userName + " bio: "+bio+"}";
	}

}