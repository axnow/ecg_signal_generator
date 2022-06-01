package xyz.nowinski.ecg.desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcgGeneratorDesktop {
    public static void main(String[] args) {
        new EcgGeneratorDesktop().buildAndDisplayGui();
    }

        private void buildAndDisplayGui(){
            JFrame frame = new JFrame("Ecg generator");
            buildContent(frame);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        private void buildContent(JFrame aFrame){
            JPanel panel = new JPanel();
            panel.add(new JLabel("Hello"));
            JButton ok = new JButton("OK");
            ok.addActionListener(new ShowDialog(aFrame));
            panel.add(ok);
            aFrame.getContentPane().add(panel);
        }

        private static final class ShowDialog implements ActionListener {
            /** Defining the dialog's owner JFrame is highly recommended. */
            ShowDialog(JFrame aFrame){
                fFrame = aFrame;
            }
            @Override public void actionPerformed(ActionEvent aEvent) {
                JOptionPane.showMessageDialog(fFrame, "This is a dialog");
            }
            private JFrame fFrame;
        }

}
