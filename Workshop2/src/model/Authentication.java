package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Authentication {

	private ArrayList<User> users = new ArrayList<User>();
	private ReadWriteFile readWriteFile = new ReadWriteFile();
	private boolean loggedIn = false;
	
	
	public Authentication() throws FileNotFoundException{
		this.users = readWriteFile.readFile();	
	}
	
	public void logIn(String username, String password){
		for(User u : users)
			if(u.getUsername().equals(username)){
				if(u.getPassword().equals(password))
					loggedIn = true;
				return;
			}
	}
	
	public void logOut(){
		loggedIn = false;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
}
