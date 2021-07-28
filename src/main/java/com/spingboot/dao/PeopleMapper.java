package com.spingboot.dao;

import com.spingboot.model.Person;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PeopleMapper implements RowMapper<Person> {

    public static final String SelectSQL = "select * from People";
    public static final String InsertSQL = "insert into People values (?,?)";
    public static final String UpdateSQL = "UPDATE People set username = ? where id = ?";
    public static final String DeleteSQL = "delete from People where id = ?";

    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        UUID id = UUID.fromString(rs.getString("id"));
        String fullName = rs.getString("username");
        System.out.println(id + "-" + fullName);
        return new Person(id, fullName);
    }

}
