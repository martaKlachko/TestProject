
import dao.Degree;
import dao.Department;
import dao.Lector;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class Main {


    public static void main(String[] args) {


        Session session = HibernateSessionFactory.getSessionFactory().openSession();


        //task1
       HibernateUtil.headOfDepartment("інформатика");
        //task2
        HibernateUtil. departmentStatistics("інформатика");
        //task3
        HibernateUtil.avgSalaryOfDepartment("інформатика");
        //task4
        HibernateUtil.employeeCount("інформатика");
        //task5
        HibernateUtil.globalSearch("к");


        session.close();


    }
}
