/**
 * 
 */
package com.testbusiness.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testbusiness.model.UserModel;
import com.testbusiness.service.UserService;

/**
 * @author PedrazJ1
 *
 */
@RestController
public class UserController {
	
	private UserService service;
    // convert the JSON object to a Java object
    ObjectMapper mapper = new ObjectMapper();
	/**
	 * @param service
	 */
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}


	@RequestMapping("/users")
    public List<Object> callExternalService() throws JsonProcessingException {
		List<UserModel> response = this.service.getAll();
		JSONArray data = new JSONArray(response);
        return data.toList();
    }

	@RequestMapping("/users/{id}")
    public String getUserBiId(@PathVariable("id") int itemId) {
		UserModel response = this.service.getById(itemId);
		System.out.println(response);
		return response.toString();
    }

}
