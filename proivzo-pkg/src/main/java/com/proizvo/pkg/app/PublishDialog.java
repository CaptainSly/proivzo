/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proizvo.pkg.app;

import com.proizvo.pkg.cfg.Configuration;
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.File;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author john
 */
public class PublishDialog extends JDialog {

    public static void showDialog(Frame parent, boolean modal, final boolean exitSystem,
            File workDir, File tempDir, File toolDir) {
        final PublishDialog dialog = new PublishDialog(parent, modal);
        dialog.workDir = workDir;
        dialog.tempDir = tempDir;
        dialog.toolDir = toolDir;
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (exitSystem) {
                    System.exit(0);
                }
            }
        });
        dialog.setVisible(true);
    }

    private File workDir;
    private File tempDir;
    private File toolDir;

    /**
     * Creates new form PublishDialog
     */
    public PublishDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOsGroup = new javax.swing.ButtonGroup();
        dirChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        rbWin = new javax.swing.JRadioButton();
        rbMac = new javax.swing.JRadioButton();
        rbIos = new javax.swing.JRadioButton();
        tfPubStorageLoc = new javax.swing.JTextField();
        btnChoosePubStorage = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnPublish = new javax.swing.JButton();

        dirChooser.setApproveButtonText("Choose");
        dirChooser.setDialogTitle("Select storage directory");
        dirChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Publish your game");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnOsGroup.add(rbWin);
        rbWin.setText("Windows");

        btnOsGroup.add(rbMac);
        rbMac.setText("Mac OS X");

        btnOsGroup.add(rbIos);
        rbIos.setSelected(true);
        rbIos.setText("Android/iOS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbWin)
                .addGap(37, 37, 37)
                .addComponent(rbMac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(rbIos)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbWin)
                    .addComponent(rbMac)
                    .addComponent(rbIos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tfPubStorageLoc.setText("SOMEDIR");

        btnChoosePubStorage.setText("Choose...");
        btnChoosePubStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoosePubStorageActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Output folder:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfPubStorageLoc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChoosePubStorage)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPubStorageLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoosePubStorage))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPublish.setText("Publish");
        btnPublish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPublish)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPublish)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChoosePubStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoosePubStorageActionPerformed
        if (dirChooser.showOpenDialog(this) != JOptionPane.OK_OPTION) {
            return;
        }
        File pubFolder = dirChooser.getSelectedFile();
        tfPubStorageLoc.setText(pubFolder.getAbsolutePath());
    }//GEN-LAST:event_btnChoosePubStorageActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Load configuration at first startup
        Configuration sys = Configuration.getInstance();
        Properties cfg = sys.loadPackagerCfg();
        // Load variables with their defaults
        String title = cfg.getProperty("title", "Proizvo Packager");
        File rootW = (new File(cfg.getProperty("workRoot", ""))).getAbsoluteFile();
        File rootD = (new File(cfg.getProperty("tempDir",
                sys.myHome + File.separator + "temp"))).getAbsoluteFile();
        File rootT = (new File(cfg.getProperty("toolDir",
                sys.myHome + File.separator + "thirdparty"))).getAbsoluteFile();
        // Patch root when invoked from editor
        workDir = workDir == null ? rootW : workDir;
        tempDir = tempDir == null ? rootD : tempDir;
        toolDir = toolDir == null ? rootT : toolDir;
        // Set UI elements
        this.setTitle(title);
        tfPubStorageLoc.setText(workDir + "");
        System.out.printf("Temp dir => %s %n", tempDir);
        System.out.printf("Tool dir => %s %n", toolDir);
    }//GEN-LAST:event_formWindowOpened

    private void btnPublishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublishActionPerformed
        try {
            File yourWork = new File(tfPubStorageLoc.getText());
            String[] args = new String[]{"-w", yourWork + ""};
            com.xafero.jaddle.cmd.Program.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btnPublishActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /* Set native OS X look and feel */
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Packager");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        /* Create and display the dialog */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame parent = new javax.swing.JFrame();
                boolean modal = false;
                final boolean exitSystem = true;
                showDialog(parent, modal, exitSystem, null, null, null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoosePubStorage;
    private javax.swing.ButtonGroup btnOsGroup;
    private javax.swing.JButton btnPublish;
    private javax.swing.JFileChooser dirChooser;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton rbIos;
    private javax.swing.JRadioButton rbMac;
    private javax.swing.JRadioButton rbWin;
    private javax.swing.JTextField tfPubStorageLoc;
    // End of variables declaration//GEN-END:variables
}
