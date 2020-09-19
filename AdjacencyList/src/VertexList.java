import java.util.*;
public class VertexList {

private String name;
private ArrayList<Vertex> neighs;
public VertexList(String name){
    this.name = new String(name);
}
public String toString(){
    return name + " " + neighs;
}
public ArrayList<Vertex> getNeighs(){
    return neighs;
}
public ArrayList<Vertex> setNeighs(ArrayList<Vertex> neighs){
    this.neighs = neighs;
    return neighs;
}
}