package com.sisyphuswxg.spring.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.naming.Name;
import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {

    private ApplicationContext ctx = null;
    private JdbcTemplate jdbcTemplate;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    {
        ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        employeeDAO = ctx.getBean(EmployeeDAO.class);
        departmentDAO = ctx.getBean(DepartmentDAO.class);
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
    }

    /**
     * 使用具名参数时, 可以使用 update(String sql, SqlParameterSource paramSource) 方法进行更新操作
     * 1. SQL 语句中的参数名和类的属性一致!
     * 2. 使用 SqlParameterSource 的 BeanPropertySqlParameterSource 实现类作为参数.
     */
    @Test
    public void testNamedParameterJdbcTemplate2(){
        String sql = "insert into employees(last_name, email, department_id) values(:lastName, :email, :deptId)";
        Employee employee = new Employee();
        employee.setLastName("XYZ");
        employee.setEmail("xyz@qq.com");
        employee.setDeptId(100);

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    /**
        可以为参数起名字，不用再去对应位置，直接对应参数名，便于维护
        缺点：麻烦
     */
    @Test
    public void testNamedParameterJdbcTemplate(){
        String sql = "insert into employees(last_name, email, department_id) values(:ln, :email, :deptId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ln", "yyy");
        paramMap.put("email", "yyy@qq.com");
        paramMap.put("deptId", 90);

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Test
    public void testDepartmentDao(){
        System.out.println(departmentDAO.get(100));
    }

    @Test
    public void testEmployeeDao(){
        System.out.println(employeeDAO.get(100));
    }

    /**
    从数据库中获取一条记录，实际得到对应的一个对象
    需要调用queryForObject
     1. 其中的RowMapper指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
     2. 使用SQL中列的别名完成列名和类的属性名的映射，例如last_name lastName
     3. 不支持级联属性，JdbcTemplate到底是一个JDBC的小工具而不是ORM框架
     */
    @Test
    public void testQueryForObject(){
        String sql = "select employee_id, last_name lastName, email, department_id deptId from employees where employee_id = ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 100);
        System.out.println(employee);
    }

    //统计单个列的值，或统计查询
    @Test
    public void testQueryForObject2(){
        String sql = "select count(*) from employees";
        long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println("count of table employess: " + count);
    }

    //查到实体类的集合
    // - 注意调用的不是queryForList()方法
    @Test
    public void testQueryForList(){
        String sql = "select employee_id, last_name lastName, email, department_id deptId from employees where employee_id > ?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employees = jdbcTemplate.query(sql, rowMapper, 200);
        System.out.println(employees);
    }

    //批量操作
    //最后一个参数是Object[]的List类型
    @Test
    public void testBatchUpdate(){
        String sql = "insert into employees(first_name, last_name) values(?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"wang1", "xuguang1"});
        batchArgs.add(new Object[]{"wang2", "xuguang2"});
        jdbcTemplate.batchUpdate(sql, batchArgs);

    }

    @Test
    public void testUpdate(){
        String sql = "update employees set last_name = ? where employee_id = ?";
        jdbcTemplate.update(sql, "xuguang", "100");
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
