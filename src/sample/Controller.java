package sample;

import javafx.fxml.FXML;
import javafx.animation.PathTransition;
import com.sun.source.tree.Tree;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
//import java.awt.Rectangle;
//import java.awt.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Duration;
class Vertex{
    private String ver;
    private int xcor,ycor;
    Vertex(String ver,int x,int y)
    {
        this.ver=ver;
        this.xcor=x;
        this.ycor=y;
    }
    int get_x()
    {
        return xcor;
    }
    String get_ver()
    {
        return ver;
    }
    int get_y()
    {
        return ycor;
    }
    void set_x(int i)
    {
        xcor=i;
    }
    void set_y(int j)
    {
        ycor=j;
    }
}
class Ashima2
{
    String v;
    int w;
    void add(String v,int w)
    {
        this.v=v;
        this.w=w;
    }
    String get_to()
    {
        return v;
    }
    int get_w()
    {
        return w;
    }
    void set_w(int w)
    {
        this.w=w;
    }

}
class Ashima
{
    Vertex parent;
    ArrayList<Ashima2> list2 = new ArrayList<Ashima2>();
    void add_parent(Vertex parent)
    {
        this.parent=parent;
    }
    void add_child(String child,int w)
    {
        this.list2.add(new Ashima2());
        this.list2.get(list2.size()-1).add(child,w);
    }
    int get_x()
    {
        return this.parent.get_x();
    }
    String get_ver()
    {
        return this.parent.get_ver();
    }
    int get_y()
    {
        return this.parent.get_y();
    }
    void set_x(int i)
    {
        this.parent.set_x(i);
    }
    void set_y(int j)
    {
        this.parent.set_y(j);
    }
    void get_it_done()
    {
        Collections.sort(list2 , new check2() );
    }
}
class check implements Comparator<Ashima>
{
    public int compare(Ashima a,Ashima b)
    {
        String s3=a.get_ver();
        String s4=b.get_ver();
        return s3.compareTo(s4);
    }
}
class check2 implements Comparator<Ashima2>
{
    public int compare(Ashima2 a,Ashima2 b)
    {
        String s3=a.get_to();
        String s4=b.get_to();
        return s3.compareTo(s4);
    }
}
class MyException extends Exception
{
    public MyException(String s)
    {
        super(s);
    }
}
public class Controller
{
    ArrayList<Ashima> list = new ArrayList<Ashima>();
    @FXML
    TextField vertex,x,y,newvertex,delvertex,modvertex,newx,newy,fromvertex,tovertex,weight,sfromvertex,stovertex,dfromvertex,dtovertex,mfromvertex,mtovertex,mweight,filepath,ofilepath,source,goal,type;
    @FXML
    Button add,search,delete,modify,addedge;
    @FXML
    TextArea space;
    @FXML
    AnchorPane animation;
    public void add_input(ActionEvent e)
    {
        try
        {
            String ver = vertex.getText();
            String xcor = x.getText();
            String ycor = y.getText();
            int i = Integer.parseInt(xcor);
            int j = Integer.parseInt(ycor);
            Vertex parent = new Vertex(ver, i, j);
            for(int t=0;t<list.size();t++)
            {
                if(list.get(t).get_ver().equals(ver)&&list.get(t).get_x()!=i&&list.get(t).get_y()!=j)
                    throw new MyException("");
            }
            if(ver.equals(""))
                throw new MyException("");

            list.add(new Ashima());
            list.get(list.size() - 1).add_parent(parent);
            Circle dot = new Circle();
            dot.setCenterX(i*10);
            dot.setCenterY(j*10);
            dot.setRadius(20);
            dot.setFill(Color.WHITE);
            dot.setStroke(Color.ORANGE);
            dot.setStrokeWidth(10);
            animation.getChildren().add(dot);
            Text text=new Text();
            text.setText(ver);
            text.setX(i*10-1);
            text.setY(j*10+9);
            text.setFont(Font.font("Verdana",20));
            text.setStroke(Color.BLACK);
            animation.getChildren().add(text);
            space.setText(ver + " added successfully " + xcor + " " + ycor);
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");                                   //Alert code taken from https://code.makery.ch/blog/javafx-dialogs-official/
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
        catch(NumberFormatException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");                                   //Alert code taken from https://code.makery.ch/blog/javafx-dialogs-official/
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void search_input(ActionEvent e)
    {
        try
        {
            String newver = newvertex.getText();
int k=0;
            for ( k = 0; k < list.size(); k++)
            {
                if ((list.get(k).get_ver()).equals(newver))
                {
                    space.setText(newver + " vertex found at " + Integer.toString(list.get(k).get_x()) + "," + Integer.toString(list.get(k).get_y()));
                    break;
                }
            }
            if(k==list.size())
                space.setText("vertex not found");
            if(newver.equals(""));
            throw new MyException("");
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }

    }
    public void delete_input(ActionEvent e)
    {
        try
        {
            String newver = delvertex.getText();
            int k=-1;
            int flag=0;
            for ( k = 0; k < list.size(); k++) {
                if ((list.get(k).get_ver()).equals(newver)) {
                    flag=1;
                    list.remove(k);
                    space.setText(newver + " vertex removed ");

                }
            }

                if(flag==0)
                    throw new MyException("");
            if(newver.equals(""))
                throw new MyException("");
            for ( k = 0; k < list.size(); k++)
            {
                for (int m = 0; m < list.get(k).list2.size(); m++)
                {
                    if (list.get(k).list2.get(m).get_to().equals(newver))
                    {
                        list.get(k).list2.remove(m);
                    }
                }
            }
            clears() ;
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void clears()
    {
        int r=0,c=0;
        animation.getChildren().clear();
        for(int i=0;i<list.size();i++)
        {
          r=list.get(i).get_x();
          c=list.get(i).get_y();
            Circle dot = new Circle();
            dot.setCenterX(r*10);
            dot.setCenterY(c*10);
            dot.setRadius(20);
            dot.setFill(Color.WHITE);
            dot.setStroke(Color.ORANGE);
            dot.setStrokeWidth(10);
            animation.getChildren().add(dot);
            Text text=new Text();
            text.setText(list.get(i).get_ver());
            text.setX(r*10-1);
            text.setY(c*10+9);
            text.setStroke(Color.BLACK);
            text.setFont(Font.font("Verdana",20));
            animation.getChildren().add(text);

        }
        int i=0,j=0;
        for( i=0;i<list.size();i++)
        {
            for( j=0;j<list.get(i).list2.size();j++)
            {
                String s=list.get(i).list2.get(j).get_to();
                        for(int k=0;k<list.size();k++)
                        {
                            if(list.get(k).get_ver().equals(s)) {
                                Line line = new Line();
                                line.setStartX(list.get(i).get_x() * 10);
                                line.setStartY(list.get(i).get_y() * 10);
                                line.setEndX(list.get(k).get_x() * 10);
                                line.setEndY(list.get(k).get_y() * 10);
                                line.setStroke(Color.RED);
                                line.setStrokeWidth(4);
                                animation.getChildren().add(line);
                            }
                        }

            }
        }
    }
    public void modify_input(ActionEvent e)
    {
        try
        {
            String ver = modvertex.getText();
            String xcor = newx.getText();
            String ycor = newy.getText();
            int i = Integer.parseInt(xcor);
            int j = Integer.parseInt(ycor);
            int k=-1;
            int flag=0;
            for ( k = 0; k < list.size(); k++) {
                if ((list.get(k).get_ver()).equals(ver)) {
                    flag=1;
                    list.get(k).set_x(i);
                    list.get(k).set_y(j);
                }
            }
            clears();

            if(flag==0)
                throw new MyException("");
            space.setText(ver + " now has coordinates " + xcor + "," + ycor);
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
        catch (NumberFormatException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }

    }
    public void add_edge(ActionEvent e)
    {
        try
        {
            String from = fromvertex.getText();
            String to = tovertex.getText();
            String wt = weight.getText();
            int w = Integer.parseInt(wt);
            int k=0;
            int flag1=0,flag2=0;
            int r=0,c=0;
            for ( k = 0; k < list.size(); k++)
            {
                if ((list.get(k).get_ver()).equals(from))
                {
                     r=k;
                     flag1=1;
                    list.get(k).list2.add(new Ashima2());
                    list.get(k).list2.get(list.get(k).list2.size() - 1).add(to, w);
                }
                if(list.get(k).get_ver().equals(to)) {
                    c = k;
                    flag2=1;
                }
            }
            Line line = new Line();
            line.setStartX(list.get(r).get_x()*10);
            line.setStartY(list.get(r).get_y()*10);
            line.setEndX(list.get(c).get_x()*10);
            line.setEndY(list.get(c).get_y()*10);
            line.setStroke(Color.RED);
            line.setStrokeWidth(4);
            animation.getChildren().add(line);
 clears();
            if(flag1==0||flag2==0)
                throw new MyException("");
            space.setText("an edge is added between vertex " + from + " and vertex " + to);
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
        catch (NumberFormatException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");                                   //Alert code taken from https://code.makery.ch/blog/javafx-dialogs-official/
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void search_edge(ActionEvent e)
    {
        try
        {
            String from = sfromvertex.getText();
            String to = stovertex.getText();
            int k=0 ,m=0;
            for ( k = 0; k < list.size(); k++)
            {
                if ((list.get(k).get_ver()).equals(from))
                {
                    for ( m = 0; m < list.get(k).list2.size(); m++)
                    {
                        if (list.get(k).list2.get(m).get_to().equals(to))
                        {
                            space.setText("The edge is found between vertex " + from + " and vertex " + to + " with weight " + Integer.toString(list.get(k).list2.get(m).get_w()));
                        }
                    }
                }
            }
            if(from.equals("")||to.equals(""))
                throw new MyException("");
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void delete_edge(ActionEvent e)
    {
        try
        {
            String from = dfromvertex.getText();
            String to = dtovertex.getText();
            int k=-1,m=-1;
            int flag1=0,flag2=0;
            for ( k = 0; k < list.size(); k++)
            {
                if ((list.get(k).get_ver()).equals(from))
                {
                    flag1=1;
                    for ( m = 0; m < list.get(k).list2.size(); m++)
                    {
                        if (list.get(k).list2.get(m).get_to().equals(to))
                        {
                            flag2=1;
                            list.get(k).list2.remove(m);
                            space.setText("The edge  between vertex " + from + " and vertex " + to + " is deleted ");
                        }
                    }
                }
            }
            clears();

            if(flag1==0||flag2==0)
                throw new MyException("");
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void modify_edge(ActionEvent e)
    {
        try
        {
            String from = mfromvertex.getText();
            String to = mtovertex.getText();
            String wt = mweight.getText();
            int w = Integer.parseInt(wt);
            int k=-1,m=-1;
            int flag1=0,flag2=0;
            for ( k = 0; k < list.size(); k++) {
                if ((list.get(k).get_ver()).equals(from)) {
                    flag1 = 1;
                    for (m = 0; m < list.get(k).list2.size(); m++) {
                        if (list.get(k).list2.get(m).get_to().equals(to)) {
                            flag2 = 1;
                            list.get(k).list2.get(m).set_w(w);
                            space.setText("The edge  between vertex " + from + " and vertex " + to + " is now of weight " + Integer.toString(list.get(k).list2.get(m).get_w()));
                        }
                    }
                }
            }
            if(flag1==0||flag2==0)
            throw new MyException("");
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
        catch(NumberFormatException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");                                   //Alert code taken from https://code.makery.ch/blog/javafx-dialogs-official/
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void add_file (ActionEvent e)throws Exception
    {
        try
        {
            String fpath = filepath.getText();
            filepath.clear();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fpath));
            String s1 = bufferedReader.readLine();
            int n = Integer.parseInt(s1);
            for (int i = 0; i < n; i++) {
                String s2 = bufferedReader.readLine();
                String[] arr = s2.split(" ", 5);
                vertex.setText(arr[0]);
                x.setText(arr[1]);
                y.setText(arr[2]);
                add.fire();

            }
            s1 = bufferedReader.readLine();
            n = Integer.parseInt(s1);
            for (int i = 0; i < n; i++) {
                String s2 = bufferedReader.readLine();
                String[] arr = s2.split(" ", 5);
                fromvertex.setText(arr[0]);
                tovertex.setText(arr[1]);
                weight.setText(arr[2]);
                addedge.fire();
            }
        }
        catch(FileNotFoundException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
        catch(ArrayIndexOutOfBoundsException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public void export_data(ActionEvent e)throws Exception
    {
        try
        {
            String fpath = ofilepath.getText();
            ofilepath.clear();
            File file = new File(fpath);
            System.setOut(new PrintStream(file));
            System.out.println(list.size());
            Collections.sort(list, new check());
            for (int k = 0; k < list.size(); k++) {
                System.out.println(list.get(k).get_ver() + " " + list.get(k).get_x() + " " + list.get(k).get_y());
            }
            int count = 0;
            for (int k = 0; k < list.size(); k++) {
                for (int m = 0; m < list.get(k).list2.size(); m++) {
                    count++;
                }
            }
            for (int k = 0; k < list.size(); k++) {
                list.get(k).get_it_done();
            }
            System.out.println(count);
            for (int k = 0; k < list.size(); k++) {
                for (int m = 0; m < list.get(k).list2.size(); m++) {
                    System.out.println(list.get(k).get_ver() + " " + list.get(k).list2.get(m).get_to() + " " + list.get(k).list2.get(m).get_w());
                }
            }
            space.setText("the graph is successfully exported");
        }
        catch(FileNotFoundException e1)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }
    }
    public static int min_dist2(Map<String,Double> dist, Map<String,Integer> finals, ArrayList<Ashima> list)
    {
        double mn=(double)10000;
        int idx=-1;
        for(int g=0;g<list.size();g++)
        {
            if(finals.get(list.get(g).get_ver())==0 && dist.get(list.get(g).get_ver())<mn)
            {
                mn=dist.get(list.get(g).get_ver());
                idx=g;
            }
        }
        return idx;
    }
    public static String min_dist(Map<String,Double> dist,Map<String,Integer> finals, ArrayList<Ashima> list)
    {
        double mn=(double)10000;
        int idx=-1;
        String str="";
        for(int g=0;g<list.size();g++)
        {
            if(finals.get(list.get(g).get_ver())==0 && dist.get(list.get(g).get_ver())<mn)
            {
                mn=dist.get(list.get(g).get_ver());
                idx=g;
                str=list.get(g).get_ver();
            }
        }
        return str;
    }
    /*public void printpath(Map<String,String> parent,String s)
    {
        if(parent.get(s).equals("NULL"))
        {
            return;
        }
        printpath(parent,parent.get(s));
        space.setText(s);
    }*/
    /*public void  printsolution(Map<String,String> parent,ArrayList<Ashima> list,Map<String,Double> dist,String src)
    {
        //if(parent.get(go).equals("we_are_here"))
           // return ;
        //String parent_of_dest="";
        // System.out.println(dest);
        for(int j=0;j<list.size();j++)
        {
            if(dist.get(list.get(j).get_ver())==10000)
            {
                space.setText("Path does not exist");
            }
            else
            {
                space.setText(src+" ");
                printpath(parent,list.get(j).get_ver());
                space.setText("\n");
            }
        }
    }*/
    public void dijkstra(ActionEvent e) {
        try
        {
            String src = source.getText();
            String go = goal.getText();
            String stype=type.getText();
            int flag1=0,flag2=0;
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).get_ver().equals(src))
                    flag1=1;
                if(list.get(i).get_ver().equals(go))
                    flag2=1;
            }
            if(flag1==0||flag2==0)
                throw new MyException("");
            Map<String, Double> dist = new HashMap<String, Double>();
            Map<String, Integer> finals = new HashMap<String, Integer>();
            Map<String, String> parent = new HashMap<String, String>();
            if(src.equals("")||go.equals(""))
                throw new MyException("");
            //int a = 0, b = 0;
            for (int k = 0; k < list.size(); k++) {
                dist.put(list.get(k).get_ver(), (double) 10000);
                finals.put(list.get(k).get_ver(), 0);
                parent.put(list.get(k).get_ver(), "");
           /* if (list.get(k).get_ver().equals(src)) {
                a = 1;
            }
            if (list.get(k).get_ver().equals(go)) {
                b = 1;
            }*/
            }
            /*if(src.equals(go))
            {
                space.setText("source on landmark");
            }
            else if(go.equals("") || a==0 || b==0)
            {
                space.setText("no path exists");
            }*/
            dist.put(src, (double) (0));
            // System.out.println(dest);
            parent.put(src, "NULL");
            for (int g = 0; g < list.size(); g++) {
                String u = min_dist(dist, finals, list);
                int v = min_dist2(dist, finals, list);
                    /*if(v==-1 || u.equals(go))
                    {
                        break;
                    }*/
                finals.put(u, 1);
                for (int m = 0; m < list.get(v).list2.size(); m++) {
                    if (finals.get(list.get(v).list2.get(m).get_to()) == 0 && dist.get(u) != 10000 && dist.get(u) + list.get(v).list2.get(m).get_w() < dist.get(list.get(v).list2.get(m).get_to())) {
                        dist.put(list.get(v).list2.get(m).get_to(), dist.get(u) + list.get(v).list2.get(m).get_w());
                        parent.put(list.get(v).list2.get(m).get_to(), list.get(v).get_ver());
                    }
                }
            }
            if (dist.get(go) == (double) (10000))
                space.setText("no path exists");
            else {
                String[] ans = new String[list.size() + 2];
                ans[0] = go;
                int i = 1;
                String q = go;
                while (!(parent.get(q).equals("NULL"))) {
                    ans[i] = parent.get(q);
                    q = parent.get(q);
                    i++;
                }
                String r = "";
                for (int j = i - 1; j >= 0; j--) {
                    r = r + ans[j] + " ";
                    space.setText(r);
                }

                if(stype.equalsIgnoreCase("line"))
                {
                    int x=0,y=0,x1=0,y1=0;
                    for (int l = i - 1; l >= 1; l--) {
                        String ab = ans[l];
                        String cd=ans[l-1];
                        for (int h = 0; h < list.size(); h++) {
                            if (list.get(h).get_ver().equals(ab)) {
                                x = list.get(h).get_x();
                                y = list.get(h).get_y();
                            }
                                if(list.get(h).get_ver().equals(cd))
                                {
                                    x1 = list.get(h).get_x();
                                    y1 = list.get(h).get_y();
                                //LineTo lineTo = new LineTo(x * 15, y * 15);
                                //path.getElements().add(lineTo);
                            }
                        }
                        Line line = new Line();
                        line.setStartX(x*10);
                        line.setStartY(y*10);
                        line.setEndX(x1*10);
                        line.setEndY(y1*10);
                        line.setStroke(Color.INDIGO);
                        line.setStrokeWidth(8);
                        animation.getChildren().add(line);
                    }
                }
                else {
                    String ab=ans[i-1];
                    int x=0,y=0;
                    for(int h=0;h<list.size();h++)
                    {
                        if(list.get(h).get_ver().equals(ab)) {
                            x = list.get(h).get_x();
                            y = list.get(h).get_y();
                        }
                    }
                    PathTransition pathTransition = new PathTransition();
                    Circle circle = new Circle();
                    Line line = new Line();
                    Line line2 = new Line();
                    Rectangle rectangle = new Rectangle();
                    Polygon triangle = new Polygon();
                    if (stype.equalsIgnoreCase("circle")) {

                        pathTransition.setNode(circle);
                        circle.setCenterX(30);
                        circle.setCenterY(20);
                        circle.setRadius(25);
                        circle.setFill(Color.BLUE);
                        animation.getChildren().add(circle);
                        pathTransition.setNode(circle);
                    } else if (stype.equalsIgnoreCase("square")) {
                        pathTransition.setNode(rectangle);
                        rectangle.setX(20);
                        rectangle.setY(30);
                        rectangle.setHeight(20);
                        rectangle.setWidth(20);
                        rectangle.setFill(Color.BLUEVIOLET);
                        animation.getChildren().add(rectangle);
                        pathTransition.setNode(rectangle);
                    } else if (stype.equalsIgnoreCase("cross")) {

                        line.setStartX(0);
                        line.setStartY(0);
                        line.setEndX(10);
                        line.setEndY(10);
                        line.setStroke(Color.YELLOW);
                        line.setStrokeWidth(4);
                        animation.getChildren().add(line);
                        line2.setStartX(0);
                        line2.setStartY(10);
                        line2.setEndX(10);
                        line2.setEndY(0);
                        line2.setStroke(Color.YELLOW);
                        line2.setStrokeWidth(4);
                        animation.getChildren().add(line2);
                    } else if (stype.equalsIgnoreCase("plus")) {
                        line.setStartX(5);
                        line.setStartY(15);
                        line.setEndX(5);
                        line.setEndY(-5);
                        line.setStroke(Color.MAGENTA);
                        line.setStrokeWidth(4);
                        animation.getChildren().add(line);
                        line2.setStartX(0);
                        line2.setStartY(10);
                        line2.setEndX(10);
                        line2.setEndY(10);
                        line2.setStroke(Color.MAGENTA);
                        line2.setStrokeWidth(4);
                        animation.getChildren().add(line2);

                    } else if (stype.equalsIgnoreCase("triangle")) {
                        pathTransition.setNode(triangle);
                        triangle.getPoints().addAll(new Double[]{
                                0.0, 0.0,
                                30.0, 0.0,
                                15.0, 30.0});
                        triangle.setFill(Color.GREEN);
                        animation.getChildren().add(triangle);
                        pathTransition.setNode(triangle);
                    }
                    pathTransition.setDuration(Duration.millis(1000));

                    if (stype.equalsIgnoreCase("cross")) {
                        pathTransition.setNode(line);
                    } else if (stype.equalsIgnoreCase("plus")) {
                        pathTransition.setNode(line);
                    }
                    Path path = new Path();
                    MoveTo moveTo = new MoveTo(x * 10, y * 10);
                    path.getElements().add(moveTo);
                    for (int l = i - 2; l >= 0; l--) {
                        ab = ans[l];
                        for (int h = 0; h < list.size(); h++) {
                            if (list.get(h).get_ver().equals(ab)) {
                                x = list.get(h).get_x();
                                y = list.get(h).get_y();
                                LineTo lineTo = new LineTo(x * 10, y * 10);
                                path.getElements().add(lineTo);
                            }
                        }
                    }
                    pathTransition.setPath(path);
                    pathTransition.setCycleCount(500);
                    pathTransition.setAutoReverse(false);
                    pathTransition.play();


                    PathTransition pathTransition2 = new PathTransition();
                    pathTransition2.setDuration(Duration.millis(1000));
                    if (stype.equalsIgnoreCase("cross") || stype.equalsIgnoreCase("plus")) {
                        pathTransition2.setNode(line2);
                    }

                    pathTransition2.setPath(path);
                    pathTransition2.setCycleCount(500);
                    pathTransition2.setAutoReverse(false);
                    pathTransition2.play();
                }
                //space.setText(go+"\n");
            }
        }
        catch(MyException ex)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid values");
            alert.setContentText("Enter the correct values");
            alert.showAndWait();
        }


    }


}
