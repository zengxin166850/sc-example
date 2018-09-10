package com.example.microservicesimpleprovideruser.mapper;

import com.example.microservicesimpleprovideruser.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User findById(Long id);
}
