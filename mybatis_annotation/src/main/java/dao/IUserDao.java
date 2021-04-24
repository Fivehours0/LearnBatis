package dao;

import domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * id对应uid
     * 多个参数必须加上@Param, @Param内的参数和sql语句内的参数名称对应
     * 关于@Param：
     *  基本类型参数或者String类型，需要加上
     *  引用类型不需要加
     *  如果只有一个基本类型的化可以忽略，建议加上
     *  sql语句中引用的就是我们这里的@Param中设定的属性名
     */
    @Select("select * from user where id=#{uid} and username=#{username}")
    User findById(@Param("uid") int id, @Param("username") String username);
}
