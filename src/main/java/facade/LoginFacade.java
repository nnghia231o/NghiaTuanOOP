/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

import service.LoginService;

/**
 *
 * @author USER
 */
public class LoginFacade {
    private LoginService loginService = new LoginService();
    public boolean login(String username, String password) {
        return loginService.login(username, password);
    }
}
