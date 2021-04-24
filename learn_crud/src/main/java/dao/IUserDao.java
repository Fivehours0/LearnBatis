package dao;

import domain.QueryVo;
import domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     */
    void saveUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     */
    void deleteUser(int userId);

    /**
     * 根据id查询
     */
    User findById(int userId);

    /**
     * 查询总记录的条数
     */
    int getTotalCount();

    /**
     * 根据QueryVo中的条件查询用户
     * 1.貌似适合多表查询，一个表对应一个查询类
     * 2.由多个对象组成查询条件，来实现数据的查询（多个对象整合至QueryVo类中）
     */
    List<User> findUserByVo(QueryVo vo);
}
