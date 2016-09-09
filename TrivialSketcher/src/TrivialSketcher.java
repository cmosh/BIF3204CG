/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This is an extremely scaled-down sketching canvas; with it you
 * can only scribble thin black lines.  For simplicity the window
 * contents are never refreshed when they are uncovered.
 */
public class TrivialSketcher extends JPanel {

    /**
     * Keeps track of the last point to draw the next line from.
     */
    private ArrayList<ArrayList<Point>> Strokes = new ArrayList<>();

    /**
     * Constructs a panel, registering listeners for the mouse.
     */
    public TrivialSketcher() {
        // When the mouse button goes down, set the current point
        // to the location at which the mouse was pressed.
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                ArrayList<Point> lastPoint = new ArrayList();

                lastPoint.add(new Point(e.getX(), e.getY()));
                Strokes.add(lastPoint);
            }


        });

        // When the mouse is dragged, draw a line from the old point
        // to the new point and update the value of lastPoint to hold
        // the new current point.
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                int last = Strokes.size();
                int lastp = Strokes.get(last-1).size();
                Strokes.get(last-1).add(new Point(e.getX(), e.getY()));
                g.drawLine( Strokes.get(last-1).get(lastp - 1).x,  Strokes.get(last-1).get(lastp -1).y,
                        e.getX(), e.getY());
                g.dispose();
            }
        });
    }
    @Override
    public void paint(Graphics g) {

        for (int y = 0; y < Strokes.size(); y++) {

            int i = 0;
            for (int j = 1; j < Strokes.get(y).size(); j++) {

                g.drawLine(Strokes.get(y).get(i).x, Strokes.get(y).get(i).y,
                        Strokes.get(y).get(j).x, Strokes.get(y).get(j).y);

                i++;
            }
        }
    }
    /**
     * A tester method that embeds the panel in a frame so you can
     * run it as an application.
     */

}