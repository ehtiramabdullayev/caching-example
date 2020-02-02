package com.caching.proj.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"users"})
public class UserService {
    private List<User> users = new ArrayList<>();

    @Autowired
    public UserService() {
    }

    @PostConstruct
    private void fillUsers(){
        users.add(new User(1,"user_1",1));
        users.add(new User(2,"user_2",2));
        users.add(new User(3,"user_3",3));
        users.add(new User(4,"user_4",4));
        users.add(new User(5,"user_5",5));
        users.add(new User(6,"user_6",6));
    }

    @Cacheable // caches the result of findAll() method
    public List<User>findAll(){
        simulateSlowService(3000L);
        return this.users;
    }

    /***
     * @CachePut always lets the method execute.
     * It is used to update the cache with the result of the method execution
     * it will update the cache itself,
     * and the request to findAll() will return us recently updated data.
     */
    @CachePut
    public User updateUser(User user){
        this.users.set(0,user);
        return this.users.get(0);
    }

    private void simulateSlowService(long mills){
        try {
            Thread.sleep(mills);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
