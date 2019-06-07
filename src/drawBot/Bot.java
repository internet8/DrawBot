package drawBot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Bot {
	
    public static void click(int x, int y) throws AWTException, InterruptedException{
        Robot r = new Robot();
        r.mouseMove(x, y);    
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void clickDown(int x, int y) throws AWTException, InterruptedException{
    	Robot r = new Robot();
        Thread.sleep(1);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void release(int x, int y) throws AWTException, InterruptedException{
        Robot r = new Robot();
        Thread.sleep(1);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void move(int x, int y) throws AWTException, InterruptedException{
        Robot r = new Robot();
        //Thread.sleep(1);
        r.mouseMove(x, y);    
    }
}
