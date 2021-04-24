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
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", 0);
        map.put("pageSize", 3);
        List<User> users = userDao.getLimit(map);
        for (User user: users) {
            System.out.println(user);
        }
        // 6.释放资源
        session.close();
        in.close();
    }
}
