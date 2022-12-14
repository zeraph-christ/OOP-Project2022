package display;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import Element.EleButton;
import Element.EleLabel;

public class FrGameOver extends JPanel {
    private static final long serialVersionUID = 1L;
    private long point;
    public FrGameOver(long point, ActionListener main) {
        try {
            this.point = point;
            this.setBackground(new Color(241, 98, 69));
            this.setBounds(0, 0, 1000, 600);
            this.setFocusable(true);
            this.setLayout(null);
            EleLabel status = new EleLabel("You Died!", 40, 0, 150, 1000, 100);
            status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            status.setForeground(Color.white);
            EleLabel showPoint = new EleLabel("Total : " + this.point, 30, 0, 250, 1000, 100);
            showPoint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            showPoint.setForeground(Color.white);
            EleButton restart = new EleButton("Restart", 15, 500, 330, 200, 50);
            restart.addActionListener(main);
            EleButton menu = new EleButton("Home", 15, 250, 330, 200, 50);
            menu.addActionListener(main);
            this.add(showPoint);
            this.add(status);
            this.add(restart);
            this.add(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
