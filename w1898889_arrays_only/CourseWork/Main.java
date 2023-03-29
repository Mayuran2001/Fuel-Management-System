import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static String [][] fuel_queue = new String[3][6];
    public static int fuel=6600;

    public static void main(String[] args){

        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println();
                System.out.println("____________Fuel Center Menu____________");
                System.out.println();
                System.out.println("100 or VFQ:   View all Fuel Queues.");
                System.out.println("101 or VEQ:   View all Empty Queues.");
                System.out.println("102 or ACQ:   Add customer to a Queue.");
                System.out.println("103 or RCQ:   Remove a customer from a Queue.(From a specific location)");
                System.out.println("104 or PCQ:   Remove a served customer.");
                System.out.println("105 or VCS:   View Customers Sorted in alphabetical order(Do not use library sort routine)");
                System.out.println("106 or SPD:   Store Program Data into a file.");
                System.out.println("107 or LPD:   Load Program Data into a file.");
                System.out.println("108 or STK:   View Remaining Fuel Stock.");
                System.out.println("109 or AFS:   Add Fuel Stock.");
                System.out.println("999 or EXT:   Exit the Program.");

                if (fuel <= 500 && fuel>0) {
                    System.out.println("\nThe stock reaches a value of 500 liters.");
                }

                System.out.println();

                System.out.print("Please select one menu option: ");
                String menu = input.next();

                System.out.println();

                switch (menu) {
                    case "100":
                    case "VFQ":
                        all_fuel_queue();
                        design();
                        System.out.println();
                        break;


                    case "101":
                    case "VEQ":
                        empty_queue();
                        design();
                        System.out.println();
                        break;

                    case "102":
                    case "ACQ":
                        add_customer();
                        design();
                        System.out.println();
                        break;

                    case "103":
                    case "RCQ":
                        remove_customer();
                        design();
                        System.out.println();
                        break;

                    case "104":
                    case "PCQ":
                        remove_queue();
                        design();
                        System.out.println();
                        break;


                    case "105":
                    case "VCS":
                        Sorted_in_alphabetical();
                        design();
                        System.out.println();
                        break;

                    case "106":
                    case "SPD":
                        CreateFile();
                        design();
                        System.out.println();

                    case "107":
                    case "LPD":
                        ReadFile();
                        design();
                        System.out.println();

                    case "108":
                    case "STK":
                        remaining_fuel_stock();
                        design();
                        System.out.println();
                        break;

                    case "109":
                    case "AFS":
                        add_fuel();
                        design();
                        System.out.println();
                        break;

                    case "999":
                    case "EXT":
                        System.out.println("Thank you. Goodbye. Have a nice day.");
                        return;

                    default:
                        System.out.println("Enter a valid integer!");
                        System.out.println();
                }
            } catch (Exception Ex) {
                System.out.println(Ex.getMessage());
                break;

            }
        }



    }
    public static void empty_queue(){
        boolean queueEmpty = false;
        for (int i = 0; i < fuel_queue.length; i++) {
            for (int j = 0; j < fuel_queue[i].length; j++) {
                if (fuel_queue[i][j] == null) {
                    System.out.println("Empty pump is " + (i + 1) + " empty queue is " + (j + 1) + ".");
                    queueEmpty = true;
                }
            }
        }
        if (!queueEmpty) {
            throw new RuntimeException("Queue is full. No empty queues!");
        }
    }
    public static void add_customer() {
        Scanner s1 = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String name= s1.next();
        try {
            Integer.parseInt(name);
            System.out.println("Enter a valid name!");
            System.out.println();
            add_customer();
        } catch (Exception Ex) {
            boolean queueFull = false;
            try {
                initial_loop:
                for (int i = 0; i < fuel_queue.length; i++) {
                    for (int j = 0; j < fuel_queue[i].length; j++) {
                        if (fuel_queue[i][j] == null) {
                            fuel_queue[i][j] = name;
                            queueFull = true;
                            break initial_loop;
                        }
                    }
                }
                if (!queueFull) {
                    throw new RuntimeException("Queue is full!");
                }
            } catch (Exception Exe) {
                System.out.println(Exe.getMessage());
            }
        }
    }

    public static void all_fuel_queue(){
        for (int i = 0; i < fuel_queue.length; i++) {
            for (int j = 0; j < fuel_queue[i].length; j++) {
                if (fuel_queue[i][j] != null) {
                    System.out.println("Customer " + fuel_queue[i][j] + " is at pump " + (i + 1) + " queue " + (j + 1) + ".");
                } else {
                    System.out.println("pump " + (i + 1) + " queue " + (j + 1) + " is empty.");
                }
            }
        }
    }

    public static void remove_queue(){
        Scanner input = new Scanner(System.in);
        boolean queueEmpty = false;
        System.out.print("Enter your fuel limit:");

        if(input.hasNextInt()) {
            int fuel_limit = input.nextInt();{
                if (fuel - fuel_limit >= 0) {
                    initial_loop: for (int i = 0; i < fuel_queue.length; i++) {
                        for (int j = 0; j < fuel_queue[i].length; j++) {
                            if (fuel_queue[i][j] != null) {
                                fuel_queue[i][j] = null;
                                queueEmpty = true;
                                fuel = fuel - fuel_limit;
                                System.out.println("Served customer removed successfully. ");
                                break initial_loop;
                            }
                        }
                    }
                }else
                    System.out.println("Out of stock!");
            }
        }else
            System.out.println("Enter a valid integer!");
        if(!queueEmpty){
            System.out.println("queue is empty.");
        }
    }



    public static void remove_customer() {
        Scanner input = new Scanner(System.in);
        int pump;
        int queue;
        int queueCustomer = 0;

        try {

            // Check whether fuel queue is empty or not
            for (int i=0; i<fuel_queue.length; i++) {
                for (int j=0; j<fuel_queue[i].length; j++) {
                    if (Objects.equals(fuel_queue[i][j], null)) {
                        queueCustomer += 1;
                    }
                }
            }

            if (queueCustomer != 18) {
                System.out.print("Enter pump number: ");
                if (input.hasNextInt()) {
                    pump = input.nextInt();
                    if (pump > 0 && pump < 4) {
                        System.out.print("Enter your queue number: ");
                        if (input.hasNextInt()) {
                            queue = input.nextInt();
                            if (queue > 0 && queue < 7) {
                                if (!Objects.equals(fuel_queue[pump - 1][queue - 1], null)) {
                                    fuel_queue[pump - 1][queue - 1] = null;
                                    System.out.println("Customer removed Successfully.");
                                } else {
                                    throw new RuntimeException("Queue is empty!");
                                }
                            } else {
                                throw new RuntimeException("Invalid Queue No.!");
                            }
                        } else {
                            throw new RuntimeException("Integer Required!");
                        }
                    } else {
                        throw new RuntimeException("Invalid Pump!");
                    }
                } else {
                    throw new RuntimeException("Integer Required!");
                }
            } else {
                throw new RuntimeException("Fuel queue is empty!");
            }
        } catch (Exception Ex){
            System.out.println(Ex.getMessage());
        }
    }
    public static void add_fuel(){
        Scanner input =new Scanner(System.in);

        System.out.print("Enter your new fuel stock: ");
        if(input.hasNextInt()){
            int new_fuel_stock = input.nextInt();
            System.out.println("Current fuel stock: "+fuel);
            fuel= fuel+new_fuel_stock;
            System.out.println("New Fuel Stock: "+fuel);
        }else{
            System.out.println("Enter valid integer.");
            System.out.println();
            add_fuel();
        }
    }


    public static void remaining_fuel_stock() {
        System.out.println("Remaining fuel stock : "+fuel);



    }

    public static void Sorted_in_alphabetical(){
            String[][] tempArray = fuel_queue;
        try {
            for (int i=0; i < tempArray.length; i++) {
                for (int j = 0; j < tempArray[i].length; j++) {
                    for (int k = 0; k < tempArray[i].length - j - 1; k++) {
                        if (tempArray[i][k] != null && tempArray[i][k + 1] != null && tempArray[i][k].compareTo(tempArray[i][k + 1]) > 0) {
                            String temp = tempArray[i][k];
                            tempArray[i][k] = tempArray[i][k+ 1];
                            tempArray[i][k + 1] = temp;
                        }
                    }
                }
            }
            System.out.println("--------Alphabetical sorted array---------");
            for (int i = 0; i < tempArray.length; i++) {
                System.out.println("\nPump"+(i+1));
                for (int j = 0; j < tempArray[i].length; j++) {
                    if (tempArray[i][j] != null) {
                        System.out.println(tempArray[i][j]);
                    }
                }
            }
        } catch (Exception Ex) {
            System.out.println(Ex.getMessage());
        }
    }



    public static void design(){
        System.out.println("___________________________________________________________________________________________________________________________________________________________");
    }


    public static void CreateFile() {
        try {
            File myObj = new File("filename.txt");
            System.out.println("File created : " + myObj.getName());
            FileWriter file = new FileWriter("data.txt");
            for (int i = 0; i < fuel_queue.length; i++) {
                for (int j = 0; j < fuel_queue[i].length; j++) {
                    if (fuel_queue[i][j] != null) {
                        file.write(fuel_queue[i][j]+"\n");
                    } else {
                        file.write("Empty\n");
                    }
                }
            }
            file.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ReadFile(){
        String[] tempArray = new String[18];
        int count = 0;
        try{
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                tempArray[count] = data;
                count += 1;
            }
            myReader.close();

            count = 0;
            for (int i = 0; i < fuel_queue.length; i++) {
                for (int j = 0; j < fuel_queue[i].length; j++) {
                    if (Objects.equals(tempArray[count], "Empty")) {
                        fuel_queue[i][j] = null;
                    } else {
                        fuel_queue[i][j] = tempArray[count];
                    }
                    count += 1;
                }
            }
            System.out.println("Data Loaded Successfully.");
        }catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}