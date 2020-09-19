import java.io.*
import java.util.*;
import javax.swing.*;
public class Graph {
    File filelist = new File("Adjacency List.txt");
    public Graph(ArrayList<Vertex> vList ){

    }
    public Graph display(){
        //menu for options
        System.out.printf("Please Select an Option: %n" +
                "1. Create Graph from File %n" +
                "2. Determine DFS %n" +
                "3. Determine BFS %n" +
                "4. Quit%n");
        //pop up window
        String ans = JOptionPane.showInputDialog(null, "Please Select an option","");
        //we can use switch to determine what choice they made
        switch(ans){
            case "1":
                //Read the file the first int is the Vertices the rest is neighbors
                try{
                    Scanner Reader = new Scanner(filelist);
                    Reader.nextInt() = vertex;
                    while(Reader.hasNextInt()){

                    }
                }catch(FileNotFoundException e){
                    System.out.println("Error Reading File.");
                    e.printStackTrace();
                }

        }

        return null;


    }
    public Graph dfs(){
        return null;

    }
}
