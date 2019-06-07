package drawBot;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DragAndDrop {
	static String path ="";
	public JComponent makeUI() {
	    final JLabel label = new JLabel("Drop image from a web browser and then press UP.");
	    label.setTransferHandler(new TransferHandler() {
	      @Override public boolean canImport(JComponent component, DataFlavor[] flavors) {
	        return true;
	      }
	      @Override public boolean importData(JComponent component, Transferable transferable) {
	        label.setText("");
	        try {
	          for (DataFlavor flavor : transferable.getTransferDataFlavors()) {
	            System.out.println(flavor);
	            if (DataFlavor.imageFlavor.equals(flavor)) {
	              Object o = transferable.getTransferData(DataFlavor.imageFlavor);
	              ImageIcon io = new ImageIcon((Image) o);
	              if (o instanceof Image) {
	                label.setIcon(io);
	                
	                return true;
	              }
	            }
	            if (DataFlavor.javaFileListFlavor.equals(flavor)) {
	              Object o = transferable.getTransferData(DataFlavor.javaFileListFlavor);
	              if (o instanceof List) {
	                List list = (List) o;
	                for (Object f : list) {
	                  if (f instanceof File) {
	                    File file = (File) f;
	                    System.out.println(file);
	                    if (!file.getName().endsWith(".bmp")) {
	                      label.setIcon(new ImageIcon(file.getAbsolutePath()));
	                      path = file.getAbsolutePath();
	                      return true;
	                    }
	                  }
	                }
	              }
	            }
	          }
	        } catch (Exception ex) {
	          ex.printStackTrace();
	        }
	        return false;
	      }
	      @Override public int getSourceActions(JComponent component) {
	        return COPY;
	      }
	    });
	    return new JScrollPane(label);
	  }
	  public static void main(String... args) {
	    EventQueue.invokeLater(new Runnable() {
	      @Override public void run() {
	        createAndShowGUI();
	      }
	    });
	  }
	  public static void createAndShowGUI() {
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.getContentPane().add(new DragAndDrop().makeUI());
	    frame.setSize(500, 300);
	    JButton button = new JButton("Draw");
		button.setBounds(50, 100, 100, 50);
		frame.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
		        if(e.getKeyCode() == KeyEvent.VK_UP) {
		        	App a = new App();
		        	try {
		        		BufferedImage imageToSend;
		        		imageToSend = ImageIO.read(new File(path));
						a.draw(imageToSend);
					} catch (AWTException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		        	System.exit(0); 
		        }
		    }
		});
		//frame.add(button);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		frame.setAlwaysOnTop( true );
	  }
}