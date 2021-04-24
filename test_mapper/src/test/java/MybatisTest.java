import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis入门案例
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂，建造者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产SqlSession对象，工厂模式
        SqlSession session = factory.openSession();
        // 4.使用SqlSession对象创建Dao接口的代理对象，代理模式
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 5.使用代理对象执行方法
        // 这里使用map而不是实体类来传递参数，parameterType设置为map。这是野路子，不用创建对象
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", "map_insert");
        map.put("id", 51);
        userDao.updateUser(map);
        session.commit();
        // 6.释放资源
        session.close();
        in.close();
    }
}
