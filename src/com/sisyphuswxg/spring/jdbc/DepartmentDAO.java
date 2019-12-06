package com.sisyphuswxg.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDAO extends JdbcDaoSupport {

    @Autowired
    public void setDataSource2(DataSource dataSource){
        setDataSource(dataSource);
    }

    public Department get(Integer id){
        String sql = "select department_id id, department_name name from departments where department_id = ?";
        RowMapper<Department> rowMapper = new BeanPropertyRowMapper<>(Department.class);
        return getJdbcTemplate().queryForObject(sql, rowMapper, id);
    }

}
