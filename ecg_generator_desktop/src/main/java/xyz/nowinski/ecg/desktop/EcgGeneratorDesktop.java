package xyz.nowinski.ecg.desktop;

import xyz.nowinski.ecg.signal.SignalSource;
import xyz.nowinski.ecg.signal.sample.SineSignalSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcgGeneratorDesktop {
    public static void main(String[] args) {
        new EcgGeneratorDesktop().buildAndDisplayGui();
    }

    private void buildAndDisplayGui() {
        JFrame frame = new JFrame("Ecg generator");
        buildContent(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void buildContent(JFrame aFrame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("Signal info"));
        infoPanel.setLayout(new BorderLayout());
        JTextArea infoArea = new JTextArea(5, 20);
        infoArea.setText("Info area");
        infoArea.setEditable(false);
        infoPanel.add(infoArea);
        mainPanel.add(infoPanel);

        JPanel plotPanel = new JPanel();
        plotPanel.setBorder(BorderFactory.createTitledBorder("Signal plot"));
        plotPanel.setLayout(new BorderLayout());

        JPanel plotMock = new JPanel();
        plotMock.setBackground(Color.red);
        plotMock.setMinimumSize(new Dimension(500, 150));
        plotMock.setPreferredSize(plotMock.getMinimumSize());
        plotPanel.add(plotMock);

        mainPanel.add(plotPanel);

        JPanel signalControlPanel = new JPanel();
        signalControlPanel.setBorder(BorderFactory.createTitledBorder("Signal"));
        signalControlPanel.setLayout(new BoxLayout(signalControlPanel, BoxLayout.Y_AXIS));
        JComboBox signalTypeBox = new JComboBox();
        signalTypeBox.setEditable(false);
        ComboBoxModel<SignalSource> model = new DefaultComboBoxModel<>(new SignalSource[]{
                new SineSignalSource(1., 1., 1. )});
        signalTypeBox.setModel(model);
        signalControlPanel.add(signalTypeBox);

        mainPanel.add(signalControlPanel);
        aFrame.getContentPane().add(mainPanel);
    }

    private static final class ShowDialog implements ActionListener {
        /**
         * Defining the dialog's owner JFrame is highly recommended.
         */
        ShowDialog(JFrame aFrame) {
            fFrame = aFrame;
        }

        @Override
        public void actionPerformed(ActionEvent aEvent) {
            JOptionPane.showMessageDialog(fFrame, "This is a dialog");
        }

        private JFrame fFrame;
    }
}
