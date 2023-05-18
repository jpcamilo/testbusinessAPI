/**
 * 
 */
package com.testbusiness.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testbusiness.model.UserModel;
import com.testbusiness.service.UserService;

/**
 * Controller
 * @author Juan Camilo Pedraza
 *
 */
@RequestMapping("/testbusiness")
@RestController
public class UserController {
	
	private UserService service;
    
	/**
	 * Constructor.
	 * 
	 * @param service
	 */
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}


	@GetMapping("/users")
	@ResponseBody
    public ResponseEntity<List<Object>>  getAllUsers() {

		HttpHeaders headers = headerBuilder();
		List<UserModel> response = this.service.getAll();
		JSONArray data = new JSONArray(response);
        return new ResponseEntity<List<Object>>(data.toList(), headers, HttpStatus.OK);


    }


	@GetMapping("/users/{id}")
	@ResponseBody
    public ResponseEntity<Object> getUserById(@PathVariable("id") int itemId) {
		
		HttpHeaders headers = headerBuilder();		
		UserModel response = this.service.getById(itemId);
		JSONObject data = new JSONObject(response);
		return new ResponseEntity<Object>(data.toString(), headers, HttpStatus.OK);
    }


	/**
	 * @return
	 */
	private HttpHeaders headerBuilder() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-Custom-Header", "customValue");
		headers.setAccessControlAllowOrigin("http://localhost:4200");
		headers.setAccessControlAllowCredentials(true);
		List<String> allowH = new ArrayList<>();
		allowH.add("origin");
		allowH.add("content-type");
		allowH.add("accept");
		allowH.add("authorization");
		headers.setAccessControlAllowHeaders(allowH);
		List<HttpMethod> allowH2 = new ArrayList<>();
		allowH2.add(HttpMethod.GET);
		headers.setAccessControlAllowMethods(allowH2);
		return headers;
	}	
	
}
