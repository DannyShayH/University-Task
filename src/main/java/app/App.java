package app;

import app.config.HibernateConfig;
import app.daos.CourseDAO;
import app.daos.StudentDAO;
import app.daos.TeacherDAO;
import app.entities.Course;
import app.entities.CourseName;
import app.entities.Student;
import app.entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    public static void initiate() {
        EntityManager em = emf.createEntityManager();

        System.out.println("App initiated");
        StudentDAO studentDao = new StudentDAO(emf);
        CourseDAO courseDAO = new CourseDAO(emf);
        TeacherDAO teacherDAO = new TeacherDAO(emf);

        Teacher teacher1 = Teacher.builder()
                .email("ohanne@gmail.com")
                .name("Johanne1")
                .zoom("werty.dk")
                .build();

        Course course = Course.builder()
                .courseName(CourseName.SCIENCE)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .teacher(teacher1)
                .build();

        Student student = Student.builder()
                .email("Niels@Gmail.com")
                .createdAt(LocalDateTime.now())
                .name("Niels").course(course)
                .updatedAt(LocalDateTime.now())
                .build();

        teacherDAO.create(teacher1);
        courseDAO.create(course);

        studentDao.create(student);
        course.addStudent(student);
        courseDAO.update(course);

        System.out.println(teacher1);
        System.out.println(course);
        System.out.println(student);

        em.close();
        emf.close();
        System.out.println("App terminated");
    }
}
