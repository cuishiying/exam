package com.shanglan.exam.repository;

import com.shanglan.exam.entity.TestPaperType;
import com.shanglan.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/21.
 */
@Repository
public class UserRepository{

    @Resource
    private JdbcTemplate jdbcTemplate;

    public User findUserByUsernameAndtruename(String username, String truename){
        String sql="select * from cnoa_main_user u where u.username=? and u.truename=?";

        User user = jdbcTemplate.queryForObject(sql, new Object[]{username, truename}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setDeptId(resultSet.getInt("deptId"));
                u.setUid(resultSet.getInt("uid"));
                u.setUsername(resultSet.getString("username"));
                u.setTruename(resultSet.getString("truename"));
                return u;
            }
        });
        return user;
    }



    public User findByUid(Integer uid) {
        String sql="select * from cnoa_main_user u where u.uid=?";

        User user = jdbcTemplate.queryForObject(sql, new Object[]{uid}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setDeptId(resultSet.getInt("deptId"));
                u.setUid(resultSet.getInt("uid"));
                u.setUsername(resultSet.getString("username"));
                u.setTruename(resultSet.getString("truename"));
                return u;
            }
        });
        return user;
    }

    public List<User> findAll() {
        String sql="select * from cnoa_main_user";

        List<User> list = jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setDeptId(resultSet.getInt("deptId"));
                u.setUid(resultSet.getInt("uid"));
                u.setUsername(resultSet.getString("username"));
                u.setTruename(resultSet.getString("truename"));
                return u;
            }
        });
        return list;
    }


    public Integer count(){
        String sql="select count(*) from cnoa_main_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }


}
