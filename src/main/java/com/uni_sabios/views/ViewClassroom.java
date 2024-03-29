package com.uni_sabios.views;

import java.util.List;

import com.uni_sabios.exceptions.classroomexceptions.ClassroomExceptionInsertDataBase;
import com.uni_sabios.exceptions.classroomexceptions.ClassroomNullException;
import com.uni_sabios.repository.impl.RepositoryBuildingMysqlImpl;
import com.uni_sabios.repository.models.Building;
import com.uni_sabios.repository.models.Classroom;
import com.uni_sabios.services.ServiceBuilding;
import com.uni_sabios.services.impl.ServiceBuildingImpl;

public class ViewClassroom extends ViewMain{

    private static final ServiceBuilding serviceBuilding = new ServiceBuildingImpl(new RepositoryBuildingMysqlImpl());

    public static void startMenu() {
        int opc = 0;

        do {
            opc = showMenu();
            switch (opc) {
                case 1:
                    createClassroom();
                    break;
                case 2:
                    getClassroom();
                    break;
                case 3:
                    listClassrooms();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Not Available Choice");
                    sc.next();
            }
            
        }while(opc>0);
    }

    private static int showMenu() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        System.out.println("*".repeat(10) + " Student's Menu " + "*".repeat(10));
        System.out.println("\t 1) Create a new Classroom");
        System.out.println("\t 2) Get Classroom by ID");
        System.out.println("\t 3) List Classrooms");
        System.out.println("\t 0) Return to Main Menu");
        System.out.println("*".repeat(35));
        System.out.print("Choose an Option: ");
        return sc.nextInt();
    } 

    private static void createClassroom(){
        sc.nextLine();
        System.out.println("Creating new Classroom...");
        System.out.print("\t Classroom's Name: ");
        String name = sc.nextLine();
        int capacity = 0;
        do {
            System.out.print("\t Classroom's Capacity: ");
            capacity = sc.nextInt();
        }while(capacity <= 0);
        int level = 0;
        do {
            System.out.print("\t Classroom's Level: ");
            level = sc.nextInt();
        }while(level <= 0);
        List<Building> buildings = serviceBuilding.list();
        System.out.println("Building List");
        for (int i=0; i<buildings.size(); i++) {
            System.out.println("\t" + (i+1) + ") " + buildings.get(i).getName());
        }
        int opc = 0;
        do {
            System.out.print("\t Choose a Building: ");
            opc = sc.nextInt();
        }while(opc < 0 || opc > buildings.size());
        int buildingId = buildings.get(opc-1).getId();
        Classroom classroom = new Classroom(capacity, level, name, buildingId);
        try {
            serviceClassroom.create(classroom);
            System.out.println("Classroom created successfully!");
        } catch (ClassroomExceptionInsertDataBase e) {
            System.out.println(e.getMessage());
        }
        sc.next();
        
    }

    private static void getClassroom() {
        System.out.println("Searching a Classroom...");
        sc.nextLine();
        System.out.print("\t Classroom's ID: ");
        int id = sc.nextInt();
        try {
            Classroom classroom = serviceClassroom.getClassroom(id);
            System.out.println();
            classroom.print();
        } catch (ClassroomNullException e) {
            System.out.println(e.getMessage());
        }
    }

    
    private static void listClassrooms() {
        System.out.println("Classrooms List");
        for (Classroom classroom : serviceClassroom.list()) {
            classroom.print();
            System.out.println();
        }
        sc.next();
    }
    
}
