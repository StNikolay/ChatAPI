package com.stnikolay.ChatAPI.DAO.user;

import com.stnikolay.ChatAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User registration(String username, String password) {
        jdbcTemplate.update(
                "insert into t_user(username, password) values (?, ?)",
                username, password
        );
        return findUserByUsername(username);
    }

    @Override
    public User findUserByUsername(String username) {
        return jdbcTemplate.query(
                "select * from t_user where username = ?",
                new Object[] { username },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query(
                "select * from t_user where id = ?",
                new Object[] { id },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    @Override
    public boolean checkForExistence(String username, String password) {
        return jdbcTemplate.queryForObject("select count(*) from t_user " +
                        "where username = ? and password = ?",
                Integer.class,
                username,
                password
        ) != 0;
    }
}
