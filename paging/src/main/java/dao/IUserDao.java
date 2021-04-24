package dao;

import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 分页操作
     * @return
     */
    List<User> getLimit(Map<String, Integer> map);
}
