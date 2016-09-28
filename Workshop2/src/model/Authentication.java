package model;

public class Authentication {

	private User user = new User();
	private boolean loggedIn = false;
	
	
	public Authentication(){
	
	}
	
	public void logIn(String username, String password){
		if(username.equals(user.getUsername()) && password.equals(user.getPassword()))
					loggedIn = true;	
	}
	
	public void logOut(){
		loggedIn = false;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}	
}