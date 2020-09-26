import javafx.stage.FileChooser;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Graph {

    private static ArrayList<VertexList> vList;      //= new ArrayList<VertexList>(vCount);
    private static ArrayList<String> Depth;
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

    public static ArrayList<String> DFS(VertexList vertexList, int vCount) {
        Depth = new ArrayList<String>();
        boolean[] wasVisited = new boolean[vCount];
        for (int i = 1; i <= vCount; i++) {
            wasVisited[i - 1] = false;
        }
        dfsRecursive(vertexList, wasVisited, Depth);
        return Depth;
    }

    public static void dfsRecursive(VertexList current, boolean[] wasVisited, ArrayList<String> Depth) {
        int cNum = Integer.parseInt(current.getName());
        int index = cNum - 1;
        while (wasVisited[vCount - 1] != true) {
            while (wasVisited[index] != true) {
                wasVisited[index] = true;
                Depth.add(bot, current.getName());
                bot++;

                for (int i = 0; i < index; i++) {
                    dfsRecursive(vList.get(i), wasVisited, Depth);
                }

            }
            index++;
        }
    }

    public static void BFS(VertexList vertexList, int vCount) {
        String source;
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean[] visited = new boolean[vCount];
        // Create a queue for BFS
        LinkedList<String> queue = new LinkedList<String>();

        // Mark the current node as visited and enqueue it
        visited[vCount - 1] = true;
        queue.add(vertexList.getName());
        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            source = queue.poll();
            System.out.print(source + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Vertex> i = vertexList.getNeibs().iterator();
            while (i.hasNext()) {
                Vertex n = i.next();
                for (int j = 0; j < vCount; j++) {
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.add("" + n);
                    }
                }
            }
        }
    }

        public static void main (String[]args) throws FileNotFoundException {
            Scanner input = new Scanner(System.in);
            Graph graph = null;
            //menu for options
            String menu = "Please Select an Option: \n" +
                    "1. Create Graph \n" +
                    "2. Determine DFS \n" +
                    "3. Determine BFS \n" +
                    "4. Quit\n";

            //pop up window
            String ans = JOptionPane.showInputDialog(null, menu + "Please Select an option", "");

            //we can use switch to determine what choice they made
            //sorry for all the JOptionPane I saw he wanted a pop out menu
            switch (ans) {
                //Builds the list
                case "1":
                    try {
                        String ans2 = JOptionPane.showInputDialog(null, "Please input whether you " +
                                "would like to run from a file or input vertex's manually \n"
                                + "Press 1 for manual and 2 for file", "");
                        if (ans2.equals("1")) {
                            graph = new Graph();
                        } else if (ans2.equals("2")) {
                            String filename = JOptionPane.showInputDialog(null,
                                    "Enter File Name Without .txt", "");
                            graph = new Graph(filename + ".txt");
                        }
                        assert graph != null;
                        graph.display();
                    } catch (FileNotFoundException e) {
                        System.out.println("no file was found with that name");
                        e.printStackTrace();
                    }
                    //Starts the DFS
                case "2":
                    try {
                        String filename = JOptionPane.showInputDialog(null,
                                "Enter File Name Without .txt", "");
                        graph = new Graph(filename + ".txt");
                        graph.display();
                        String ans3 = JOptionPane.showInputDialog(null, "Would you like to do a " +
                                "Depth First Search? \n" + "Press 1 for Yes and 2 for No", "");
                        int choose = Integer.parseInt(ans3);
                        if (ans3.equals("1")) {
                            String ans4 = JOptionPane.showInputDialog(null, "Enter Starting Vertex", 1);
                            int b = Integer.parseInt(ans4);
                            choose = b - 1;
                            DFS(vList.get(choose), vCount);
                            System.out.println(Depth);
                        }
                        break;
                    } catch (NullPointerException | FileNotFoundException e) {
                        System.out.println("There is no list");
                        e.getStackTrace();
                    }
                    //Starts the BFS
                case "3":
                    try {
                        String filename = JOptionPane.showInputDialog(null,
                                "Enter File Name Without .txt", "");
                        graph = new Graph(filename + ".txt");
                        graph.display();
                        String ans5 = JOptionPane.showInputDialog(null, "Would you like to do a " +
                                "Breadth First Search? \n" + "Press 1 for Yes and 2 for No", "");
                        int choose = Integer.parseInt(ans5);
                        if (ans5.equals("1")) {
                            String ans6 = JOptionPane.showInputDialog(null, "Enter Starting Vertex", 1);
                            int b = Integer.parseInt(ans6);
                            choose = b - 1;
                            BFS(vList.get(choose), vCount);
                            System.out.println(Depth);
                        }
                    } catch (NullPointerException | FileNotFoundException e) {
                        System.out.println("There is no list");
                        e.getStackTrace();
                    }
                    //exits the program
                case "4":
                    System.exit(0);
            }
        }


}
