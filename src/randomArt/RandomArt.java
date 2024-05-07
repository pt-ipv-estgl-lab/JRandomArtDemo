package randomArt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A RandomArtPanel draws random pictures which might be taken to have
 * some vague resemblance to abstract art. A new picture is produced every
 * four seconds. There are three types of pictures: random lines,
 * random circles, and random 3D rects. The figures are drawn in
 * random colors on a background that is a random shade of gray.
 * A main() routine allows this class to be run as a program
 */

public class RandomArt extends JPanel {

    /**
     * The constructor creates a timer with a delay time of four seconds
     * (2000 milliseconds), and with a lambda expression of type
     * ActionListener that responds to an event by repainting this
     * panel. It also starts the timer running.
     */
    public RandomArt() {
        Timer timer = new Timer(2000, e -> repaint());
        timer.start();
    }

    /**
     * main() routine makes it possible to run this class as a program.
     * It just opens a window that contains a RandomArt panel.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Random Art ??");
        RandomArt content = new RandomArt();
        window.setContentPane(content);
        window.setSize(400, 400);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * The paintComponent() method fills the panel with a random shade of
     * gray and then draws one of three types of random "art". The type
     * of art to be drawn is chosen at random.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; // for access to advanced features.
        g2.setStroke(new BasicStroke(2)); // draw lines that are two pixels wide.
        g2.setRenderingHint( // turn on antialiasting for smoother lines.
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Note: Since the next three lines fill the entire panel with
        // gray, there is no need to call super.paintComponent(g), since
        // any drawing that it does will only be covered up anyway.

        Color randomGray = Color.getHSBColor(1.0F, 0.0F, (float) Math.random());
        g.setColor(randomGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        Random ranInt = new Random();
        var artType = ranInt.nextInt(3);
        switch (artType) {
            case 0 -> {
                for (int i = 0; i < 500; i++) {
                    var x1 = ranInt.nextInt(getWidth());
                    var y1 = ranInt.nextInt(getHeight());
                    var x2 = ranInt.nextInt(getWidth());
                    var y2 = ranInt.nextInt(getHeight());
                    Color randomHue = Color.getHSBColor((float) Math.random(),
                            1.0F, 1.0F);
                    g.setColor(randomHue);
                    g.drawLine(x1, y1, x2, y2);
                }
            }
            case 1 -> {
                for (int i = 0; i < 200; i++) {
                    int centerX = ranInt.nextInt(getWidth());
                    int centerY = ranInt.nextInt(getHeight());
                    Color randomHue = Color.getHSBColor((float) Math.random(), 1.0F, 1.0F);
                    g.setColor(randomHue);
                    g.drawOval(centerX - 50, centerY - 50, 100, 100);
                }
            }
            default -> {
                for (int i = 0; i < 25; i++) {
                    int centerX = ranInt.nextInt(getWidth());
                    int centerY = ranInt.nextInt(getHeight());
                    int size = 30 + ranInt.nextInt(170);
                    Color randomColor = new Color(ranInt.nextInt(256),
                                    ranInt.nextInt(256), ranInt.nextInt(256));
                    g.setColor(randomColor);
                    g.fill3DRect(centerX - size / 2, centerY - size / 2, 
                                    size, size, true);
                }
            }
        }

    }
}
