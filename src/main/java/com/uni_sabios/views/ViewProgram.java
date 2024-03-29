package com.uni_sabios.views;

import com.uni_sabios.exceptions.programexceptions.ProgramNullException;
import com.uni_sabios.repository.models.Program;

public class ViewProgram extends ViewMain{

    public static void startMenu() {
        int opc = 0;

        do {
            clear();
            opc = showMenu();
            switch (opc) {
                case 1:
                    createProgram();
                    break;
                case 2:
                    printProgram();
                    break;
                case 3:
                    modifyProgram();
                    break;
                case 4:
                    deleteProgram();
                    break;
                case 5:
                    listPrograms();
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
        System.out.println("*".repeat(10) + " Program's Menu " + "*".repeat(10));
        System.out.println("\t 1) Create a new Program");
        System.out.println("\t 2) Get Program by ID");
        System.out.println("\t 3) Edit Program");
        System.out.println("\t 4) Delete Program");
        System.out.println("\t 5) List Programs");
        System.out.println("\t 0) Return to Main Menu");
        System.out.println("*".repeat(35));
        System.out.print("Choose an Option: ");
        return sc.nextInt();
    } 

    private static void createProgram(){
        sc.nextLine();
        System.out.println("Creating new Program...");
        System.out.print("\t Program's Name: ");
        String name = sc.nextLine();
        int opc = 0;
        do {
            System.out.println("\t Program's level");
            System.out.println("\t  1) Pre-Graduate");
            System.out.println("\t  2) Post-Graduate");
            System.out.print("\t Choose(1/2): ");
            opc = sc.nextInt();
        }while(opc < 0 || opc > 2);
        String level = "";
        if(opc == 1) level = "Pre-Graduate";
        else level = "Post-Graduate";
        Program program = new Program(name, level);
        serviceProgram.create(program);
        sc.next();
    }

    private static Program getProgram() {
        sc.nextLine();
        System.out.print("\t Program's ID: ");
        int id = sc.nextInt();
        try {
            return serviceProgram.getProgram(id);
        } catch (ProgramNullException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void printProgram() {
        System.out.println("Searching a Program...");
        sc.nextLine();
        System.out.print("ID: ");
        int id = sc.nextInt();
        try {
            Program program = serviceProgram.getProgram(id);
            System.out.println();
            program.print();
        } catch (ProgramNullException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modifyProgram() {
        System.out.println("Modifying a Program...");
        Program program = getProgram();
        if(program != null) {
            int opc = 0;
            System.out.println(" 1) Name");
            System.out.println(" 2) Level");
            opc = sc.nextInt();
            sc.nextLine();
            if(opc == 1){
                System.out.print("\t New Program's Name: ");
                String name = sc.nextLine();
                program.setName(name);
            }else if(opc == 2){
                do {
                    System.out.println("\t Program's level");
                    System.out.println("\t  1) Pre-Graduate");
                    System.out.println("\t  2) Post-Graduate");
                    System.out.print("\t Choose(1/2): ");
                    opc = sc.nextInt();
                }while(opc < 0 || opc > 2);
                String level = "";
                if(opc == 1) level = "Pre-Graduate";
                else level = "Post-Graduate";
                program.setLevel(level);
            }else{
                System.out.println("Incorrect Choose");
            }
            serviceProgram.modify(program);
            System.out.println("Program Modified");
        }
        sc.next(); 
    }

    private static void deleteProgram() {
        System.out.println("Deleting a Program...");
        Program program = getProgram();
        if(program != null) {
            serviceProgram.delete(program);
            System.out.println("Program deleted sucessfully");
        } else {
            System.out.println("Program not deleted sucessfully");
        }
        sc.next();
    }

    public static void listPrograms() {
        System.out.println("Program's List");
        for(Program program : serviceProgram.list()) {
            program.print();
            System.out.println();
        }
        sc.next();
    }
    
}
