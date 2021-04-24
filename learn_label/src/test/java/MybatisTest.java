import dao.IUserDao;
import domain.QueryVo;
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
import java.util.Date;
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
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产SqlSession对象
        session = factory.openSession();
        // 4.使用SqlSession对象创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        // 6.释放资源
        session.close();
        in.close();
    }

    @Test
    public void findAllTest() {
        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void saveUserTest() {
        // 5.使用代理对象执行方法
        User user = new User(51, "dd", new Date(), "男", "上海");
        userDao.saveUser(user);
        // 提交事务
        session.commit();
    }

    @Test
    public void testUpdate() {
        // 5.使用代理对象执行方法(本来name值是dd，update之后改为了gg)
        User user = new User(51, "gg", new Date(), "女", "江苏");
        userDao.updateUser(user);
        // 提交事务
        session.commit();
    }

    @Test
    public void testDelete() {
        // 5.使用代理对象执行方法
        userDao.deleteUser(50);
        // 提交事务
        session.commit();
    }

    @Test
    public void testFindById() {
        // 5.使用代理对象执行方法
        User user = userDao.findById(51);
        System.out.println(user);
    }

    @Test
    public void testgetTotalNum() {
        // 5.使用代理对象执行方法
        int count = userDao.getTotalCount();
        System.out.println(count);
    }

    @Test
    public void testFindByQueryVo() {
        User user = new User();
        user.setUsername("%王%"); // %表示任意字符都可以，模糊查找
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u: users) {
            System.out.println(u);
        }
    }
}
