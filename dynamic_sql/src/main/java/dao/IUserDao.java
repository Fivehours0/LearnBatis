package dao;

import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 插入一条数据
     */
    void addUser(User user);

    /**
     * 根据if确定查询的条件
     */
    List<User> getUserIf(Map<String, Object> map);

    /**
     * 根据where确定查询的条件
     */
    List<User> getUserWhere(Map<String, Object> map);

    /**
     * 根据choose, when, otherwise确定查询的条件
     * @param map
     * @return
     */
    List<User> getUserChoose(Map<String, Object> map);

    /**
     * 根据set确定查询的条件
     */
    void updateUserSet(Map<String, Object> map);

    /**
     * sql片段实现代码复用
     * @param map
     * @return
     */
    List<User> getUserSql(Map<String, Object> map);

    /**
     * 通过foreach方式查询user
     * @param map
     * @return
     */
    List<User> getUserForeach(Map<String, Object> map);
}
