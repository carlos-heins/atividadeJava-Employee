package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("How many employeers will be registered? ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println();
            System.out.println("Employee #" + (i + 1) + ":");
            System.out.print("Id: ");
            Integer id = sc.nextInt();

            while(hasId(list, id)){
                System.out.println("ID already taken! Try again: ");
                id = sc.nextInt();
            }

            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Salary: ");
            Double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, salary);

            list.add(emp); // isso é muito legal
        }

        System.out.println();
        System.out.print("Enter the employee ID that will have salary increase: ");
        int idSalary = sc.nextInt();
        Integer pos = position(list, idSalary);
        if (pos == null) {
            System.out.println("This ID does not exists!");
        } else {
            System.out.print("Enter the percentage: ");
            double percentage = sc.nextDouble();

            list.get(pos).increaseSalary(percentage);;
        }

        System.out.println();
        System.out.println("List the employees: ");

        for( Employee emp : list ){
            System.out.println(emp);
        }

        sc.close();
    }

    public static Integer position(List<Employee> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static boolean hasId(List<Employee> list, int id ){
        // Outro método mais simples de fazer uma busca em uma lista (transformando em stream para fazer as operações)
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }

}
