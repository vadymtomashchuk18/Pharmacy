package com.pharmacy.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.pharmacy.entities.Delivery;
import com.pharmacy.entities.DeliveryRecord;

public class DeliveryDetailedInfoWindow extends JFrame {

    private JPanel contentPane;
    private JTextField txtPublicationName;
    private JTextField txtPublicationIdentity;
    private JTextField txtReaderName;
    private JTextField txtFrom;
    private JTextField txtTo;
    
    /**
     * Create the frame.
     */
    public DeliveryDetailedInfoWindow(Delivery object) {
        setResizable(false);
        setTitle("\u0406\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0456\u044F \u043F\u0440\u043E \u0432\u0438\u0434\u0430\u0447\u0443");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 299, 221);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblPublication = new JLabel("Date");
        
        txtPublicationName = new JTextField();
        txtPublicationName.setEditable(false);
        txtPublicationName.setColumns(10);
        txtPublicationName.setText(object.getDate().toLocaleString());
        
        JLabel lblIdentity = new JLabel("Pharmacy id");
        
        txtPublicationIdentity = new JTextField();
        txtPublicationIdentity.setEditable(false);
        txtPublicationIdentity.setColumns(10);
        txtPublicationIdentity.setText(object.getPharmacy_id() + "");
        
        JLabel lblReader = new JLabel("Delivery's Id");
        
        txtReaderName = new JTextField();
        txtReaderName.setEditable(false);
        txtReaderName.setColumns(10);
        txtReaderName.setText(object.getId() +"");
        
        
        JButton btnReturn = new JButton("Delivery records");
        btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TableDataViewWindow<>(DeliveryRecord.class, Arrays.asList(new DeliveryRecord[]{
						new DeliveryRecord(1, 1, 2),
						new DeliveryRecord(1, 2, 20),
						new DeliveryRecord(1, 3, 30),
				}), null).setVisible(true);
			}
		});
        btnReturn.setEnabled(true);
        
        
        
        
        JButton btnClose = new JButton("close");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryDetailedInfoWindow.this.setVisible(false);
                DeliveryDetailedInfoWindow.this.dispose();
            }
        });
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblReader)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(txtReaderName)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                    .addComponent(lblPublication)
                                    .addComponent(txtPublicationName, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                    .addComponent(lblIdentity)
                                    .addComponent(txtPublicationIdentity, 0, 0, Short.MAX_VALUE)))
                            .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(btnReturn)
                                .addGap(36)
                                .addComponent(btnClose)))
                        
        )));
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPublication)
                        .addComponent(lblIdentity))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(txtPublicationName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPublicationIdentity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblReader)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(txtReaderName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnReturn)
                        .addComponent(btnClose))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
