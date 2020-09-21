import javafx.stage.FileChooser;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Graph {

    private static ArrayList<VertexList> vList ;      //= new ArrayList<VertexList>(vCount);
    private static ArrayList<String> Depth ;
    private static int bot = 0;
    public static int vCount;

    public Graph() {

        vCount = readInteger("Number of Vertices", 1);
        vList = new ArrayList<VertexList>(vCount + 1);

        int i;
        for (i = 1; i < vCount + 1; i++) {
            // Add vertex with label number, start with #1
            vList.add(i - 1, new VertexList("" + i));

            int nCount = readInteger("Number of neighbors for vertex # " + i, 0);

            ArrayList<Vertex> neibs = new ArrayList<Vertex>(nCount);

            for (int k = 0; k < nCount; k++) {
                int vertexNum = readInteger("Enter neighbor # " + (k + 1) + " for vertex # " + i, 1);
                neibs.add(new Vertex(" " + vertexNum));
            }

            vList.get(i - 1).setNeibs(neibs);
        }

    }

    public Graph(String file) throws FileNotFoundException {

        Scanner inFile = new Scanner(new FileReader(file));
        vCount = inFile.nextInt();
        String trash = inFile.nextLine();
        vList = new ArrayList(vCount);
        int i;

        for (i = 0; i < vCount; i++) {
            vList.add(i, new VertexList("" + 1));
            String neibslist = inFile.nextLine();
            String[] neibsArray = neibslist.split(" ");
            int[] neibsArray1 = new int[neibsArray.length];

            for (int j = 0; j < neibsArray.length; j++) {
                neibsArray1[j] = Integer.parseInt(neibsArray[j]);

            }
            ArrayList<Vertex> neibs = new ArrayList<Vertex>(neibsArray.length);
            for (int k = 0; k < neibsArray.length; k++) {
                int vertexNum = Integer.parseInt(neibsArray[k]);
                neibs.add(new Vertex("" + vertexNum));
            }
            vList.get(i).setNeibs(neibs);

        }
        inFile.close();
    }

    public void display() {

        for (int i = 1; i < vList.size() + 1; i++) {
            ArrayList<Vertex> neibs = vList.get(i - 1).getNeibs();

            System.out.print(i + " neibs = ");
            Iterator<Vertex> it = neibs.iterator();

            System.out.print("[");
            while (it.hasNext()) {
                System.out.print(it.next());
                if (it.hasNext()) {
                    System.out.print(", ");
                }
            }

            System.out.print("]");

            System.out.println();

        }

    }

    private int readInteger(String prompt, int min) {

        int val = 0;

        do {
            String ans = JOptionPane.showInputDialog(null, prompt, "");

            try {
                val = Integer.parseInt(ans);
            } catch (NumberFormatException nfe) {
                val = 0;
            }
        } while (val < min);

        return val;

    }

    //vList isnt
    //wasVisited will always be 0 because vCount is in Graph()
    public static ArrayList<String> DFS(VertexList vertexList,int vCount) {
        Depth = new ArrayList<String>();
        boolean[] wasVisited = new boolean[vCount];
        for (int i=1; i<=vCount; i++ ) {
            wasVisited[i-1]= false;
        }
        dfsRecursive(vertexList, wasVisited,Depth);
            return Depth;
    }

    public static void dfsRecursive(VertexList current, boolean[] wasVisited, ArrayList<String> Depth) {
        int cNum = Integer.parseInt(current.getName());
        int index= cNum-1;
        while(wasVisited[vCount-1] != true) {
            while (wasVisited[index] != true) {
                wasVisited[index] = true;
                Depth.add(bot, current.getName());
                bot++;

            for (int i = 0; i < index; i++) {
                dfsRecursive(vList.get(i), wasVisited, Depth);
            }

        }index++;
    }
    }



    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Graph graph = null;
        //menu for options
        String menu = "Please Select an Option: \n" +
                "1. Create Graph \n" +
                "2. Determine DFS \n" +
                "3. Determine BFS \n" +
                "4. Quit\n";
        //pop up window
        //if someone knows how to use FileReader that would be nice
        String ans = JOptionPane.showInputDialog(null, menu + "Please Select an option","");

        //we can use switch to determine what choice they made
        switch(ans){
            //Builds the list
            case "1":
                try {
                    System.out.println("Please input whether you would like to run from a file or input vertex's " +
                            "manually" + "\n" + "Press 1 for manual and"
                            + " 2 for file");
                    int choose = input.nextInt();
                    if (choose == 1) {
                        graph = new Graph();
                    } else if (choose == 2) {
                        graph = new Graph("Adjacency List.txt");
                    }
                    assert graph != null;
                    graph.display();
                }
                catch(FileNotFoundException e){
                    System.out.println("no file was found with that name");
                    e.printStackTrace();
                }
        //Starts the DFS
            case "2":
                try{
                    System.out.println("Would you like to do a Depth First Search?" + "\n" + "Press 1 for Yes and" +
                            " 2 for No");
                 int choose = input.nextInt();
                 if (choose == 1) {
                    System.out.println("Enter Starting Vertex: ");
                    int b = input.nextInt();
                    choose = b - 1;
                    DFS(vList.get(choose),vCount);
                    System.out.println(Depth);
                 }
                    break;
                }catch(NullPointerException e){
                    System.out.println("There is no list");
                    e.getStackTrace();
            }
        //Starts the BFS
            case "3":
                System.out.println("Haven't written that yet try in a week");
                break;
         //exits the program
            case "4":
                System.exit(0);
        }
    }

}

// Make sure that a user enters the number of vertices
// do...while repeates until a successful number entry

