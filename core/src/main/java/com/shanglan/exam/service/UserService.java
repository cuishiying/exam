package com.shanglan.exam.service;

import com.shanglan.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuishiying on 2017/6/21.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
