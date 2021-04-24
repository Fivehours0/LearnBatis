package dao;

import domain.Teacher;

public interface ITeacherMapper {
    /**
     * 根据老师id获取老师，并获取该老师下的所有学生
     * @param id
     * @return
     */
    Teacher getById(int id);
}
