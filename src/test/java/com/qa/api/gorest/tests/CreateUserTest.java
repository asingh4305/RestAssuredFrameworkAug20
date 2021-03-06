package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.pojo.User;
import com.qa.gorest.restclient.RestClient;
import com.qa.gorest.util.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;

/**
 * 
 * @author ASHUTOSH SINGH
 *
 */

@Epic("Create user goRest api feature implementation.....")
@Feature("Create user api feature.....")

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "4796aea4838ce5487518c706d49cc847b57e4196b52f0921539697e2d44bf060";
	
	
	@DataProvider
	public Object[][] getUserData() {
		Object [][] userData = ExcelUtil.getTestData("userdata");
		return userData;
	}
	
	
	@Test(dataProvider = "getUserData")
	public void createUserAPIPOSTTest(String name, String email, String gender, String status) {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+ token);
		
		User user = new User(name,  email,  gender,  status);
		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
		
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		System.out.println("================================");		
	}
}
