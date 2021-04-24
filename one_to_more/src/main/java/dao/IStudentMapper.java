package dao;

import domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IStudentMapper {
    /**
     * 获取所有的学生信息，Student实体类中有teacher属性，将这个teacher属性对应上
     * @return
     */
    @Select("select * from student")
    List<Student> getAllStudent();
}
