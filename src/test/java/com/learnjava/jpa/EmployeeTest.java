package com.learnjava.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vikmalik
 */
public class EmployeeTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public EmployeeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            emf = Persistence.createEntityManagerFactory("com.isoftmagic_LearnJava_jar_1.0-SNAPSHOTPU");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (emf != null) {
            em = emf.createEntityManager();
        }
    }

    @AfterClass
    public static void tearDownClass() {
        if (em != null) {
            em.close();
        }
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Employee.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        if (em != null) {
            Employee emp = new Employee();
            emp.setName("Vikas");
            emp.setSalary(100.50f);

            em.getTransaction().begin();
            try {
                em.persist(emp);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }

            Long expResult = 1l;
            Long result = emp.getId();
            assertEquals(expResult, result);
        }

    }

    @Test
    public void testGetEmployees() {
        System.out.println("GetEmployees");
        if (em != null) {
            List<Employee> empList = em.createQuery("Select emp from Employee emp").getResultList();
            assertEquals(1, empList.size());
        }
    }

}
