package app.daos;

import app.entities.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;


public class CourseDAO implements IDAO<Course> {
    EntityManagerFactory emf;

        public CourseDAO(EntityManagerFactory emf) {
            this.emf = emf;
        }
    @Override
    public Course create(Course course) {
        try(EntityManager em = emf.createEntityManager()) {
          em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return course;
        }
    }

    @Override
    public Course getById(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Course.class, id);
        }
        }

    @Override
    public Course update(Course course) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Course updatedCourse = em.merge(course);
            em.getTransaction().commit();
            return updatedCourse;
        }

        }

    @Override
    public Course delete(int id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Course courseToDelete = em.find(Course.class, id);
            if (courseToDelete != null) {
                em.remove(courseToDelete);
            }
            em.getTransaction().commit();
            return courseToDelete;
        }
        }

     @Override
    public Set<Course> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return new HashSet(em.createQuery("SELECT c FROM Course c").getResultList());
        }
    }


}
