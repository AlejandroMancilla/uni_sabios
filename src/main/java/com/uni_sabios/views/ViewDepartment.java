package com.uni_sabios.views;

import com.uni_sabios.exceptions.departmentexceptions.DepartmentNullException;
import com.uni_sabios.repository.models.Department;

public class ViewDepartment extends ViewMain {

    public static void startMenu() {
        int opc = 0;

        do {
            clear();
            opc = showMenu();
            switch (opc) {
                case 1:
                    createDepartment();
                    break;
                case 2:
                    printDepartment();
                    break;
                case 3:
                    modifyDepartment();
                    break;
                case 4:
                    deleteDepartment();
                    break;
                case 5:
                    listDepartments();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Not Available Choice");
                    sc.next();
            }
            
        }while(opc>0);
    }

        private static int showMenu() {
        System.out.println("*".repeat(10) + " Department's Menu " + "*".repeat(10));
        System.out.println("\t 1) Create a new Department");
        System.out.println("\t 2) Get Department by ID");
        System.out.println("\t 3) Edit Department");
        System.out.println("\t 4) Delete Department");
        System.out.println("\t 5) List Departments");
        System.out.println("\t 0) Return to Main Menu");
        System.out.println("*".repeat(35));
        System.out.print("Choose an Option: ");
        return sc.nextInt();
    } 

    private static void createDepartment(){
        sc.nextLine();
        System.out.println("Creating new Department...");
        System.out.print("\t Department's Name: ");
        String name = sc.nextLine();
        Department department = new Department(name);
        serviceDepartment.create(department);
        sc.next();
    }

    private static Department getDepartment() {
        System.out.print("\t Department's ID: ");
        int id = sc.nextInt();
        try {
            return serviceDepartment.getDepartment(id);
        } catch (DepartmentNullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void printDepartment() {
        System.out.println("Searching a Department...");
        sc.nextLine();
        System.out.print("ID: ");
        int id = sc.nextInt();

        try {
            Department department = serviceDepartment.getDepartment(id);
            System.out.println();
            department.print();
        } catch (DepartmentNullException e) {
            System.out.println(e.getMessage());
        }
        sc.next();
    }

    private static void modifyDepartment() {
        System.out.println("Modifying a Department...");
        Department department = getDepartment();
        if(department != null) {
            int opc = 0;
            do {
                System.out.println(" 1) Name");
                System.out.println(" 2) Save & Exit");
                opc = sc.nextInt();
                sc.nextLine();
                if(opc == 1){
                    System.out.print("\t New Department's Name: ");
                    String name = sc.nextLine();
                    department.setName(name);
                }
            }while(opc < 1 || opc >= 2);
            serviceDepartment.modify(department);
            System.out.println("Modified");
        }
        sc.next();     
    }

    private static void deleteDepartment() {
        System.out.println("Deleting a Department...");
        Department department = getDepartment();
        if(department != null) {
            serviceDepartment.delete(department);
            System.out.println("Department deleted sucessfully");
        } else {
            System.out.println("Department not deleted sucessfully");
        }
    }

    private static void listDepartments() {
        System.out.println("Department's List");
        for(Department department : serviceDepartment.list()) {
            department.print();
            System.out.println();
        }
        sc.next();
    }
    
}
