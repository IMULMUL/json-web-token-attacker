/**
 * JOSEPH - JavaScript Object Signing and Encryption Pentesting Helper
 * Copyright (C) 2016 Dennis Detering
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package eu.dety.burp.joseph.attacks.bleichenbacher_pkcs1.gui;

import eu.dety.burp.joseph.attacks.bleichenbacher_pkcs1.BleichenbacherPkcs1;
import eu.dety.burp.joseph.utilities.*;
import org.apache.commons.codec.binary.Base64;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class BleichenbacherPkcs1DecryptionAttackPanel extends javax.swing.JPanel {
    private static final Logger loggerInstance = Logger.getInstance();
    private BleichenbacherPkcs1 reference;
    private Timer attackTimer;
    private long startTime;
    private byte[] result;
    private int amountRequests;

    /**
     * Creates new form BleichenbacherPkcs1DecryptionAttackPanel
     */
    public BleichenbacherPkcs1DecryptionAttackPanel(BleichenbacherPkcs1 reference) {
        this.reference = reference;

        initComponents();

        setVisibilityStatusComponents(false);
        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        cekFormatHex.setVisible(false);
        cekFormatB64.setVisible(false);
        resultKeyLabel.setVisible(false);
        resultKeyLabel.setVisible(false);
        resultKeyValue.setVisible(false);
        resultContentLabel.setVisible(false);
        resultContentValue.setVisible(false);

        attackTimer = new Timer(1000, taskPerformer);
    }

    /**
     * Set the visibility of several status components
     * 
     * @param status
     *            Boolean value used for setVisible()
     */
    private void setVisibilityStatusComponents(boolean status) {
        timeElapsedLabel.setVisible(status);
        amountRequestsLabel.setVisible(status);
        currentSLabel.setVisible(status);
        timeElapsedValue.setVisible(status);
        amountRequestsValue.setVisible(status);
        currentSValue.setVisible(status);
        jScrollPane1.setVisible(status);
    }

    /**
     * Set the visibility of several result components
     * 
     * @param status
     *            Boolean value used for setVisible()
     */
    private void setVisibilityResultComponents(boolean status) {
        jScrollPane2.setVisible(status);
        jScrollPane3.setVisible(status);
        cekFormatHex.setVisible(status);
        cekFormatB64.setVisible(status);
        resultKeyLabel.setVisible(status);
        resultKeyValue.setVisible(status);
        resultContentLabel.setVisible(status);
        resultContentValue.setVisible(status);
    }

    /**
     * Set the currently found s value on text widget
     * 
     * @param s
     *            BigInteger s value
     */
    public void setCurrentSValue(BigInteger s) {
        this.currentSValue.setText(s.toString());
    }

    /**
     * Set amount of performed requests
     * 
     * @param value
     *            Amount of requests
     */
    public void setAmountRequestsValue(int value) {
        amountRequests = value;
    }

    /**
     * Actions to perform if attack has been finished
     * 
     * @param result
     *            Byte array of the calculated key
     */
    public void attackDoneAction(byte[] result, JoseParameter joseParameter) {
        attackTimer.stop();
        startAttackButton.setEnabled(true);
        cancelAttackButton.setEnabled(false);

        if (result.length > 0) {
            this.result = result;

            setVisibilityResultComponents(true);

            resultKeyValue.setText(Decoder.bytesToHex(result));

            String[] components = Decoder.getComponents(joseParameter.getJoseValue());

            try {
                byte[] content = Crypto.decryptAES(components[0], result, Base64.decodeBase64(components[2]), Base64.decodeBase64(components[3]),
                        Base64.decodeBase64(components[4]));
                resultContentValue.setText(new String(content, StandardCharsets.UTF_8));
            } catch (DecryptionFailedException e) {
                loggerInstance.log(BleichenbacherPkcs1.class, "Failed to decrypt the content: " + e.getMessage(), Logger.LogLevel.ERROR);
                resultContentValue.setText("[ERROR] Could not decrypt content. See error logs for further information.");
                resultContentValue.setEnabled(false);
            }

        }
    }

    /**
     * Task Performer
     * <p>
     * Starts the time elapsed timer and prints the elapsed time and amount of requests to the text widget
     */
    private ActionListener taskPerformer = new ActionListener() {
        long difference;

        public void actionPerformed(ActionEvent evt) {
            difference = System.nanoTime() - startTime;
            String timeElapsed = String.format("%02d:%02d:%02d", TimeUnit.NANOSECONDS.toHours(difference), TimeUnit.NANOSECONDS.toMinutes(difference)
                    - (TimeUnit.NANOSECONDS.toHours(difference) * 60), TimeUnit.NANOSECONDS.toSeconds(difference)
                    - (TimeUnit.NANOSECONDS.toMinutes(difference) * 60));

            timeElapsedValue.setText(timeElapsed);
            amountRequestsValue.setText(Integer.toString(amountRequests));
        }
    };

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cekFormatButtonGroup = new javax.swing.ButtonGroup();
        startAttackButton = new javax.swing.JButton();
        cancelAttackButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        timeElapsedLabel = new javax.swing.JLabel();
        amountRequestsLabel = new javax.swing.JLabel();
        currentSLabel = new javax.swing.JLabel();
        resultKeyLabel = new javax.swing.JLabel();
        cekFormatHex = new javax.swing.JRadioButton();
        cekFormatB64 = new javax.swing.JRadioButton();
        attackDescription = new javax.swing.JLabel();
        attackDescription.putClientProperty("html.disable", null);
        timeElapsedValue = new javax.swing.JLabel();
        amountRequestsValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        currentSValue = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultContentValue = new javax.swing.JTextArea();
        resultContentLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultKeyValue = new javax.swing.JTextArea();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("JOSEPH"); // NOI18N
        startAttackButton.setText(bundle.getString("STARTATTACKBUTTON")); // NOI18N
        startAttackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAttackButtonActionPerformed(evt);
            }
        });

        cancelAttackButton.setText(bundle.getString("CANCELATTACKBUTTON")); // NOI18N
        cancelAttackButton.setEnabled(false);
        cancelAttackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAttackButtonActionPerformed(evt);
            }
        });

        timeElapsedLabel.setText(bundle.getString("TIME_ELAPSED")); // NOI18N

        amountRequestsLabel.setText(bundle.getString("AMOUNT_REQUESTS")); // NOI18N

        currentSLabel.setText(bundle.getString("FOUND_S")); // NOI18N

        resultKeyLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        resultKeyLabel.setText(bundle.getString("RESULT_CEK")); // NOI18N

        cekFormatButtonGroup.add(cekFormatHex);
        cekFormatHex.setSelected(true);
        cekFormatHex.setText(bundle.getString("HEX")); // NOI18N
        cekFormatHex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekFormatHexActionPerformed(evt);
            }
        });

        cekFormatButtonGroup.add(cekFormatB64);
        cekFormatB64.setText(bundle.getString("BASE64URL")); // NOI18N
        cekFormatB64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekFormatB64ActionPerformed(evt);
            }
        });

        attackDescription
                .setText("<html><em>Note: This attack will take several minutes and performs thousands of requests to the server!</em><br />This attack is only successful, if the valid oracle responses are correctly marked.</html>");

        timeElapsedValue.setText("00:00:00");
        timeElapsedValue.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        amountRequestsValue.setText("0");
        amountRequestsValue.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jScrollPane1.setBackground(new java.awt.Color(251, 251, 251));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        currentSValue.setEditable(false);
        currentSValue.setBackground(new java.awt.Color(251, 251, 251));
        currentSValue.setColumns(20);
        currentSValue.setForeground(new java.awt.Color(0, 0, 0));
        currentSValue.setLineWrap(true);
        currentSValue.setRows(4);
        currentSValue.setTabSize(4);
        currentSValue.setText("0");
        currentSValue.setWrapStyleWord(true);
        currentSValue.setBorder(null);
        jScrollPane1.setViewportView(currentSValue);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        resultContentValue.setEditable(false);
        resultContentValue.setColumns(20);
        resultContentValue.setLineWrap(true);
        resultContentValue.setRows(5);
        resultContentValue.setWrapStyleWord(true);
        resultContentValue.setBorder(null);
        jScrollPane2.setViewportView(resultContentValue);

        resultContentLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        resultContentLabel.setText(bundle.getString("RESULT_CONTENT")); // NOI18N

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        resultKeyValue.setEditable(false);
        resultKeyValue.setColumns(20);
        resultKeyValue.setLineWrap(true);
        resultKeyValue.setRows(5);
        resultKeyValue.setWrapStyleWord(true);
        resultKeyValue.setBorder(null);
        jScrollPane3.setViewportView(resultKeyValue);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGroup(
                                                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(attackDescription, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
                                                                                .addComponent(jSeparator1)
                                                                                .addGroup(
                                                                                        layout.createSequentialGroup()
                                                                                                .addGroup(
                                                                                                        layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(
                                                                                                                        layout.createSequentialGroup()
                                                                                                                                .addComponent(startAttackButton)
                                                                                                                                .addPreferredGap(
                                                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(
                                                                                                                                        cancelAttackButton))
                                                                                                                .addGroup(
                                                                                                                        layout.createSequentialGroup()
                                                                                                                                .addComponent(cekFormatHex)
                                                                                                                                .addPreferredGap(
                                                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(cekFormatB64))
                                                                                                                .addComponent(resultKeyLabel)
                                                                                                                .addGroup(
                                                                                                                        layout.createSequentialGroup()
                                                                                                                                .addGroup(
                                                                                                                                        layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                                .addComponent(
                                                                                                                                                        currentSLabel,
                                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        Short.MAX_VALUE)
                                                                                                                                                .addComponent(
                                                                                                                                                        amountRequestsLabel,
                                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        Short.MAX_VALUE)
                                                                                                                                                .addComponent(
                                                                                                                                                        timeElapsedLabel,
                                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        Short.MAX_VALUE))
                                                                                                                                .addPreferredGap(
                                                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addGroup(
                                                                                                                                        layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                                .addComponent(
                                                                                                                                                        timeElapsedValue,
                                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        576,
                                                                                                                                                        Short.MAX_VALUE)
                                                                                                                                                .addComponent(
                                                                                                                                                        amountRequestsValue,
                                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                        Short.MAX_VALUE)
                                                                                                                                                .addComponent(
                                                                                                                                                        jScrollPane1,
                                                                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)))
                                                                                                                .addComponent(resultContentLabel))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))).addContainerGap())
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(
                                                                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(attackDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(startAttackButton)
                                        .addComponent(cancelAttackButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(timeElapsedLabel)
                                        .addComponent(timeElapsedValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(amountRequestsLabel)
                                        .addComponent(amountRequestsValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(currentSLabel)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(resultKeyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cekFormatHex).addComponent(cekFormatB64))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(resultContentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(68, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void startAttackButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startAttackButtonActionPerformed
        startAttackButton.setEnabled(false);
        cancelAttackButton.setEnabled(true);

        // Reset previously enabled components
        setVisibilityStatusComponents(true);
        setVisibilityResultComponents(false);
        currentSValue.setText("0");
        resultKeyValue.setText("");
        resultContentValue.setText("");

        this.startTime = System.nanoTime();
        attackTimer.start();

        reference.performDecryptionAttack();

    }// GEN-LAST:event_startAttackButtonActionPerformed

    private void cancelAttackButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelAttackButtonActionPerformed
        startAttackButton.setEnabled(true);
        cancelAttackButton.setEnabled(false);
        // setVisibilityStatusComponents(false);

        attackTimer.stop();
        this.startTime = 0;

        reference.cancelDecryptionAttack();
    }// GEN-LAST:event_cancelAttackButtonActionPerformed

    private void cekFormatHexActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cekFormatHexActionPerformed
        if (cekFormatHex.isSelected()) {
            resultKeyValue.setText(Decoder.bytesToHex(this.result));
        }
    }// GEN-LAST:event_cekFormatHexActionPerformed

    private void cekFormatB64ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cekFormatB64ActionPerformed
        if (cekFormatB64.isSelected()) {
            resultKeyValue.setText(Decoder.base64UrlEncode(this.result));
        }
    }// GEN-LAST:event_cekFormatB64ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amountRequestsLabel;
    private javax.swing.JLabel amountRequestsValue;
    private javax.swing.JLabel attackDescription;
    private javax.swing.JButton cancelAttackButton;
    private javax.swing.JRadioButton cekFormatB64;
    private javax.swing.ButtonGroup cekFormatButtonGroup;
    private javax.swing.JRadioButton cekFormatHex;
    private javax.swing.JLabel currentSLabel;
    private javax.swing.JTextArea currentSValue;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel resultContentLabel;
    private javax.swing.JTextArea resultContentValue;
    private javax.swing.JLabel resultKeyLabel;
    private javax.swing.JTextArea resultKeyValue;
    private javax.swing.JButton startAttackButton;
    private javax.swing.JLabel timeElapsedLabel;
    private javax.swing.JLabel timeElapsedValue;
    // End of variables declaration//GEN-END:variables
}
