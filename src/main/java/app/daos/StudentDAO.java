package app.daos;

import app.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PrePersist;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;


public class StudentDAO implements IDAO<Student> {

    private static EntityManagerFactory emf;

    public StudentDAO (EntityManagerFactory emf){
        this.emf = emf;

    }

    @Override
    public Student create(Student student) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return student;
        }
    }

    @Override
    public Student getById(int id) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            em.getTransaction().commit();
            return student;

        }
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public Student delete(int id) {
        return null;
    }
    @Override
    public Set<Student> getAll() {
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultStream().collect(Collectors.toSet());
        }
    }

}
