/**
 * 
 */
package com.testbusiness.controller.test;

import java.util.ArrayList;
import java.util.List;

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

import com.testbusiness.controller.UserController;
import com.testbusiness.exceptions.NotFoundException;
import com.testbusiness.model.UserModel;
import com.testbusiness.service.UserService;

/**
 * @author Juan Camilo Pedraza
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
    
    @InjectMocks
    private  UserController controller;
    
    @Mock
    private UserService service;
    
    @Test
    @DisplayName("GET - Get Users List.")
    void ConsultaUsuarios() {
    	Mockito.when(service.getAll()).thenReturn(new ArrayList<UserModel>());
    	ResponseEntity<List<Object>> response = this.controller.getAllUsers();
    	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "expected response code is obtained");
    }

    @Test
    @DisplayName("GET - Get Users List. EXCEPTION")
    void ConsultaUsuariosException() {
    	Mockito.when(service.getAll()).thenThrow(NotFoundException.class);
    	Assertions.assertThrows(NotFoundException.class, () -> this.controller.getAllUsers(), "The expected incidence occurs.");
    }

	@Test
    @DisplayName("GET - Get User.")
    void GetUserTest() {
		Mockito.when(service.getById(Mockito.anyInt())).thenReturn(new UserModel());
    	ResponseEntity<Object> response = this.controller.getUserById(1);
    	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "expected response code is obtained");
    }    
	@Test
    @DisplayName("GET - Get User. EXCEPTION")
    void GetUserTestFail() {
		Mockito.when(service.getById(Mockito.anyInt())).thenThrow(NotFoundException.class);
    	Assertions.assertThrows(NotFoundException.class, () -> this.controller.getUserById(1), "The expected incidence occurs.");
    }        
}
