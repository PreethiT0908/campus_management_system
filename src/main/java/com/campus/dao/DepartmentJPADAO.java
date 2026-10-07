package com.campus.dao;

import com.campus.model.Departments;
import com.campus.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class DepartmentJPADAO {

    public List<Departments> getAllDepartments() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM Department d ORDER BY d.id", Departments.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Departments getDepartmentById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Departments.class, id);
        } finally {
            em.close();
        }
    }

    public Departments getDepartmentByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Departments> list = em.createQuery("SELECT d FROM Department d WHERE LOWER(d.name) = LOWER(:name)", Departments.class)
                    .setParameter("name", name != null ? name.trim() : "")
                    .getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    public void addDepartment(Departments department) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}