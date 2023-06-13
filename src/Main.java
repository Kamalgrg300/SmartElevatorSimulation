
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Main class which is responsible for creating a JFrame for the GUI and an object of the World class
// which is able to control the elevator system. The simulation is controlled using a start and a stop button.

public class Main
{
    private final JFrame frame;
    private final JPanel panel;
    private final JButton btnStart;
    private final JButton btnStop;
    private World world;
    
    public Main()
    {
        world = new World();
        world.setBounds(0, 0, 1050, 750);
        
        btnStart = new JButton("Start");
        btnStart.setBounds(290, 762, 80, 25);
        btnStart.setFocusPainted(false);
        btnStart.addActionListener((e) -> {
            world.start();
            System.out.println("pressed start");
        });
        
        btnStop = new JButton("Stop");
        btnStop.setBounds(380, 762, 80, 25);
        btnStop.setFocusPainted(false);
        btnStop.addActionListener((e) -> {
            world.stop();
            System.out.println("pressed stop");
        });
        
        panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(1050, 800));
        panel.add(world);
        panel.add(btnStart);
        panel.add(btnStop);
        
        frame = new JFrame("Elevator Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        Main main = new Main();
    }
}
