
import dao.Degree;
import dao.Department;
import dao.Lector;
import javafx.scene.shape.Circle;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void menu() {
        System.out.println("Menu: ");
        System.out.println("To show the head of department: press 1 ");
        System.out.println("To show department statistics: press 2 ");
        System.out.println("To show the average salary for department: press 3");
        System.out.println("To show count of employee of department: press 4 ");
        System.out.println("To make global search: press 5 ");
        System.out.println("To exit: press 0 ");


        String answer = sc.next();

        switch (answer) {
            case "1":
                System.out.println("enter department name");
                HibernateUtil.headOfDepartment(sc.next());
                continueWork();
                break;
            case "2":
                System.out.println("enter department name");
                HibernateUtil.departmentStatistics(sc.next());
                continueWork();
                break;
            case "3":
                System.out.println("enter department name");
                HibernateUtil.avgSalaryOfDepartment(sc.next());
                continueWork();
                break;
            case "4":
                System.out.println("enter department name");
                HibernateUtil.employeeCount(sc.next());
                continueWork();
                break;
            case "5":
                System.out.println("enter pattern to search");
                HibernateUtil.globalSearch(sc.next());
                continueWork();
                break;
            case "0":
                System.out.println("exit");
                sc.close();
                break;
            default:
                System.out.println("wrong input!!!");
                menu();
                break;
        }
    }


    public static void continueWork() {

        System.out.println("To continue press 1, to exit press 2");

        String d = sc.next();
        switch (d) {
            case "1":
                menu();
                break;
            case "2":
                System.out.println("Work is done.");
                sc.close();
                break;
            default:
                System.out.println("Wrong input!please answer again!");
                continueWork();
                break;

        }
    }

    public static void main(String[] args) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        menu();
        session.close();

    }
}


