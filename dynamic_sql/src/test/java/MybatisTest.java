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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    public void testAddUser() {
        User user = new User(50,"insert",new Date(),"男","江苏");
        userDao.addUser(user);
        session.commit();
    }

    @Test
    public void testGetUserIf() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("address", "北京%");
        map.put("username", "小二王");
        List<User> users = userDao.getUserIf(map);
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserWhere() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("address", "北京%");
        map.put("username", "小二王");
        List<User> users = userDao.getUserWhere(map);
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserChoose() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("address", "北京%");
        map.put("username", "小二王");
        map.put("id", 40);
        List<User> users = userDao.getUserChoose(map);
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUserSet() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("address", "北京");
        map.put("username", "小二王");
        map.put("id", 46);
        userDao.updateUserSet(map);
        session.commit();
    }

    @Test
    public void testGetUserSql() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("address", "北京%");
        map.put("username", "小二王");
        List<User> users = userDao.getUserSql(map);
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserForeach() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
//        ids.add(41);
//        ids.add(42);
        map.put("ids", ids);
        List<User> users = userDao.getUserForeach(map);
        for (User user: users) {
            System.out.println(user);
        }
        System.out.println(ids.toString());
    }
}
