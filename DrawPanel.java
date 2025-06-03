import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel implements MouseListener {
    private List<Segment> segments = new ArrayList<>();
    private List<Point> intersections = new ArrayList<>();
    private Point start = null;
    private JTextArea logArea;

    public DrawPanel(JTextArea logArea) {
        this.logArea = logArea;
        addMouseListener(this);
        setBackground(Color.WHITE);
    }

    public void calculateIntersections() {
        intersections = new ArrayList<>();
        logArea.setText(""); // Limpa log

        int count = 1;
        for (int i = 0; i < segments.size(); i++) {
            for (int j = i + 1; j < segments.size(); j++) {
                Point p = IntersectionDetector.getIntersection(segments.get(i), segments.get(j));
                if (p != null) {
                    intersections.add(p);
                    logArea.append("Interseção " + count + ":\n");
                    logArea.append("  Segmento A: (" + segments.get(i).p1.x + "," + segments.get(i).p1.y + ") → (" + segments.get(i).p2.x + "," + segments.get(i).p2.y + ")\n");
                    logArea.append("  Segmento B: (" + segments.get(j).p1.x + "," + segments.get(j).p1.y + ") → (" + segments.get(j).p2.x + "," + segments.get(j).p2.y + ")\n");
                    logArea.append("  Ponto: (" + p.x + "," + p.y + ")\n\n");
                    count++;
                }
            }
        }

        if (intersections.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não foram encontradas interseções.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha os segmentos
        for (int i = 0; i < segments.size(); i++) {
            Segment s = segments.get(i);
            if (i == segments.size() - 1) {
                g.setColor(Color.BLUE); // Último segmento desenhado
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawLine(s.p1.x, s.p1.y, s.p2.x, s.p2.y);
        }

        // Desenha os pontos de interseção
        g.setColor(Color.RED);
        for (Point p : intersections) {
            g.fillOval(p.x - 4, p.y - 4, 8, 8);
            g.drawString("(" + p.x + "," + p.y + ")", p.x + 6, p.y - 6);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (start == null) {
            start = new Point(e.getX(), e.getY());
        } else {
            Point end = new Point(e.getX(), e.getY());
            segments.add(new Segment(start, end));
            start = null;
            repaint();
        }
    }

    public void clear() {
        segments.clear();
        intersections.clear();
        start = null;
        logArea.setText("");
        repaint();
    }

    // Métodos MouseListener vazios
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
