package com.shanglan.exam.repository;

import com.shanglan.exam.entity.TestPaperType;
import com.shanglan.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/21.
 */
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {


}
