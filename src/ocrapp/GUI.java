// Package declaration
package ocrapp;

// Imports declaration
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
// End of imports declaration

/**
 * <h1>Optical Character Recognition Application</h1>
 * A program that can graph and solve standard form linear and quadratic
 * equations from user-inputted images. The user writes their equation, takes a
 * picture, uploads the picture to the computer, and inputs it into the program.
 * The program converts the image into a machine-readable equation, and can then
 * graph and solve the equation. As the user makes corrections to the computer's
 * mistakes over time, the program evolves to make fewer errors.
 * 
 * @author David Chen
 * @author John Fish
 * @author Ryan Mandur
 * @version 1.0
 */
public class GUI extends javax.swing.JFrame {
    
    // Variables declaration
    BufferedImage image;
    Detector d;
    Equation equation;
    Graph graph;
    // End of variables declaration
    
    /**
     * Constructor of GUI.
     */
    public GUI() {
        // Initializes the components of the JFrame
        initComponents();
        
        // Sets some properties of the JFrame
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setEnabled(true);
        
        // ChangeListener that detects tab changes
        boolean enableChangeListener = false; // Set to false for prototype demo
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
                        Graph graph = new Graph(equation, bufferedGraph.getWidth(), bufferedGraph.getHeight());
                        g.drawImage(graph.image, 0, 0, bufferedGraph);
                        //BufferedImage i = resize(graph.image, bufferedGraph.getWidth(), bufferedGraph.getHeight());
                        //g.drawImage(i, 0, 0, bufferedGraph);
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
        trainButton = new javax.swing.JButton();
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
        tabbedPane.setForeground(new java.awt.Color(153, 0, 0));

        mainTab.setBackground(new java.awt.Color(255, 255, 255));

        inputtedImage.setBackground(new java.awt.Color(225, 245, 254));
        inputtedImage.setForeground(new java.awt.Color(225, 245, 254));
        inputtedImage.setOpaque(false);
        inputtedImage.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                inputtedImageAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

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
        equationLabel.setForeground(new java.awt.Color(153, 0, 0));
        equationLabel.setText("Equation:");

        trainButton.setBackground(new java.awt.Color(41, 182, 246));
        trainButton.setForeground(new java.awt.Color(1, 87, 155));
        trainButton.setText("Train");
        trainButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        trainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trainButtonMouseClicked(evt);
            }
        });

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
                        .addGap(18, 18, 18)
                        .addComponent(trainButton)
                        .addContainerGap(302, Short.MAX_VALUE))
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
                .addGroup(mainTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(equationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(equationLabel))
                    .addComponent(trainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Main", mainTab);

        graphTab.setBackground(new java.awt.Color(255, 255, 255));
        graphTab.setForeground(new java.awt.Color(1, 87, 155));

        bufferedGraph.setBackground(new java.awt.Color(225, 245, 254));
        bufferedGraph.setForeground(new java.awt.Color(225, 245, 254));
        bufferedGraph.setOpaque(false);
        bufferedGraph.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                bufferedGraphAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

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
        titleLabel.setForeground(new java.awt.Color(153, 0, 0));
        titleLabel.setText("OCR Application");
        titleLabel.setToolTipText("");

        browseButton.setBackground(new java.awt.Color(41, 182, 246));
        browseButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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
                .addGap(193, 193, 193)
                .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(browseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Resizes a BufferedImage to a new specified width and height.
     * @param img original BufferedImage
     * @param newW new width
     * @param newH new height
     * @return resized BufferedImage
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }   
    
    /**
     * Detects if text in equationTextField is modified.
     * Adds the changes to the detector database.
     * @param evt ActionEvent
     */
    private void equationTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equationTextFieldActionPerformed
        try {
            d.populateDB(equationTextField.getText());
        } catch (Exception e) {
            this.equation = new Equation(equationTextField.getText());
            this.graph = new Graph(this.equation, bufferedGraph.getWidth(), bufferedGraph.getHeight());
        }
    }//GEN-LAST:event_equationTextFieldActionPerformed
    
    /**
     * Draws BufferedImage inputtedImage.
     */
    private void drawMainImage(){
        Graphics g = inputtedImage.getGraphics();
        g.drawImage(this.image, 0, 0, this);
    }
    
    /**
     * Draws BufferedImage bufferedGraph.
     */
    private void drawGraph(){
        Graphics g = bufferedGraph.getGraphics();
        g.drawImage(graph.image, 0, 0, this);
    }
    
    /**
     * Detects if browseButton is clicked.
     * Attempts to open a JFileChooser, then takes the chosen image,
     * resizes it, and then displays it on the JPanel inputtedImage.
     * @param evt MouseEvent
     */
    private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButtonMouseClicked
        int returnVal = imageBrowser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage fullImage = ImageIO.read(imageBrowser.getSelectedFile());
                this.d = new Detector(fullImage);
                String guess = d.content;
                equationTextField.setText(guess);
                this.equation = new Equation(guess);
                this.graph = new Graph(this.equation, bufferedGraph.getWidth(), bufferedGraph.getHeight());
                this.image = resize(d.image, inputtedImage.getWidth(), inputtedImage.getHeight());
                this.drawMainImage();
            } catch (IOException e){
            }
        }
    }//GEN-LAST:event_browseButtonMouseClicked
    
    /**
     * Redraws inputtedImage.
     * @param evt AncestorEvent
     */    
    private void inputtedImageAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_inputtedImageAncestorAdded
        if (this.d != null) {
            this.drawMainImage();
        }
    }//GEN-LAST:event_inputtedImageAncestorAdded
    
    /**
     * Redraws bufferedGraph.
     * @param evt AncestorEvent
     */
    private void bufferedGraphAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_bufferedGraphAncestorAdded
        if (this.graph != null) {
            this.drawGraph();
        }
    }//GEN-LAST:event_bufferedGraphAncestorAdded
    
    /**
     * Populates the database when JButton trainButton is clicked.
     * @param evt MouseEvent
     */
    private void trainButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trainButtonMouseClicked
        try {
            d.populateDB(equationTextField.getText());
        } catch (Exception e) {
            this.equation = new Equation(equationTextField.getText());
            this.graph = new Graph(this.equation, bufferedGraph.getWidth(), bufferedGraph.getHeight());
        }
    }//GEN-LAST:event_trainButtonMouseClicked

    /**
     * Main method.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
        }     
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
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
    private javax.swing.JButton trainButton;
    // End of variables declaration//GEN-END:variables
}
