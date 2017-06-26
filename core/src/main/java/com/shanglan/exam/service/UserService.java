package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/21.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByUsernameAndtruename(String username, String truename){
        User user = userRepository.findUserByUsernameAndtruename(username, truename);
        return user;
    }

    public User findByUid(Integer uid){
        User user = userRepository.findByUid(uid);
        return user;
    }

    public List<User> findAll(){
        List<User> all = userRepository.findAll();
        return all;
    }

    public Integer count(){
        Integer count = userRepository.count();
        return count;
    }


}
