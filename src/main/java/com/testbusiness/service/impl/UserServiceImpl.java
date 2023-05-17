/**
 * 
 */
package com.testbusiness.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testbusiness.model.UserModel;
import com.testbusiness.service.UserService;

/**
 * @author PedrazJ1
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private RestTemplate restTemplate;
	
    private final String URL = "https://dummy.restapiexample.com/api/v1/employees";
    private final String URL_ID = "https://dummy.restapiexample.com/api/v1/employees";
    
	
    /**
	 * @param restTemplate
	 */
	@Autowired
	public UserServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}	
	
	@Override
	public List<UserModel> getAll() {

        List<UserModel> listUsr = new ArrayList<UserModel>();

        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONArray data = jsonObj.getJSONArray("data"); 
        
        for (int i = 0; i < data.length(); i++) {
        	UserModel usrTmp = new UserModel();
        	usrTmp.setId(Long.parseLong(data.getJSONObject(i).get("id").toString()));  
        	usrTmp.setName(data.getJSONObject(i).getString("employee_name").toString());
        	usrTmp.setAge(Long.parseLong(data.getJSONObject(i).get("employee_age").toString()));
        	usrTmp.setSalary(Long.parseLong(data.getJSONObject(i).get("employee_salary").toString()));
        	usrTmp.setSalary_annual(this.getSalaryAnnual(data.getJSONObject(i).get("employee_salary").toString()));
        	listUsr.add(usrTmp);
        }        
        
        
		return listUsr;
	}

	private Long getSalaryAnnual(String string) {
		return Long.parseLong(string)*12;
	}

	@Override
	public UserModel getById(int id) {
        ResponseEntity<String> response = restTemplate.getForEntity(URL_ID, String.class);
        JSONObject jsonObj = new JSONObject(response.getBody());
        UserModel UserResponse = new UserModel();
        UserResponse.setId(Long.parseLong(jsonObj.get("id").toString()));  
        UserResponse.setName(jsonObj.getString("employee_name").toString());
        UserResponse.setAge(Long.parseLong(jsonObj.get("employee_age").toString()));
        UserResponse.setSalary(Long.parseLong(jsonObj.get("employee_salary").toString()));
        UserResponse.setSalary_annual(this.getSalaryAnnual(jsonObj.get("employee_salary").toString()));
        
		return UserResponse;
	}

}
