package com.djzhao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * JDBCController
 *
 * @author djzhao
 * @date 20/03/19 9:51
 * @email djzhao627@gmail.com
 */

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public List<Map<String, Object>> getUsers() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/add")
    public String addUser() {
        String sql = "insert into user(username, password) values ('小明', '123456')";
        int update = jdbcTemplate.update(sql);
        return update > 0 ? "OK" : "NO";
    }

    @GetMapping("/update")
    public String updateUser() {
        String sql = "update user set password = ? where username = ?";
        String[]  params = new String[2];
        params[0] = "000000";
        params[1] = "小明";
        int update = jdbcTemplate.update(sql, params);
        return update > 0 ? "OK" : "NO";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from user where id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update > 0 ? "OK" : "NO";
    }
}
