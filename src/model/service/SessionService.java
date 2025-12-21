package model.service;

import model.entity.User;

public class SessionService {

    private User LoginService;

    public User getLoginService() {
        return LoginService;
    }

    public void setLoginService(User loginService) {
        LoginService = loginService;
    }
}
