/**
 * 
 */
package com.testbusiness.mapper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.testbusiness.model.UserModel;

/**
 * Mapper
 * @author Juan Camilo Pedraza
 *
 */
@Service
public final class UserMapper {

	
	/**
	 * Mapper UserModel
	 * 
	 * @param jsonObj
	 * @return
	 */
	public UserModel mapperUserModel(JSONObject jsonObj) {
		UserModel UserResponse = new UserModel();
        UserResponse.setId(Long.parseLong(jsonObj.get("id").toString()));  
        UserResponse.setName(jsonObj.getString("employee_name").toString());
        UserResponse.setAge(Long.parseLong(jsonObj.get("employee_age").toString()));
        UserResponse.setSalary(Long.parseLong(jsonObj.get("employee_salary").toString()));
        UserResponse.setSalary_annual(this.getSalaryAnnual(jsonObj.get("employee_salary").toString()));
		return UserResponse;
	}
	
	/**
	 * Mapper List User Model
	 * 
	 * @param listUsr
	 * @param data
	 */
	public List<UserModel> mapperListUsers( JSONArray data) {
        List<UserModel> listUsr = new ArrayList<UserModel>();
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
	
}
