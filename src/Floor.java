
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Java Class to manage a floor

public class Floor
{
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private int x6;
    private int x7;
    private int x8;
    private int y;
    private int l;
    private int floor;
    private List<Passenger> passengers;
    private List<Passenger> departing;
    
    public Floor(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int y, int l, int floor)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.x6 = x6;
        this.x7 = x7;
        this.x8 = x8;
        this.y = y;
        this.l = l;
        this.floor = floor;
        this.passengers = Collections.synchronizedList(new ArrayList());
        this.departing = Collections.synchronizedList(new ArrayList());
    }
    
    // draw the floor on the GUI
    public void draw(Graphics g)
    {
        g.drawLine(0, y, x1, y);
        g.drawLine(x2, y, x3, y);
        g.drawLine(x4, y, x5, y);
        g.drawLine(x6, y, x7, y);
        g.drawLine(x8, y, l, y);
        
        g.setColor(Color.LIGHT_GRAY);
        
        g.fillRect(222, y - 50, 8, 10);
        g.fillRect(372, y - 50, 8, 10);
        g.fillRect(372 + 150, y - 50, 8, 10);
        g.fillRect(372 + 2*150, y - 50, 8, 10);
        
        g.drawLine(x1, y, x2, y);
        g.drawLine(x3, y, x4, y);
        g.drawLine(x5, y, x6, y);
        g.drawLine(x7, y, x8, y);
        
        g.setColor(Color.BLACK);
        
        g.drawString("Floor " + (floor), 30, y - 45);
        
        // draw passengers on the floor
        for (int i = 0, j = 800; i < passengers.size(); i++, j += 50)
        {
            passengers.get(i).setDestPosX(j);
            passengers.get(i).draw(g);
        }
        
        Iterator iterator = departing.iterator();
        
        // loop to draw departing passengers, such that they are seen moving away from the elevators towards right
        while (iterator.hasNext())
        {
            Passenger temp = (Passenger) iterator.next();
            
            temp.setDestPosX(1050 + 25);
            temp.setDirection(Passenger.Mode.RIGHT);
            temp.draw(g);
            
            if (temp.getX() == temp.getDestPosX())
            {
                iterator.remove();
            }
        }
    }
    
    
    public void addPassenger(Passenger passenger)
    {
        int k = (!passengers.isEmpty()) ? passengers.get(passengers.size() - 1).getX() + 1050 : 1050;
        
        passenger.setX(k);
        passengers.add(passenger);
    }
    
    public int getFloor()
    {
        return this.floor;
    }
    
    public List<Passenger> getPassengers()
    {
        return this.passengers;
    }
    
    public List<Passenger> getDeparting()
    {
        return this.departing;
    }
}