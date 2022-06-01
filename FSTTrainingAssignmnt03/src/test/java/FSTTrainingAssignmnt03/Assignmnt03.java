package testCases;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Assignmnt03 extends testBase{
	
	@Test(description="perform test to CREATE user")
	public void createUser()
	{
		//creating user with escape character
		this.post_CreateUser("{\r\n  \"id\": 1,\r\n  \"first_name\": \"Babita\",\r\n  \"middle_name\": \"\",\r\n  \"last_name\": \"Fernandez\",\r\n  \"date_of_birth\": \"01-06-1981\"\r\n}", "http://thetestingworldapi.com/Help/api/studentsDetails", "Content-Type","application/json", 200);
		//this.post_CreateUser("{\r\n  \"id\": 0,\r\n  \"username\": \"ABC\",\r\n  \"firstName\": \"Abc\",\r\n  \"lastName\": \"Bcd\",\r\n  \"email\": \"abc@bbc.com\",\r\n  \"password\": \"abcbbc1\",\r\n  \"phone\": \"1234567890\",\r\n  \"userStatus\": 0\r\n}", "https://petstore.swagger.io/v2/user", "Content-Type","application/json", 200);
	}
		
	@Test(description="perform PUT operation with escape character")
	public void putEscape() throws IOException
	{
		
		this.put_updateUserEscapechar("{\r\n  \"id\": 1,\r\n  \"first_name\": \"Babita-Modified\",\r\n  \"middle_name\": \"\",\r\n  \"last_name\": \"Fernandez\",\r\n  \"date_of_birth\": \"01-06-1981\"\r\n}", "http://thetestingworldapi.com/Help/api/studentsDetails", "Content-Type", "application/json", 200);		
	}
	
	@Test(description="to perform GET modified User details")
	public void getUser()
	{
				//get User Details
	//this.get_GetUser(1,"http://thetestingworldapi.com/Help/api/studentsDetails", "message", 200);
	//	this.get_GetUser("http://thetestingworldapi.com/api/studentsDetails/1", "message", 200);
		Response res = given().when().get("http://thetestingworldapi.com/api/studentsDetails/1");
		System.out.println("Response is "+res.asString());
	//	System.out.println(res.path("data.email").toString());
		System.out.println(res.getStatusCode());
		assertEquals(res.getStatusCode(), 200);
	
	}
	
	
	@Test(description="perform DELETE operation")
	public void deleteUser()throws IOException
	{
		this.deleteUser(2663238,"http://thetestingworldapi.com/api/studentsDetails/", "message", 200);
	//URL "http://thetestingworldapi.com/Help/api/studentsDetails/" is not working
	}
	
		
}
