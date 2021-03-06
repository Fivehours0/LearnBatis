import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis入门案例
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void initial() throws IOException {
        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂，建造者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产SqlSession对象，工厂模式
        session = factory.openSession();
        // 4.使用SqlSession对象创建Dao接口的代理对象，代理模式
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void testFindAll() {
        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(41, "老王");
        System.out.println(user);
    }

    @After
    public void close() throws IOException {
        // 6.释放资源
        session.close();
        in.close();
    }
}
