/**
 * 
 */
package com.testbusiness.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.testbusiness.exceptions.NotFoundException;
import com.testbusiness.mapper.UserMapper;
import com.testbusiness.model.UserModel;
import com.testbusiness.service.impl.UserServiceImpl;

/**
 * @author Juan Camilo Pedraza
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl service;
	
	@Mock
	RestTemplate restTemplate;
	
	
	@Test
	@DisplayName("get all Users")
	void getAllTest() {
    	JSONArray data = new JSONArray();
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
    	data.put(object);
    	data.put(object2);
		ResponseEntity<Object> responseUrl = new ResponseEntity<Object>(data, HttpStatus.OK);
		

		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(responseUrl);
		Exception e = null;
        try {
        	this.service.getAll();
        } catch (Exception ex) {
            e = ex;
        }
        
        Assertions.assertNotNull(e, "no exception occurs");
	}

	@Test
	@DisplayName("get all Users EXCEPCION" )
	void getAllTestFail() {

		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenThrow(HttpClientErrorException.class);
		Assertions.assertThrows(HttpClientErrorException.class, () -> this.service.getAll(), "The expected incidence occurs.");
	}	
	
	@Test
	@DisplayName("get a User")
	void getUserTest() {

    	JSONObject object = new JSONObject();
    	object.put("id", 1);
    	object.put("employee_name", "Dummy name");
    	object.put("employee_age", 20);
    	object.put("employee_salary", 10);

    	ResponseEntity<Object> responseUrl = new ResponseEntity<Object>(object, HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(responseUrl);
		Exception e = null;
        try {
        	this.service.getById(1);
        } catch (Exception ex) {
            e = ex;
        }
        
        Assertions.assertNotNull(e, "no exception occurs");
	}

	@Test
	@DisplayName("get a User EXCEPCION" )
	void getUserTestFail() {

		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any())).thenThrow(HttpClientErrorException.class);
		Assertions.assertThrows(HttpClientErrorException.class, () -> this.service.getById(1), "The expected incidence occurs.");
	}		
}
