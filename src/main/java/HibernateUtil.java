import dao.Degree;
import dao.Department;
import dao.Lector;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by User on 26.03.2018.
 */
public class HibernateUtil {

    public static Optional<Department> findDepartmentByName(String departmentName) {

        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery("from Department d where d.name=:name")
                .setParameter("name", departmentName);
        Optional<Department> department = Optional.ofNullable((Department) q.uniqueResult());
        if (!department.isPresent()) {
            System.out.println("Sorry!There is no department " + departmentName + " in our university");
        }
        session.getTransaction().commit();
        return department;

    }

    public static void headOfDepartment(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();


        if (department.isPresent()) {
            session.beginTransaction();
            Query qry = session.createQuery("select p.head from Department p where p.name=:name").
                    setParameter("name", departmentName);
            Optional<Lector> head = Optional.ofNullable((Lector) qry.uniqueResult());
            if (head.isPresent()) {
                System.out.println("Head of " + departmentName + " department is " + head.get().getName());
            } else {
                System.out.println("There is no head in department " + departmentName + " now");
            }
            session.getTransaction().commit();
        }

    }

    public static void departmentStatistics(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();

        if (department.isPresent()) {
            session.beginTransaction();
            Query qry = session.createQuery("select count(l.name) " +
                    "from Lector l where l.degree=:degree and :department in elements(l.departments)");
            Long assistantsCount = (Long) qry.setParameter("degree", Degree.ASSISTANT)
                    .setParameter("department", department.get()).uniqueResult();
            Long assosiateProfessorsCount = (Long) qry.setParameter("degree", Degree.ASSOSIATE_PROFESSOR)
                    .setParameter("department", department.get()).uniqueResult();
            Long professorsCount = (Long) qry.setParameter("degree", Degree.PROFESSOR)
                    .setParameter("department", department.get()).uniqueResult();
            session.getTransaction().commit();
            System.out.println("Statistics of " + departmentName + " department");
            System.out.println("assistants - " + assistantsCount);
            System.out.println("assosiate_professors - " + assosiateProfessorsCount);
            System.out.println("professors - " + professorsCount);
        }

    }

    public static void avgSalaryOfDepartment(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();


        if (department.isPresent()) {
            session.beginTransaction();
            Query qry = session.createQuery("select avg(l.salary) from Lector l where :name in elements(l.departments) ").
                    setParameter("name", department.get());
            Double avgSalary = (Double) qry.uniqueResult();
            session.getTransaction().commit();
            System.out.println("Average salary of department " + departmentName + " is " + avgSalary);

        }
    }

    public static void employeeCount(String departmentName) {
        Optional<Department> department = findDepartmentByName(departmentName);
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();

        if (department.isPresent()) {
            session.beginTransaction();
            Query qry = session.createQuery("select count(l) from Lector l where :name in elements(l.departments) ").
                    setParameter("name", department.get());
            Long count = (Long) qry.uniqueResult();

            session.getTransaction().commit();
            System.out.println("Count of employee of department " + departmentName + " is " + count);

        }
    }

    public static void globalSearch(String template) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query q = session.createQuery("from Lector l where l.name like :template")
                .setParameter("template", "%" + template.toLowerCase() + "%");
        List<Lector> lectors = q.list();
        System.out.println("Global search by '" + template + "'");
        if (lectors.isEmpty()) {
            System.out.println("Answer: There are no lectors with '" + template + "' in name");
        } else {
            System.out.println("Answer:");
            for (Lector l : lectors
                    ) {
                System.out.println(l.getName());
            }
        }
        session.getTransaction().commit();
    }
}
