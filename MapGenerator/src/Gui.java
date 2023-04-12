import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Gui {
    private JTextPane text = new JTextPane();
    private int width;
    private int height;
    private JMenuBar menu = new JMenuBar();
    public Gui(int height, int width){
        this.width  = width;
        this.height = height;
    }
    public Gui(){

    }
    public void buildGui(){
        graphics();
    }

    public static void main(String[] args) {
        Gui g = new Gui();

        g.buildGui();
    }
    public void graphics(){

        /** buttons for the gui */
        Dimension buttonDims = new Dimension(10,20);
        JButton button = new JButton();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        Point point = new Point(650,625);
        Point point1 = new Point(650,(point.y-25));
        Point point2 = new Point(650,(point.y+25));
        button.setText("up");
        button1.setText("left");
        button2.setText("right");
        button3.setText("back");
        button.setSize(buttonDims);
        button1.setSize(buttonDims);
        button2.setSize(buttonDims);
        button3.setSize(buttonDims);
        button.setVisible(true);
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button.setLocation(point);
        button1.setLocation(point1);
        button2.setLocation(point2);
        button3.setLocation(point2);
        text.setEditable(true);
        text.setSize(600,700);
        Font font = new Font(
                Font.MONOSPACED,
                Font.BOLD,
                text.getFont().getSize());
        text.setFont(font);
        /** frame implementation*/
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JPanel frame1 = new JPanel();
        frame.setTitle("Trivia Maze");

        panel.setLayout(new BorderLayout());
        Dimension dims = new Dimension();


        dims.height = text.getHeight()+100;//800;
        dims.width  = text.getWidth()+200;//600;
        frame.setMinimumSize(dims);
        /////Components

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame1.add(button);
        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);
        //panel.add(text);
      /*  frame.add(button);
        frame.add(button1);
        frame.add(button2);*/
        //frame.add(text);
        frame.add(panel);
        frame.add(frame1);

        frame.setVisible(true);


    }
}
