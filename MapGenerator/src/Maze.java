

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static sun.awt.geom.Crossings.debug;

public class Maze {
    public StringBuilder maze ;
    private int start = 1;
    private JTextPane text = new JTextPane();
    private int width;
    private int height;
    private JMenuBar menu = new JMenuBar();

    public Maze(int height, int width){
        this.width  = width;
        this.height = height;
    }


    public void buildGraph(){
        maze = new StringBuilder( "x x");
        for (int i = 1; i < width; i++) {
            maze.append("xx");
        }
        maze.append("\n");
        for (int i = 0; i < height*2-1; i++) {
            maze.append("x");
            if(i%2==0) {
                for (int j = 0; j < width; j++) {
                    maze.append(" x");
                }
            }else{
                for (int j = 0; j < width; j++) {
                    maze.append("xx");
                }

            }maze.append("\n");
        }
        for (int i = 1; i < width; i++) {
            maze.append("xx");
        }maze.append("x x");


    }
    public void buildMaze(){
        int vertex = width*2+3;//(5*2)+2+1//10*2 +2+1
        DFS(vertex);


    }
    public void DFS(int vertex) {
        Stack stack = new Stack();
        List<Integer> list = neighbors(vertex);

        /*        if (vertex!='v'){*/
        stack.push(vertex);

        maze.setCharAt(vertex, 'v');//visited
        while (list.size()>0) {
            Random r = new Random();
            int ran = r.nextInt(list.size());
            int choice = list.get(ran);
            if(maze.charAt(choice)!='v') {
                maze.setCharAt((vertex + choice) / 2, ' ');//removes wall

                //maze.setCharAt( choice, ' ');
                DFS(choice);//

            }
                list.remove(ran);
            //stack.pop();//removes list.charAt(ran)

        }
        System.out.println(stack.toString());

    }
    public void DFSS(int vertex) {
        Stack stack = new Stack();

        List<Integer> list = neighbors(vertex);

        /*        if (vertex!='v'){*/
        stack.push(vertex);

        maze.setCharAt(vertex, 'v');//visited
        while (list.size() > 0) {
            Random r = new Random();
            int ran = r.nextInt(list.size());
            int choice = list.get(ran);
            if (maze.charAt(choice) == 'v') {
                maze.setCharAt(choice, '+');

                //maze.setCharAt( choice, ' ');
                DFSS(choice);//

            }
        }
    }

    public void replace(int row, int column, char replace){

         maze.setCharAt(positionVert(row,column),replace);

    }
    public int positionVert(int row, int column) {
        int pos = (row + 0) * (width * 2 + 2) * 2 + (column * 2 + 1) + (width * 2 + 2);
        return pos;
    }
    public List<Integer> neighbors(int pos) {
        List<Integer> list = new ArrayList<Integer>();
        int adjUp = pos - width * 4 - 4;

        int adjDown = pos + width * 4 + 4;
        int forward = pos + 2;
        int back = pos - 2;
        if (adjUp >= 0) {
            list.add(adjUp);//0
        }
        if (adjDown < maze.length()) {
            list.add(adjDown);
        }
        if (maze.charAt(forward) != '\n') {
            list.add(forward);//2
        }
        if (maze.charAt(back)!='\n') {
            list.add(back);
        }
        return list;
    }
    public void solveMaze(){
        DFSS(width*2+3);
       // DFS(width+3);
    }


    public StringBuilder removeV(StringBuilder maze){
        for (int i = 0; i <maze.length() ; i++) {
            if(maze.charAt(i)=='v'){
                maze.setCharAt(i,' ');
            }
        }return Maze.this.maze = maze;
    }
    public String toString(){return maze.toString();}
    public void graphic(){
        /////Button
        Dimension buttonDims = new Dimension(100,20);
        JButton button = new JButton();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        Point point = new Point(650,625);
        Point point1 = new Point(650,(point.y-25));
        Point point2 = new Point(650,(point.y+25));
        button.setText("10x15");
        button1.setText("5x5");
        button2.setText("20x40");
        button.setSize(buttonDims);
        button1.setSize(buttonDims);
        button2.setSize(buttonDims);;
        button.setVisible(true);
        button1.setVisible(true);
        button2.setVisible(true);
        button.setLocation(point);
        button1.setLocation(point1);
        button2.setLocation(point2);

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Maze.this.height = 10;
                Maze.this.width = 15;

                buildGraph();
                buildMaze();
                //solveMaze();
               // System.out.println(maze.toString());
                removeV(maze);
                text.setText(maze.toString());
                //graphic();

            }

        };
        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Maze.this.height = 5;
                Maze.this.width = 5;

                buildGraph();
                buildMaze();
                //solveMaze();
                removeV(maze);
                text.setText(maze.toString());
                //graphic();

            }

        };
        ActionListener al2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Maze.this.height = 20;
                Maze.this.width = 40;

                buildGraph();
                buildMaze();
               // solveMaze();
                removeV(maze);
                text.setText(maze.toString());
                //graphic();

            }

        };
        /////Action Listeners
        button.addActionListener(al);
        button1.addActionListener(al1);
        button2.addActionListener(al2);
        /////Text
        //text.setText(maze.toString());
        text.setEditable(true);
        text.setSize(600,700);
        Font font = new Font(
                Font.MONOSPACED,
                Font.BOLD,
                text.getFont().getSize());
        text.setFont(font);
        removeV(maze);
        text.setText(maze.toString());

        /////Frame
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("Maze Generator");

        panel.setLayout(new GridLayout());
        Dimension dims = new Dimension();


        dims.height = text.getHeight()+100;//800;
        dims.width  = text.getWidth()+200;//600;
        frame.setMinimumSize(dims);
       /* frame.setJMenuBar(menu);
        frame.add(menu);
        menu.setVisible(true);*/

        /////Components
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(button);
        frame.add(button1);
        frame.add(button2);
        frame.add(text);
        frame.add(panel);


        frame.setVisible(true);


    }



    public static void main(String[] args) {
        Maze maze = new Maze(10,15);
        maze.buildGraph();
        maze.buildMaze();
        // draw the maze }
        /*if (debug) {maze.buildMaze();};*/
        //System.out.println(maze.toString());
        maze.removeV(maze.maze);
        System.out.println(maze.toString());
        maze.graphic();

    }

}

