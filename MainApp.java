import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Interseção de Segmentos");

        JTextArea logArea = new JTextArea(20, 30);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        DrawPanel panel = new DrawPanel(logArea);

        JButton calcularBtn = new JButton("Calcular Interseções");
        calcularBtn.addActionListener(e -> panel.calculateIntersections());

        JButton limparBtn = new JButton("Limpar");
        limparBtn.addActionListener(e -> panel.clear());

        JPanel buttons = new JPanel();
        buttons.add(calcularBtn);
        buttons.add(limparBtn);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Detalhes dos Cálculos:"), BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
