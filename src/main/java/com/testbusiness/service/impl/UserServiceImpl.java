/**
 * 
 */
package com.testbusiness.service.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.testbusiness.exceptions.NotFoundException;
import com.testbusiness.mapper.UserMapper;
import com.testbusiness.model.UserModel;
import com.testbusiness.service.UserService;

/**
 * Service.
 * 
 * @author Juan Camilo Pedraza
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private RestTemplate restTemplate;
	
	private UserMapper mapper;
	
    private final String URL = "https://dummy.restapiexample.com/api/v1/employees";
    private final String URL_ID = "https://dummy.restapiexample.com/api/v1/employee";
    

	/**
	 * @param restTemplate
	 * @param mapper
	 */
	public UserServiceImpl(RestTemplate restTemplate, UserMapper mapper) {
		this.restTemplate = restTemplate;
		this.mapper = mapper;
	}




	@Override
	public List<UserModel> getAll() {
		try {

			/*
			 * When the service does not work, this Dummy is used to test the application.
			 * 
			JSONArray datat = new JSONArray();
	    	JSONObject object = new JSONObject();
	    	object.put("id", 1);
	    	object.put("employee_name", "Dummy name");
	    	object.put("employee_age", 20);
	    	object.put("employee_salary", 10);
	    	JSONObject object2 = new JSONObject();
	    	object2.put("id", 1);
	    	object2.put("employee_name", "Dummy name");
	    	object2.put("employee_age", 20);
	    	object2.put("employee_salary", 10);
	    	datat.put(object);
	    	datat.put(object2);
			ResponseEntity<Object> responseUrl = new ResponseEntity<Object>(datat, HttpStatus.OK);
			JSONObject jsonObj = new JSONObject(responseUrl.getBody());
			List<UserModel> response =  mapper.mapperListUsers(datat);
			*/
			
	    	//Comment this part, if you want to use the Dummy for testing.
			ResponseEntity<String> responseURL = restTemplate.getForEntity(URL, String.class);
			JSONObject jsonObj = new JSONObject(responseURL.getBody());
			JSONArray data = jsonObj.getJSONArray("data");
			List<UserModel> response =  mapper.mapperListUsers(data);
			///////
			return response;
		} catch (HttpClientErrorException e) {
			throw new NotFoundException(HttpStatus.NOT_FOUND, e.getMessage());
		}
        
	}




	@Override
	public UserModel getById(int id) {
		try {

			/*
			 * When the service does not work, this Dummy is used to test the application.
			 * 
			JSONObject object = new JSONObject();
	    	object.put("id", 1);
	    	object.put("employee_name", "Dummy name");
	    	object.put("employee_age", 20);
	    	object.put("employee_salary", 10);
	    	
	        UserModel UserResponse = mapper.mapperUserModel(object);
	    	*/
			
	    	//Comment this part, if you want to use the Dummy for testing.
			ResponseEntity<String> responseURL = restTemplate.getForEntity(URL_ID+"/"+id, String.class);
	        JSONObject jsonObj = new JSONObject(responseURL.getBody());
	        JSONObject jsonObjMapper = jsonObj.getJSONObject("data");
	        UserModel UserResponse = mapper.mapperUserModel(jsonObjMapper);
	        //
	        
			return UserResponse;	
		} catch (Exception e) {
			throw new NotFoundException(HttpStatus.NOT_FOUND, e.getMessage());
		}
        
	}



}
