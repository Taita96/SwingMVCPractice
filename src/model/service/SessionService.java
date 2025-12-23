package model.service;

import model.entity.User;

public class SessionService {

    private static User currentUser;

    public static void login(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLogged() {
        return currentUser != null;
    }
}
