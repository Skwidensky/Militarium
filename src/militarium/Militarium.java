/*
 * Charles Swedensky
 * The year of the snek, two-thousand-and-fifteen
 * Metamorphosis
 */
package militarium;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Charles
 */
class Surface extends JPanel {
    private static HashMap<String, Piece> pieceMap = null;
    String column, xy, type;
    int row;
    int pos1;
    int pos2;
    
    public void doDrawing(Graphics g) throws IOException {
        
        Graphics2D g2d = (Graphics2D) g;
        Dimension size = getSize();
        Insets insets = getInsets();        
        
        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;
                
        int squareSize = 64;
        
        // draw the board
        for (int i=0; i<64; i+=2) {
            g.setColor(Color.WHITE);
            g.fillRect((i%8 + (i/8)%2) * squareSize, (i/8)*squareSize, squareSize, squareSize);
            g.setColor(Color.BLACK);
            g.fillRect(((i+1)%8 - ((i+1)/8)%2)*squareSize, ((i+1)/8)*squareSize, squareSize, squareSize);
        }        
        
        // read in file
        try {
            FileReader fr = new FileReader("src/militarium/fischer_spassky_game3.txt");            
            BufferedReader br = new BufferedReader(fr);
            //ArrayList[] String capPoints = new ArrayList[];
            pieceMap = new HashMap();
            row = 8;
            column = "a";
            int turn = 0;
            int shift = 0; // constant to shift lines to give more depth
            int x = 32; // upper-right first piece x
            int y = 32; // upper-right first piece y
            int r = 0; // color gradient
            int gee = 0;
            int b = 255;
            int r2 = 255;
            int b2 = 0;
            
            // setting piece characteristics
            for (int j=1; j<33; j++) {
                Piece p = new Piece();
                // type
                if (row==8 || row==1) {
                    switch (column) {
                        case "a":
                        case "h":
                            p.setType("R");
                            break;
                        case "b":
                        case "g":
                            p.setType("N");
                            break;
                        case "c":
                        case "f":
                            p.setType("B");
                            break;
                        case "d":
                            p.setType("Q");
                            break;
                        case "e":
                            p.setType("K");
                            break;
                    }
                } else {
                    p.setType("P");
                }
                // setting position of pieces
                xy = column.concat(String.valueOf(row));
                p.setName(xy);
                
                char nextName = column.charAt(0); // convert column to char
                nextName = (char) (nextName + 1);// increment column
                column = String.valueOf(nextName);// set column to incremented char
                
                p.setTruePos1(x);
                p.setTruePos2(y);
                x = x + 64; // increment board position for piece placement
                
                if (j == 8) {
                    x = 32;
                    y = 96;
                    row = 7;
                    column = "a";
                } else if (j == 16) {
                    x = 32;
                    y = 352;
                    row = 2;
                    column = "a";
                } else if (j == 24) {
                    x = 32;
                    y = 416;
                    row = 1;
                    column = "a";
                }
                
                pieceMap.put(p.getName(), p);
            } // end for-loop
            
            // prints all of the pieces and their relative position on the board...
            // in no specific order, mind you
            for(Map.Entry<String, Piece> entry : pieceMap.entrySet()){
                System.out.println("Key : " + entry.getValue().getType() + entry.getKey() + " Value : " + 
                        entry.getValue().getTruePos1() + " " + entry.getValue().getTruePos2());
            }
            
            // loop for reading in the chess match
            for (String line; (line = br.readLine()) != null;) {
                int x1, y1, x2, y2;
      
                if (line.charAt(0)=='a') {
                    x1 = 32;                        
                } else if (line.charAt(0)=='b'){
                    x1 = 96;
                } else if (line.charAt(0)=='c') {
                    x1 = 160;
                } else if (line.charAt(0)=='d') {
                    x1 = 224;
                } else if (line.charAt(0)=='e') {
                    x1 = 288;
                } else if (line.charAt(0)=='f') {
                    x1 = 352;
                } else if (line.charAt(0)=='g') {
                    x1 = 416;
                } else {
                    x1 = 480;
                }
                
                if (line.charAt(1)=='8') {
                    y1 = 32;                        
                } else if (line.charAt(1)=='7'){
                    y1 = 96;
                } else if (line.charAt(1)=='6') {
                    y1 = 160;
                } else if (line.charAt(1)=='5') {
                    y1 = 224;
                } else if (line.charAt(1)=='4') {
                    y1 = 288;
                } else if (line.charAt(1)=='3') {
                    y1 = 352;
                } else if (line.charAt(1)=='2') {
                    y1 = 416;
                } else {
                    y1 = 480;
                }
                    
                if (line.charAt(2)=='a') {
                    x2 = 32;                        
                } else if (line.charAt(2)=='b'){
                    x2 = 96;
                } else if (line.charAt(2)=='c') {
                    x2 = 160;
                } else if (line.charAt(2)=='d') {
                    x2 = 224;
                } else if (line.charAt(2)=='e') {
                    x2 = 288;
                } else if (line.charAt(2)=='f') {
                    x2 = 352;
                } else if (line.charAt(2)=='g') {
                    x2 = 416;
                } else {
                    x2 = 480;
                }
                
                if (line.charAt(3)=='8') {
                    y2 = 32;                        
                } else if (line.charAt(3)=='7'){
                    y2 = 96;
                } else if (line.charAt(3)=='6') {
                    y2 = 160;
                } else if (line.charAt(3)=='5') {
                    y2 = 224;
                } else if (line.charAt(3)=='4') {
                    y2 = 288;
                } else if (line.charAt(3)=='3') {
                    y2 = 352;
                } else if (line.charAt(3)=='2') {
                    y2 = 416;
                } else {
                    y2 = 480;
                }
                
                // if there was a capture
                try {
                    if (line.charAt(4)=='x') {
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.drawOval(x2-32+shift, y2-16, 38, 38);
                        if (shift == 16) {
                            shift = 0;
                        } else {
                            shift += 2;
                        }                        
                    }
                } catch (Exception e) {
                    
                }
                
                // compensates for castling's mucking up of the turn rotation
                if (turn == 0){
                    g.setColor(new Color(r,gee,b));
                    g2d.drawLine(x1, y1, x2, y2);
                    try {
                        if (!line.endsWith("O")) {
                            turn++;     
                        }
                    } catch (Exception e) {
                        
                    }
                } else {
                    g.setColor(new Color(r2,gee,b2));
                    g2d.drawLine(x1, y1, x2, y2); 
                    try {
                        if (!line.endsWith("O")) {
                            turn--;
                        }
                    } catch (Exception e) {
                        
                    }
                }
                gee += 2; // increment color gradient
            }
        } catch(IOException e){
            System.out.println("Could not load file");
        }               
    } 

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            doDrawing(g);
        } catch (IOException ex) {
            Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/**************************Frame**************************/

public class Militarium extends JFrame {

    public Militarium() throws IOException {

        initUI();        
        
    }

    private void initUI() {

        setTitle("Militarium");

        add(new Surface());

        setSize(527, 551);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    

/**************************Main**************************/
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                Militarium mi = null;
                try {
                    mi = new Militarium();
                } catch (IOException ex) {
                    Logger.getLogger(Militarium.class.getName()).log(Level.SEVERE, null, ex);
                }
                mi.setVisible(true);
            }
        });
    }
}
