package com.caching.proj.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    @Autowired
    public UserService() {
    }

    @PostConstruct
    private void fillUsers(){
        users.add(new User("user_1",1));
        users.add(new User("user_2",2));
        users.add(new User("user_3",3));
        users.add(new User("user_4",4));
        users.add(new User("user_5",5));
        users.add(new User("user_6",6));
    }

    public List<User>findAll(){
        simulateSlowService(3000L);
        return this.users;
    }

    private void simulateSlowService(long mills){
        try {
            Thread.sleep(mills);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
