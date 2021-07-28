package com.spingboot.dao;

import com.spingboot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MSSQL")
public class PersonDataAccessService implements PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String InsertSQL = PeopleMapper.InsertSQL;
        Object[] params = new Object[] { id,person.getName() };
        return this.jdbcTemplate.update(InsertSQL,params);
    }

    @Override
    public List<Person> selectAllPeople() {
        String SQL = PeopleMapper.SelectSQL;
        PeopleMapper peopleMapper = new PeopleMapper();
        List<Person> list = this.jdbcTemplate.query(SQL, peopleMapper);
        System.out.println(list.size());
        return list;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        String SQL = PeopleMapper.SelectSQL + " where id = ?";
        Object[] params = new Object[] { id };
        PeopleMapper peopleMapper = new PeopleMapper();
        Person person = this.jdbcTemplate.queryForObject(SQL, peopleMapper,params);
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String deleteSQL = PeopleMapper.DeleteSQL;
        Object[] params = new Object[] { id };
        return this.jdbcTemplate.update(deleteSQL,params);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        String updateSQL = PeopleMapper.UpdateSQL;
        Object[] params = new Object[] { person.getName(),id };
        return this.jdbcTemplate.update(updateSQL,params);
    }
}
