package grpc.service.vitals;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

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

import grpc.service.vitals.vitalsServiceGrpc.vitalsServiceBlockingStub;
import grpc.service.vitals.vitalsServiceGrpc.vitalsServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class VitalsGUI {
	
	private static vitalsServiceBlockingStub blockingstub;
	private static vitalsServiceStub asyncStub;

	private ServiceInfo vitalsServiceInfo;
	
	
	private JFrame frame;
	private JTextField textMonitorID;
	private JTextArea textVitals ;
	private JTextField textMonitor_ID;
	private JTextField textMonitorStatus;
	private JTextArea textMonitorList ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VitalsGUI window = new VitalsGUI();
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
	public VitalsGUI() {
		
		String vitals_service_type = "_maths._tcp.local.";
		discoverVitalsService(vitals_service_type);
		
		//***PLEASE USE IF DISCOVERY DOES NOT WORK***//
//		String host = "localhost";
//		int port = 50052;
		String host = vitalsServiceInfo.getHostAddresses()[0];
		int port = vitalsServiceInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", port)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingstub = vitalsServiceGrpc.newBlockingStub(channel);

		asyncStub = vitalsServiceGrpc.newStub(channel);

		
		initialize();
	}

	
	
	private void discoverVitalsService(String service_type) {
		
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Vitals Service resolved: " + event.getInfo());

					vitalsServiceInfo = event.getInfo();

					int port = vitalsServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + vitalsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + vitalsServiceInfo.getHostAddresses()[1]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Vitals Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Vitals Service added: " + event.getInfo());

					
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
		
		JLabel lblNewLabel_1 = new JLabel("Monitor ID");
		panel_service_1.add(lblNewLabel_1);
		
		textMonitorID = new JTextField();
		panel_service_1.add(textMonitorID);
		textMonitorID.setColumns(10);
		
		//Service 2 method 1
		
		JButton btnGetVitals = new JButton("Get Vitals");
		btnGetVitals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(textMonitorID.getText());
				
				vitalsRequest request = vitalsRequest.newBuilder().setVID(ID).build();

				StreamObserver<vitalsResponse> responseObserver = new StreamObserver<vitalsResponse>() {

					int count =0 ;

					@Override
					public void onNext(vitalsResponse pvalue) {
						textVitals.append("The Vital Monitor's readings are " + "Pulse: " + pvalue.getPulse() + " and  BP:" + pvalue.getBP());
						System.out.println("The Vital Monitor's readings are " + "Pulse: " + pvalue.getPulse() + " and  BP:" + pvalue.getBP());
						count += 1;
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						textVitals.append("stream is completed ... received "+ count + " readings");
						System.out.println("stream is completed ... received "+ count + " readings");
					}

				};
				asyncStub.getVitals(request, responseObserver);

				try {
					Thread.sleep(15000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
				
		panel_service_1.add(btnGetVitals);
		
		textVitals = new JTextArea(5, 30);
		textVitals  .setLineWrap(true);
		textVitals .setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textVitals);
		
//		textVitals.setSize(new Dimension(200, 500));
		panel_service_1.add(scrollPane);
		
		
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		
		JPanel panel_service_3 = new JPanel();
		frame.getContentPane().add(panel_service_3);
		
	
		
		JLabel lblNewLabel_3 = new JLabel("Monitor ID");
		panel_service_2.add(lblNewLabel_3);
		
		textMonitor_ID = new JTextField();
		panel_service_2.add(textMonitor_ID);
		textMonitor_ID.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Monitor Status");
		panel_service_2.add(lblNewLabel_4);
		
		textMonitorStatus = new JTextField();
		panel_service_2.add(textMonitorStatus);
		textMonitorStatus.setColumns(10);
	
		//Service 2 method 2
		
		JButton btnDisplayMonitors = new JButton("Display Monitors");
		btnDisplayMonitors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int MID = Integer.parseInt(textMonitor_ID.getText());
				String Usage = textMonitorStatus.getText();
				
				StreamObserver<monitorList> responseObserver = new StreamObserver<monitorList>() {

					@Override
					public void onNext(monitorList value) {
						// TODO Auto-generated method stub
						textMonitorList.append("Receiving list of available monitors by ID... " + value.getDisplayMonitors());
						System.out.println("Receiving list of available monitors by ID... " + value.getDisplayMonitors());
						
						
					}

					@Override
					public void onError(Throwable t) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onCompleted() {
						// TODO Auto-generated method stub
						System.out.println("Stream is completed");
						
					}
				};
				
				StreamObserver<monitorUse> requestObserver = asyncStub.vMonitorAvailability(responseObserver);
				
				try {
					
					requestObserver.onNext(monitorUse.newBuilder().setMonitorID(MID).setMonitorStatus(Usage).build());
					requestObserver.onNext(monitorUse.newBuilder().setMonitorID(MID).setMonitorStatus(Usage).build());
					requestObserver.onNext(monitorUse.newBuilder().setMonitorID(MID).setMonitorStatus(Usage).build());
					requestObserver.onNext(monitorUse.newBuilder().setMonitorID(MID).setMonitorStatus(Usage).build());
					requestObserver.onNext(monitorUse.newBuilder().setMonitorID(MID).setMonitorStatus(Usage).build());
					requestObserver.onNext(monitorUse.newBuilder().setMonitorID(MID).setMonitorStatus(Usage).build());
					
					// Mark the end of requests
					requestObserver.onCompleted();
					


					// Sleep for a bit before sending the next one.
					Thread.sleep(new Random().nextInt(1000) + 500);


				} catch (RuntimeException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {			
					e1.printStackTrace();
				}



				try {
					Thread.sleep(15000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}	
		});
		panel_service_1.add(btnDisplayMonitors);
		
		textMonitorList = new JTextArea(5, 30);
		textMonitorList .setLineWrap(true);
		textMonitorList.setWrapStyleWord(true);
		
		JScrollPane scrollPane1 = new JScrollPane(textMonitorList);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_service_2.add(scrollPane1);
		
		
		
	}
}
	
