package com.pharmacy.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.pharmacy.data.PatientData;
import com.pharmacy.data.PrescrData;
import com.pharmacy.data.PurchaseData;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Prescription;
import com.pharmacy.entities.Purchase;
import com.pharmacy.view.elements.CustomTableModel;
import com.pharmacy.view.elements.DateLabelFormatter;
import com.pharmacy.view.elements.CustomTableModel.TableSelectionEventHandler;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class AddPatientFrame extends JFrame {

	private JFrame jFrame;
	
	private static final int BUTTON_WIDTH = 200;
	private static final int BUTTON_HEIGHT = 35;

	private static final int BORDER = 40;

	private static final Font font = new Font("SansSerif", Font.PLAIN, 14);
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldBirthday;
	private JTextField textFieldPhone;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPatientFrame frame = new AddPatientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public AddPatientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldSurname.setColumns(10);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldBirthday.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldPhone.setColumns(10);
		
		JButton button = new JButton("Додати");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DateLabelFormatter toDate = new DateLabelFormatter();
				
				 String name = textFieldName.getText();
				 String surname = textFieldSurname.getText();
				 String birthday = textFieldBirthday.getText();
				 String phone = textFieldPhone.getText();
				 System.out.println(name + " " + surname + " " + birthday + " " + phone);
				 if(name.isEmpty()){
						JOptionPane.showMessageDialog(null, "Name is empty");
						return;	
					}
				if(surname.isEmpty()){
						JOptionPane.showMessageDialog(null, "Surname is empty");
						return;	
					}
				if(birthday.equals(null)){
						JOptionPane.showMessageDialog(null, "Birthday is empty or not correct");
						return;	
					}
				/*if(phone.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Phone is empty");
				}*/
				Patient pt = new Patient(name, surname, birthday, phone);
				boolean res = PatientData.getInstance().addPatient(pt);
		
				if(res){
					JOptionPane.showMessageDialog(null, "Success");
				}
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblSurame = new JLabel("Surname");
		lblSurame.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurame.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblName)
						.addComponent(lblSurame)
						.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldBirthday, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldSurname, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(75, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(254, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addGap(251))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldSurname, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSurame, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldBirthday, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBirthday, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPhone, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		contentPane.setLayout(gl_contentPane);
		
		setTitle("Add patient");
		
		//================ Menu ========================
  		setBounds(new Rectangle(150, 150, 700, 450));
  		JMenuBar menuBar = new JMenuBar();
  		setJMenuBar(menuBar);
  		
  		JMenu main = new JMenu("\u0413\u043E\u043B\u043E\u0432\u043D\u0430");
  		menuBar.add(main);
  		
  		JMenuItem main_page = new JMenuItem("\u0413\u043E\u043B\u043E\u0432\u043D\u0430");
  		main_page.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				dispose();
  				MainFrame mainFrame = new MainFrame();
  				mainFrame.setVisible(true);
  			}
  		});
  		main.add(main_page);
  		
  		JMenuItem pharmacies = new JMenuItem("\u0410\u043F\u0442\u0435\u043A\u0438");
  		pharmacies.setEnabled(false);
  		main.add(pharmacies);
  		
  		JMenuItem medicines = new JMenuItem("\u041B\u0456\u043A\u0438");
  		medicines.setEnabled(false);
  		main.add(medicines);
  		
  		JMenuItem doctors = new JMenuItem("\u041B\u0456\u043A\u0430\u0440\u0456");
  		doctors.setEnabled(false);
  		main.add(doctors);
  		
  		JMenuItem patients = new JMenuItem("\u041F\u0430\u0446\u0456\u0454\u043D\u0442\u0438");
  		patients.setEnabled(false);
  		main.add(patients);
  		
  		JMenuItem finances = new JMenuItem("\u0411\u0443\u0445\u0433\u0430\u043B\u0435\u0442\u0440\u0456\u044F");
  		finances.setEnabled(false);
  		main.add(finances);
  		
  		JMenu menu = new JMenu("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438");
  		menuBar.add(menu);
  		
  		//========================================================
  		PatientData patientContext = PatientData.getInstance();
  		
  		JMenuItem showPatients = new JMenuItem("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438 \u043F\u0430\u0446\u0456\u0454\u043D\u0442\u0456\u0432");
  		showPatients.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				dispose();
				PatientFrame patientFrame = new PatientFrame();
				patientFrame.setVisible(true);
				*/
				dispose();
				CustomTableModel.TableSelectionEventHandler<Patient> selectionHandler = new TableSelectionEventHandler<Patient>(){

                    @Override
                    public void handle(Patient object, int row) {
                        new PatientFrame(object).setVisible(true);
                    }
                    
                };
                List<Patient> ptlist = patientContext.getAllPatients();
                new TableDataViewWindow<>(Patient.class, ptlist, selectionHandler).setVisible(true);
			}
  		});
  		menu.add(showPatients);
  		
  		//=========================================================
  		
  		PrescrData prescrContext = PrescrData.getInstance();
  		
  		JMenuItem showPrescr = new JMenuItem("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438 \u0440\u0435\u0446\u0435\u043F\u0442\u0438");
  		showPrescr.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				dispose();
				PatientFrame patientFrame = new PatientFrame();
				patientFrame.setVisible(true);
				*/
				dispose();
				CustomTableModel.TableSelectionEventHandler<Prescription> selectionHandler = new TableSelectionEventHandler<Prescription>(){

                    @Override
                    public void handle(Prescription object, int row) {
                        new PrescriptionsFrame(object).setVisible(true);
                    }
                    
                };
                List<Prescription> prescr_list = prescrContext.getAllPrescr();
                new TableDataViewWindow<>(Prescription.class, prescr_list, selectionHandler).setVisible(true);
			}
  		});
  		menu.add(showPrescr);
  		
  		PurchaseData purchContext = PurchaseData.getInstance();
  		
  		JMenuItem showPurch = new JMenuItem("\u041F\u0435\u0440\u0435\u0433\u043B\u044F\u043D\u0443\u0442\u0438 \u043F\u043E\u043A\u0443\u043F\u043A\u0438");
  		showPurch.addActionListener(new ActionListener() {
  			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				dispose();
				PatientFrame patientFrame = new PatientFrame();
				patientFrame.setVisible(true);
				*/
				dispose();
				CustomTableModel.TableSelectionEventHandler<Purchase> selectionHandler = new TableSelectionEventHandler<Purchase>(){

                    @Override
                    public void handle(Purchase object, int row) {
                        new PurchaseFrame(object).setVisible(true);
                    }
                    
                };
                List<Purchase> ptlist = purchContext.getAllPurchases();
                new TableDataViewWindow<>(Purchase.class, ptlist, selectionHandler).setVisible(true);
			}
  		});
  		menu.add(showPurch);
  		
  		JMenu add = new JMenu("\u0414\u043E\u0434\u0430\u0442\u0438");
  		menuBar.add(add);
  		
  		JMenuItem addPatient = new JMenuItem("\u0414\u043E\u0434\u0430\u0442\u0438 \u043F\u0430\u0446\u0456\u0454\u043D\u0442\u0430");
  		addPatient.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				dispose();
  				AddPatientFrame addPatientFrame = new AddPatientFrame();
  				addPatientFrame.setVisible(true);
  			}
  		});
  		add.add(addPatient);
  		
  		JMenuItem addPurchase = new JMenuItem("\u0414\u043E\u0434\u0430\u0442\u0438 \u043F\u043E\u043A\u0443\u043F\u043A\u0443");
  		addPurchase.addActionListener(new ActionListener() {
//  			public void actionPerformed(ActionEvent e) {
//  				dispose();
//  				AddPurchaseFrame addPurchFrame = new AddPurchaseFrame();
//  				addPurchFrame.setVisible(true);
//  			}
  			@Override
            public void actionPerformed(ActionEvent e) {
  				dispose();
                new AddPurchaseFrame(patientContext.getAllPatients()).setVisible(true);
//                for(Patient p : patientContext.getAllPatients()){
//                	System.out.println(p);
//                }
            }
  		});
  		add.add(addPurchase);
  		//=================== Menu ========================
		
	}
}
