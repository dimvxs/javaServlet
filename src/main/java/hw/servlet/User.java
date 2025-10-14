package hw.servlet;

public class User {
private String login;
private String password;

public void setLogin(String login)
{
	this.login = login;
}

public User()
{
	login = "";
	password = "";
}

public User(String login, String password)
{
	this.login = login;
	this.password = password;
}

public void setPassword(String password)
{
	this.password = password;
}

public String getPassword()
{
	return this.password;
}

public String getLogin()
{
	return this.login;
}



}
