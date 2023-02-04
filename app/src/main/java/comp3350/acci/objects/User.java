package comp3350.acci.objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User
{
	@PrimaryKey(autoGenerate = true)
	public int uid;

	@ColumnInfo(name="user_name")
	public String userName;

	@ColumnInfo(name="bio")
	public String bio;

	public User(){
		userName = null;
		this.bio = null;
	}

	public User(String username, String bio){
		userName = username;
		this.bio = bio;
	}

	public User(int id, String username, String bio){
		uid = id;
		userName = username;
		this.bio = bio;
	}

	public String toString(){
		return "{uid: " + uid + "\nuserName: " + userName + "\nbio: "+bio+"}\n";
	}

}