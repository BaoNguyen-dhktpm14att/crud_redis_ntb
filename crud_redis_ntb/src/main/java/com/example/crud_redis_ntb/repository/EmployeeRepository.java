package com.example.crud_redis_ntb.repository;

import com.example.crud_redis_ntb.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;//crud hash
    private RedisTemplate redisTemplate;

    //private ListOperations listOperations;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
       // this.hashOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;

    }

    public void saveEmployee(Employee employee){
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
    }
    public List<Employee> findAll(){


        return hashOperations.values("EMPLOYEE");
    }
    public Employee findById(Integer id){

        return (Employee) hashOperations.get("EMPLOYEE", id);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }
    public void delete(Integer id){
        hashOperations.delete("EMPLOYEE", id);
    }
}
