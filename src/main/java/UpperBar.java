import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class UpperBar extends JPanel {
    private Frame app;
    private int BAR_HEIGHT = 30;

    public UpperBar(Frame frame){
        this.app = frame;
            FrameMover framePull = new FrameMover(frame);
            frame.addMouseListener(framePull);
            frame.addMouseMotionListener(framePull);

            setLayout(null);
            setPreferredSize(new Dimension(app.getWidth(), BAR_HEIGHT));
            setBackground(new Color(30,30,30));
            JLabel Title = new JLabel("League of Legends - Inventory");
            Title.setBounds((app.getWidth()/2)-100 , 0, 200, BAR_HEIGHT );
            Title.setForeground(new Color(255,255,255));
            add(Title);

            JButton Exit = new JButton("X"){
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(new Color(200,90,90));
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.setPaint(new Color(0,0,0));
                    RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setRenderingHints(rh);
                    Map<TextAttribute, Object> attributes = new HashMap<>();

                    attributes.put(TextAttribute.FAMILY, Font.DIALOG);
                    attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD);
                    attributes.put(TextAttribute.SIZE, 13);

                    g2.setFont(Font.getFont(attributes));
                    g2.drawString(getText(), 16, 17);
                    g2.dispose();
                }
            };
            Exit.addActionListener(e -> {
                System.exit(0);
            });
            Exit.setFocusPainted(false);
            Exit.setFocusable(false);
            Exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Exit.setBounds(app.getWidth()-40, 0, 40, BAR_HEIGHT );
            Exit.setOpaque(true);
            Exit.setBorderPainted(false);

            add(Exit);

    }
}

class FrameMover extends MouseAdapter {
    private final Frame frame;
    private Point mousecoords = null;

    public FrameMover(Frame frame) {
        this.frame = frame;
    }

    public void mouseReleased(MouseEvent e) {
        mousecoords = null;
    }

    public void mousePressed(MouseEvent e) {
        mousecoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        Point cCoords = e.getLocationOnScreen();
        frame.setLocation(cCoords.x - mousecoords.x, cCoords.y - mousecoords.y);
    }
}
