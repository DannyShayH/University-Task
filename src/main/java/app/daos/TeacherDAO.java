package app.daos;

import app.entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;


public class TeacherDAO implements IDAO <Teacher>{
    EntityManagerFactory emf;

    public TeacherDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Teacher create(Teacher teacher) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
            return teacher;
        }
    }

    @Override
    public Teacher getById(int id) {
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Teacher.class, id);
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Teacher updatedTeacher = em.merge(teacher);
            em.getTransaction().commit();
            return updatedTeacher;
        }
    }

    @Override
    public Teacher delete(int id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Teacher teacherToDelete = em.find(Teacher.class, id);
            if (teacherToDelete != null) {
                em.remove(teacherToDelete);
            }
            em.getTransaction().commit();
            return teacherToDelete;
        }
}

    @Override
            public Set<Teacher> getAll(){
        try(EntityManager em = emf.createEntityManager()){
            return new HashSet<>(em.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList());
        }
            }
}
