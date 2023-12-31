
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// This class represents the elevator car. Atributes and methods of the class are discribed below.

public class Elevator
{
    private int xAxis;   // x position
    private int yAxis;   // y position
    private int width;   
    private int height;
    private int doorWidth;
    private int floor;

    private boolean isOpen;
    private boolean isClose;
    
    private Mode mode;
    private Mode direction;
    private List<Passenger> passengers;

    public boolean test1;
    public boolean test2;
    public boolean test3;
    public boolean test4;
    public boolean test5;
    public boolean test6;
    public boolean test7;
    
    // constructor
    public Elevator(int xAxis, int yAxis)
    {
        this(xAxis, yAxis, 100, 100, 0);
    }
    
    // constructor
    public Elevator(int xAxis, int yAxis, int width, int height, int floor)
    {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.doorWidth = width / 2;
        this.floor = floor;
        this.isOpen = false;
        this.isClose = true;
        this.mode = Mode.WAIT;
        this.direction = Mode.UP;
        this.passengers = Collections.synchronizedList(new ArrayList());
        
        test1 = true;
        test2 = false;
        test3 = false;
        test4 = false;
        test5 = false;
        test6 = false;
        test7 = false;
    }
    
    // method to draw the elevator
    public void draw(Graphics g)
    {
        for (Passenger passenger : passengers)
        {
            passenger.draw(g);
        }
        
        g.drawRect(xAxis, yAxis, width, height);
        g.setColor(Color.BLUE);
        g.fillRect(xAxis, yAxis, doorWidth, height);
        g.fillRect(xAxis + width - doorWidth, yAxis, doorWidth, height);
        g.setColor(Color.BLACK);
        g.drawRect(xAxis, yAxis, doorWidth, height);
        g.drawRect(xAxis + width - doorWidth, yAxis, doorWidth, height);
        step();
    }
    
    // controls the movement of the elevator
    public void step()
    {
        switch (mode)
        {
            case UP:
                
                --yAxis;
                
                for (Passenger passenger : passengers)
                	
                {
                	// move each passenger up by 1 unit
                    passenger.setY(passenger.getY() - 1);
                }

                if (yAxis % 100 == 0)
                {
                    ++floor;
                    
                    // switch direction when the elevator reaches floor 5
                    if (floor == 5)
                    {
                        direction = Mode.DOWN;
                    }
                }

                break;
                
            case DOWN:
                
                ++yAxis;
                
                for (Passenger passenger : passengers)
                {
                	// move each passenger down by 1 unit
                    passenger.setY(passenger.getY() + 1);
                }

                if (yAxis % 100 == 0)
                {
                    --floor;
                    
                    if (floor == 0)
                    {
                        direction = Mode.UP;
                    }
                }
                
                break;
                
            // open the elevator car doors    
            case OPEN:
                
                if (doorWidth > 0)
                {
                    --doorWidth;
                }
                else if (doorWidth == 0)
                {
                    isOpen = true;
                    isClose = false;
                }
                
                break;
                
            // close the elevator car doors
            case CLOSE:
                
                if (doorWidth < width / 2)
                {
                    ++doorWidth;
                }
                else if (doorWidth == width / 2)
                {
                    isOpen = false;
                    isClose = true;
                }
                
                break;
                
            default:
                
                break;
        }
    }
    
    // passengers leaving the cart
    public void alight(List<Passenger> passengers)
    {
        Iterator iterator = this.passengers.iterator();
        
        while (iterator.hasNext())
        {
            Passenger temp = (Passenger) iterator.next();
            
            if (temp.getDestinationFloor() == floor)
            {
                passengers.add(temp);
                iterator.remove();
            }
        }
    }
    
    // passengers getting on the cart
    public void board(List<Passenger> passengers)
    {
        int totalWeight = 0;
        
        for (Passenger passenger : passengers)
        {
            totalWeight += passenger.getWeight();
        }
        
        // a maximum of 9 passengers per elevator are allowed with a weight limit of 700
        while (this.passengers.size() < 10 && !passengers.isEmpty() && totalWeight <= 700)
        {
            this.passengers.add(passengers.get(0));
            passengers.remove(0);
        }
        
        // arrange multiple passengers in the elevator
        int x = 100 / (this.passengers.size() + 1);
        
        for (int i = 0, j = xAxis - 5 + x; i < this.passengers.size(); i++, j += x)
        {
            this.passengers.get(i).setDestPosX(j);
        }
    }
    
    public boolean isOpen()
    {
        return this.isOpen;
    }
    
    public boolean isClose()
    {
        return this.isClose;
    }
    
    public void setMode(Mode mode)
    {
        this.mode = mode;
    }
    
    public void setDirection(Mode direction)
    {
        this.direction = direction;
    }
    
    public int getFloor()
    {
        return this.floor;
    }
    
    public List<Passenger> getPassengers()
    {
        return this.passengers;
    }
    
    public Mode getMode()
    {
        return this.mode;
    }
    
    public Mode getDirection()
    {
        return this.direction;
    }
    
    public int getX()
    {
        return this.xAxis;
    }
    
    public int getY()
    {
        return this.yAxis;
    }
    
    public enum Mode {UP, DOWN, OPEN, CLOSE, WAIT};
}