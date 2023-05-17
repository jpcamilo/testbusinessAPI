/**
 * 
 */
package com.testbusiness.service;

import java.util.List;

import com.testbusiness.model.UserModel;

/**
 * @author PedrazJ1
 *
 */
public interface UserService {

	
    /**
     * Return all users.
     * 
     * @return lista Users.
     */
    public abstract List<UserModel> getAll();
    
    /**
     * Return a user by id.
     * 
     * @return User.
     */
    public abstract UserModel getById(int id);	
}
