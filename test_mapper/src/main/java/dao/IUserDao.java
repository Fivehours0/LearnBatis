package dao;

import java.util.Map;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 更新用户
     */
    void updateUser(Map<String, Object> map);

}
