import dao.IStudentMapper;
import dao.ITeacherMapper;
import domain.Student;
import domain.Teacher;
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
    private ITeacherMapper teacherDao;
    private IStudentMapper studentDao;

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
        teacherDao = session.getMapper(ITeacherMapper.class);
        studentDao = session.getMapper(IStudentMapper.class);
    }


    @Test
    public void testFindById() {
        Teacher teacher = teacherDao.getById(1);
        System.out.println(teacher);
    }

    @Test
    public void testGetAllStudent() {
        List<Student> students = studentDao.getAllStudent();
        for(Student student: students) {
            System.out.println(student);
        }
    }

    @After
    public void close() throws IOException {
        // 6.释放资源
        session.close();
        in.close();
    }
}
