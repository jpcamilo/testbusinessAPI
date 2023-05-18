/**
 * 
 */
package com.testbusiness.mapper;

import java.util.List;

import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testbusiness.model.UserModel;

/**
 * @author Juan Camilo Pedraza
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
	
	@InjectMocks
	UserMapper mapper;
	
    @Test
    @DisplayName("Map User Model")
	void mapperUserModelTest(){
    	JSONObject object = new JSONObject();
    	object.put("id", 1);
    	object.put("employee_name", "Dummy name");
    	object.put("employee_age", 20);
    	object.put("employee_salary", 10);
		UserModel response = mapper.mapperUserModel(object);
		Assertions.assertEquals(response.getId(), Long.parseLong(object.get("id").toString()));
		Assertions.assertEquals(response.getAge(), Long.parseLong(object.get("employee_age").toString()));
		Assertions.assertEquals(response.getName(), object.get("employee_name").toString());
		Assertions.assertEquals(response.getSalary(), Long.parseLong(object.get("employee_salary").toString()));
		Assertions.assertEquals(response.getSalary_annual(), Long.parseLong(object.get("employee_salary").toString())*12);
	}

    @Test
    @DisplayName("Map List User Model")
	void mapperListUsersTest(){
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
    	List<UserModel> response = mapper.mapperListUsers(data);
    	Assertions.assertEquals(response.size(), 2);
    	Assertions.assertEquals(response.get(0).getId(), Long.parseLong(data.getJSONObject(0).get("id").toString()));
    	Assertions.assertEquals(response.get(1).getId(), Long.parseLong(data.getJSONObject(1).get("id").toString()));
    }
    
}
