import java.util.*;
import javax.swing.*;
public class BuildList<i> {
    public static void main(String[] args) {

        int vCount;
        //adds vertex with label number, defaults to #1
        vCount = readInteger("Number of Vertices:", 1);
        //ArrayList Stores vertices then another list stores neighbors
        ArrayList<VertexList> vList = new ArrayList<VertexList>(vCount);
        int i;
        for (i = 1; i < vCount + 1; i++) {
            //VertexList class builds each line of the adjacency
            vList.add(i - 1, new VertexList("" + i));
            int nCount = readInteger("Number of neighbors for vertex #" + i, 0);
            ArrayList<Vertex> neighs = new ArrayList<Vertex>(nCount);
            for (int k = 0; k < nCount; k++) {
                int vertexNum = readInteger("Enter neighbor #" + (k + 1) + "for vertex" + i,1);
                neighs.add(new Vertex("" + vertexNum));
            }
            vList.get(i - 1).setNeighs(neighs);
        }
        for (i = 1; i < vCount + 1; i++) {
            ArrayList<Vertex> neighs = vList.get(i - 1).getNeighs();
            System.out.print("neighbors = ");
            Iterator<Vertex> it = neighs.iterator();
            System.out.print("[");
            while (it.hasNext()) {
                System.out.print(it.next());
                if (it.hasNext())
                    System.out.print(",");
            }
        }
    }
    public static int readInteger(String prompt, int min) {
        int val = 0;
        do {
            String ans = JOptionPane.showInputDialog(null, prompt, "");
            try {
                val = Integer.parseInt(ans);
            } catch (NumberFormatException nfe) {
                val = 0;
            }
        }
        while (val < min);
        return val;
    }

}
//Graph g = new Graph(vList);
//g.display()
//g.dfs()
