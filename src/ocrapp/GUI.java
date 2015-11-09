/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocrapp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author chend7019
 */
public class GUI extends javax.swing.JFrame {
    
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setEnabled(true);
        
        boolean enableChangeListener = true; // Set to false for prototype demo
        if (enableChangeListener) {
            tabbedPane.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    //System.out.println(tabbedPane.getSelectedIndex());
                    if (tabbedPane.getSelectedIndex() == 0) {
                        System.out.println("Main Tab");
                    }
                    if (tabbedPane.getSelectedIndex() == 1) {
                        System.out.println("Graph Tab");
                        Graphics g = bufferedGraph.getGraphics();
                        Graph graph = new Graph("mx+b");
                        graph.construct();
                        BufferedImage i = resize(graph.image, bufferedGraph.getWidth(), bufferedGraph.getHeight());
                        //graph.paint(g);
                        g.drawImage(i, 0, 0, bufferedGraph);
                    }
                }
            });        
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageBrowser = new javax.swing.JFileChooser();
        tabbedPane = new javax.swing.JTabbedPane();
        mainTab = new javax.swing.JPanel();
        inputtedImage = new javax.swing.JPanel();
        equationTextField = new javax.swing.JTextField();
        equationLabel = new javax.swing.JLabel();
        graphTab = new javax.swing.JPanel();
        bufferedGraph = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OCR Application");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        setName("OCRApp"); // NOI18N
        setResizable(false);

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setForeground(new java.awt.Color(3, 155, 229));

        mainTab.setBackground(new java.awt.Color(255, 255, 255));

        inputtedImage.setBackground(new java.awt.Color(225, 245, 254));
        inputtedImage.setForeground(new java.awt.Color(225, 245, 254));

        javax.swing.GroupLayout inputtedImageLayout = new javax.swing.GroupLayout(inputtedImage);
        inputtedImage.setLayout(inputtedImageLayout);
        inputtedImageLayout.setHorizontalGroup(
            inputtedImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        inputtedImageLayout.setVerticalGroup(
            inputtedImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );

        equationTextField.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        equationTextField.setForeground(new java.awt.Color(3, 155, 229));
        equationTextField.setToolTipText("");
        equationTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equationTextFieldActionPerformed(evt);
            }
        });

        equationLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        equationLabel.setForeground(new java.awt.Color(3, 155, 229));
        equationLabel.setText("Equation:");

        javax.swing.GroupLayout mainTabLayout = new javax.swing.GroupLayout(mainTab);
        mainTab.setLayout(mainTabLayout);
        mainTabLayout.setHorizontalGroup(
            mainTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainTabLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(equationLabel)
                        .addGap(4, 4, 4)
                        .addComponent(equationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(381, Short.MAX_VALUE))
                    .addGroup(mainTabLayout.createSequentialGroup()
                        .addComponent(inputtedImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        mainTabLayout.setVerticalGroup(
            mainTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputtedImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equationLabel))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Main", mainTab);

        graphTab.setBackground(new java.awt.Color(255, 255, 255));
        graphTab.setForeground(new java.awt.Color(1, 87, 155));

        bufferedGraph.setBackground(new java.awt.Color(225, 245, 254));
        bufferedGraph.setForeground(new java.awt.Color(225, 245, 254));

        javax.swing.GroupLayout bufferedGraphLayout = new javax.swing.GroupLayout(bufferedGraph);
        bufferedGraph.setLayout(bufferedGraphLayout);
        bufferedGraphLayout.setHorizontalGroup(
            bufferedGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );
        bufferedGraphLayout.setVerticalGroup(
            bufferedGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout graphTabLayout = new javax.swing.GroupLayout(graphTab);
        graphTab.setLayout(graphTabLayout);
        graphTabLayout.setHorizontalGroup(
            graphTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(graphTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bufferedGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        graphTabLayout.setVerticalGroup(
            graphTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(graphTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bufferedGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab("Graph", graphTab);

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(3, 155, 229));
        titleLabel.setText("OCR Application");
        titleLabel.setToolTipText("");

        browseButton.setBackground(new java.awt.Color(41, 182, 246));
        browseButton.setForeground(new java.awt.Color(1, 87, 155));
        browseButton.setText("Browse");
        browseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(208, 208, 208)
                .addComponent(browseButton)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }   
   
    private void equationTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equationTextFieldActionPerformed
        System.out.println("Equation changed to: " + evt.getActionCommand());
        // Enter database modifying code here
    }//GEN-LAST:event_equationTextFieldActionPerformed

    private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMouseClicked
        int returnVal = imageBrowser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage img = ImageIO.read(imageBrowser.getSelectedFile());
                Detector d = new Detector(img);
                equationTextField.setText(d.content);
                Graphics g = inputtedImage.getGraphics();
                BufferedImage i = resize(d.image, inputtedImage.getWidth(), inputtedImage.getHeight());
                g.drawImage(i, 0, 0, this);
            } catch (IOException e){
            }
        }
    }//GEN-LAST:event_browseButtonMouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JPanel bufferedGraph;
    private javax.swing.JLabel equationLabel;
    private javax.swing.JTextField equationTextField;
    private javax.swing.JPanel graphTab;
    private javax.swing.JFileChooser imageBrowser;
    private javax.swing.JPanel inputtedImage;
    private javax.swing.JPanel mainTab;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
