package dao;

import domain.Teacher;
import org.apache.ibatis.annotations.Select;

public interface ITeacherMapper {
    @Select("select * from teacher where id=#{id}")
    Teacher getById(int id);
}
