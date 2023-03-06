package comp3350.acci.business.implementation;

import java.util.List;

import comp3350.acci.application.Services;
import comp3350.acci.objects.User;
import comp3350.acci.persistence.UserPersistence;

public class AccessUser {

    private UserPersistence userPersistence;

    public AccessUser(){
        this.userPersistence = Services.getUserPersistence();
    }

    public List<User> getUsers(){
        return userPersistence.getUsers();
    }

    //Still working on this
}
