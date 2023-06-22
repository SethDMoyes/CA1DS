package grpc.service.patient;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import grpc.service.patient.PatientServiceGrpc.PatientServiceBlockingStub;
import grpc.service.patient.PatientServiceGrpc.PatientServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class PatientGUI {
	
	private static PatientServiceBlockingStub blockingStub;
	private static PatientServiceStub asyncStub;


	private ServiceInfo PatientServiceInfo;
	
	
	private JFrame frame;
	private JTextField textfName;
	private JTextField textlName;
	private JTextArea textPID; 
	private JTextField textPatientFile1;
	private JTextField textPatientFile2;
	private JTextField textPatientFile3;
	private JTextField textPatientFile4;
	private JTextArea textConfirmation; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI window = new PatientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatientGUI() {
		
		String patient_service_type = "_patient._tcp.local.";
		discoverPatientService(patient_service_type);
		
		//***PLEASE USE IF DISCOVERY DOES NOT WORK***//
//		String host = "localhost";
//		int port = 50051;
		String host = PatientServiceInfo.getHostAddresses()[0];
		int port = PatientServiceInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress(host, port)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = PatientServiceGrpc.newBlockingStub(channel);

		asyncStub = PatientServiceGrpc.newStub(channel);

		
		initialize();
	}

	
	
	private void discoverPatientService(String service_type) {
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Patient Service resolved: " + event.getInfo());

					PatientServiceInfo = event.getInfo();

					int port = PatientServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + PatientServiceInfo.getNiceTextString());
					System.out.println("\t host: " + PatientServiceInfo.getHostAddresses()[0]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Patient Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Patient Service added: " + event.getInfo());

					
				}
			});
			
			// Wait a bit
			Thread.sleep(2000);
			
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		JPanel panel_service_1 = new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		panel_service_1.add(lblNewLabel_1);
		
		textfName = new JTextField();
		panel_service_1.add(textfName);
		textfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		panel_service_1.add(lblNewLabel_2);
		
		textlName = new JTextField();
		panel_service_1.add(textlName);
		textlName.setColumns(10);
		
	//Service 1 method 1
		
		JButton btnGenerateID = new JButton("GenerateID");
		btnGenerateID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fn = textfName.getText();
				String ln = textlName.getText();
				
				PatientIDRequest req = PatientIDRequest.newBuilder().setFName(fn).setLName(ln).build();
				PatientIDResponse response = blockingStub.generatePID(req);
//				textPID.append("The First and Last names are: " + req.getFName() + " and " + req.getLName());
				textPID.append("The patient ID is: " + response.getPatientID());
				System.out.println("The First and Last names are: " + req.getFName() + " and " + req.getLName());
				System.out.println("The patient ID is: " + response.getPatientID());

			}
		});
		panel_service_1.add(btnGenerateID);
		
		textPID = new JTextArea(3, 20);
		textPID .setLineWrap(true);
		textPID.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textPID);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_service_1.add(scrollPane);
		
		
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		panel_service_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_3 = new JLabel("Patient Files");
		panel_service_2.add(lblNewLabel_3);
		
		textPatientFile1 = new JTextField();
		panel_service_2.add(textPatientFile1);
		textPatientFile1.setColumns(10);
		
		textPatientFile2 = new JTextField();
		panel_service_2.add(textPatientFile2);
		textPatientFile2.setColumns(10);
		
		textPatientFile3 = new JTextField();
		panel_service_2.add(textPatientFile3);
		textPatientFile3.setColumns(10);
		
		textPatientFile4 = new JTextField();
		panel_service_2.add(textPatientFile4);
		textPatientFile4.setColumns(10);
		
		//Service 1 method 2
		
		JButton btnFileConfirmation = new JButton("FileConformation");
		btnFileConfirmation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StreamObserver<ConfirmPR> responseObserver = new StreamObserver<ConfirmPR>() {
					
					String file1 = textPatientFile1.getText();
					String file2 = textPatientFile2.getText();
					String file3 = textPatientFile3.getText();
					String file4 = textPatientFile4.getText();
					
					@Override
					public void onNext(ConfirmPR msg) {
						textConfirmation.append("Files are Uploading... " + msg.getConfirmation() + "\nNumber of files: " + msg.getRecordSize());
						System.out.println("Files are Uploading... " + msg.getConfirmation() + "\nNumber of files: " + msg.getRecordSize());
					}

					@Override
					public void onError(Throwable t) {
						// TODO Auto-generated method stub
						t.printStackTrace();
					}

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						System.out.println("\nstream is complete");
						
					}
					
				};
				
				StreamObserver<UploadPR> requestObserver = asyncStub.patientRecord(responseObserver);
				try {
					requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("file1").build());
			
					requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("file2").build());
					requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("file3").build());
					requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("file4").build());
		
					requestObserver.onCompleted();
				} catch (RuntimeException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		panel_service_2.add(btnFileConfirmation);
		
		textConfirmation = new JTextArea(3, 20);
		textConfirmation .setLineWrap(true);
		textConfirmation.setWrapStyleWord(true);
		
		JScrollPane scrollPane2 = new JScrollPane(textConfirmation);
		panel_service_2.add(scrollPane2);
		
		
		
		
		JPanel panel_service_3 = new JPanel();
		frame.getContentPane().add(panel_service_3);
		
		
		
	}

}


