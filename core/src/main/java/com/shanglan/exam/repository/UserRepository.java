package com.shanglan.exam.repository;

import com.shanglan.exam.entity.TestPaperType;
import com.shanglan.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by cuishiying on 2017/6/21.
 */
public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

    User findByUid(Integer uid);

    @Query("select u from User u where  u.username =?1 and u.truename=?2")
    User findUserByUsernameAndtruename(String username, String truename);

}
