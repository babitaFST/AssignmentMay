package FSTTrainingAssignmnt03;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import io.restassured.response.Response;

public class testBase {
	
	public void post_CreateUser(String jsonBody, String url, String contentType, String contentAppl, int statusCode)
	{
		Response resp=given().body(jsonBody).header(contentType,contentAppl).when().post(url);
		System.out.println("Response Body :"+resp.asString());
		assertEquals(resp.getStatusCode(),statusCode);
		System.out.println("Status Code:"+resp.getStatusCode());

	}
	

	public void put_updateUserEscapechar(String jsonBody, String url, String contentType, String contentAppl, int statusCode)
	{
		Response resp=given().body(jsonBody).header(contentType,contentAppl).when().put(url);
		System.out.println("Response Body :"+resp.asString());
		System.out.println("Status Code:"+resp.getStatusCode());
		assertEquals(resp.getStatusCode(),statusCode);	
	}
	

	public void get_GetUser(int id, String url, String msg, int statusCode)
	{
		url=url+"/"+id;
		System.out.println(url);
		
		Response resp=given().when().get(url);
		
		System.out.println("Response Body :"+resp.asString());
		System.out.println("Message :"+resp.path(msg).toString());
		System.out.println("Status Code:"+resp.getStatusCode());
		assertEquals(resp.getStatusCode(),statusCode);
	}
	
	
	public void deleteUser(int id, String url, String msg, int statusCode)
	{
		url=url+"/"+id;
		System.out.println(url);
		Response resp=given().when().delete(url);
		System.out.println("Response Body :"+resp.asString());
		System.out.println("Status Code:"+resp.getStatusCode());
		System.out.println(resp.getStatusLine());
		assertEquals(resp.getStatusCode(),statusCode);
	}

}
