//
// Name: James Choi
// Project: 4
// Due: 12/6/2024
// Course: cs-2400-01
//
// Description:
//      A program that uses the graph ADT to implement Dijkstra's algorithm for
//      finding the shortest route between two airports.
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AirportApp {
    public static void main(String[] args) {
        DictionaryInterface<String,String> airportCodeInfo=new HashedDictionary<>(137);
        GraphInterface<String> verticesGraph=new DirectedGraph<>();

        storeAirportCodeInfo(airportCodeInfo);
        storeVertices(verticesGraph);

        String command;

        System.out.println("Airports v0.24f by J. Choi\n");

        do {
            command = executeCommand(airportCodeInfo,verticesGraph);
        } while (!command.equals("E"));

    } // end main
    private static void storeAirportCodeInfo(DictionaryInterface<String,String> airportCodeInfo) {
        String fileName="US_Airport_Codes.csv";
        try {
            Scanner scanner=new Scanner(new File(fileName));

            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                String[] splitLine=line.split(",");
                airportCodeInfo.add(splitLine[0],splitLine[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    private static void storeVertices(GraphInterface<String> verticesGraph) {
        try {
            Scanner scanner=new Scanner(new File("US_Airport_Codes.csv"));

            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                String[] splitLine=line.split(",");
                verticesGraph.addVertex(splitLine[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        try {
            Scanner scanner = new Scanner(new File("US_Airports_Routes.csv"));
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                String[] splitLine=line.split(",");
                verticesGraph.addEdge(splitLine[0],splitLine[1],Double.parseDouble(splitLine[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    private static String executeCommand(DictionaryInterface<String,String> airportCodeInfo, GraphInterface<String> graph) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Command? ");
        String command=scanner.nextLine();
        switch (command) {
            case "H":
                commandH();
                return "H";
            case "E":
                return "E";
            case "Q":
                System.out.print("Airport code? ");
                String airportCode=scanner.nextLine();
                commandQ(airportCode,airportCodeInfo);
                return "Q";
            case "D":
                System.out.print("Airport codes from to? ");
                String airportCodes=scanner.nextLine();
                String[] a=airportCodes.split(" ");
                try {
                    commandD(a[0], a[1], airportCodeInfo, graph);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid command");
                }
                return "D";
            default:
                System.out.println("Invalid command");
                return "X";
        }
    }
    private static void commandH() {
        System.out.println("Q Query the airport information by entering the airport code.");
        System.out.println("D Find the minimum distance between two airports.");
        System.out.println("H Display this message.");
        System.out.println("E Exit.");
    }
    private static void commandQ(String airportCode, DictionaryInterface<String,String> airportCodeInfo) {
        String value=airportCodeInfo.getValue(airportCode);
        if (value==null) System.out.println("Airport code unknown");
        else System.out.println(value);
    }
    private static void commandD(String beginAirport, String endAirport, DictionaryInterface<String,String> airportCodeInfo,GraphInterface<String> graph) {
        StackInterface<String> stack=new ArrayStack<>();
        double result = graph.getCheapestPath(beginAirport,endAirport,stack);
        if (result==-1) {
            System.out.println("Airports not connected");
            return;
        }
        if (result>=0) {
            System.out.println("The shortest distance between " + beginAirport + " and " + endAirport + " is " + result);
            while (!stack.isEmpty()){
                String a=stack.pop();
                System.out.println(airportCodeInfo.getValue(a) + " [" + a + "]");
            }

        }
    }

}
