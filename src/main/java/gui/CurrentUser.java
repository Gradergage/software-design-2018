package gui;

import users.MarkedUser;

public class CurrentUser {
    private static MarkedUser instance;

    public static MarkedUser getUser() {
        return instance;
    }
    public static void setCurrentUser(MarkedUser user)
    {
       instance = user;
    }
}
