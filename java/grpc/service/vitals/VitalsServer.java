package grpc.service.vitals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;


import grpc.service.vitals.vitalsServiceGrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class VitalsServer extends vitalsServiceGrpc.vitalsServiceImplBase {

	//Server for Service 2 (Vitals Service)
	public static void main(String[] args) {
		VitalsServer vserver = new VitalsServer();

		Properties prop = vserver.getProperties();
		
		vserver.registerService(prop);
		
		int port = Integer.valueOf( prop.getProperty("service_port") );// #.50052;

		try {

			Server server = ServerBuilder.forPort(port)
					.addService(vserver)
					.build()
					.start();

			System.out.println("Vitals Server started, listening on " + port);

			server.awaitTermination();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	
	private Properties getProperties() {
		
		Properties prop = null;		
		
		 try (InputStream input = new FileInputStream("src/main/resources/vitals.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Math Service properies ...");
	            System.out.println("\t service_type: " + prop.getProperty("service_type"));
	            System.out.println("\t service_name: " +prop.getProperty("service_name"));
	            System.out.println("\t service_description: " +prop.getProperty("service_description"));
		        System.out.println("\t service_port: " +prop.getProperty("service_port"));

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	
		 return prop;
	}
	
	
	private  void registerService(Properties prop) {
		
		 try {
	            // Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	            
	            String service_type = prop.getProperty("service_type") ;
	            String service_name = prop.getProperty("service_name")  ;
	           
	            int service_port = Integer.valueOf( prop.getProperty("service_port") );

	            
	            String service_description_properties = prop.getProperty("service_description")  ;
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
	            
	            // Wait a bit
	            Thread.sleep(1000);

	         

	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	}
		//Server Streaming Method for Vitals Service
	 	//https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
		public void getVitals(vitalsRequest request, StreamObserver<vitalsResponse> responseObserver) {
			System.out.println(" Vital Machine ID is " + request.getVID());
			
			for(int i=0; i<10; i++) {
				Random rand = new Random();
				int maxp = 0;
				int maxSBP = 0;
				int maxDBP = 0;
				int minp = 30;
				int mindbp = 65;
				int minsbp = 110;
				
				if(request.getVID() < 5){
					maxp = 60;
				} else if (request.getVID() > 6 && request.getVID() < 9) {
					maxp = 120;
				} else {
					maxp = 200;
				}
				
				if(request.getVID() < 5){
					maxSBP = 120;
				} else if (request.getVID() > 6 && request.getVID() < 9) {
					maxSBP = 140;
				} else {
					maxSBP = 170;
				}
				
				if(request.getVID() < 5){
					maxDBP = 70;
				} else if (request.getVID() > 6 && request.getVID() < 9) {
					maxDBP = 81;
				} else {
					maxSBP = 90;
				}
				
				int randomPulse = rand.nextInt(maxp-minp) + minp;
				int SBP = rand.nextInt(maxSBP-minsbp) + minsbp;
				int DBP = rand.nextInt(maxDBP-mindbp) + mindbp;
				
				
				vitalsResponse reply = vitalsResponse.newBuilder().setPulse(randomPulse).setBP(SBP + "/" + DBP).build();
				
				responseObserver.onNext(reply);
		
				try {
					//wait for a second
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		responseObserver.onCompleted();

	}
		
		//Bidirectional Streaming method for Vitals Service
		public StreamObserver<monitorUse> vMonitorAvailability(StreamObserver<monitorList> responseObserver) {
			
			return new StreamObserver<monitorUse> () {

				ArrayList <Integer> monitors = new ArrayList<Integer>();
				@Override
				public void onNext(monitorUse value) {
					// TODO Auto-generated method stub
					System.out.println("Receiving Monitor ID and status: " + value.getMonitorID() + value.getMonitorStatus());
//					monitors.add(value.getMonitorID());
					
					if(value.getMonitorStatus().equalsIgnoreCase("out of use")){
						monitors.add(value.getMonitorID());
					}
					else if (value.getMonitorStatus().equalsIgnoreCase("in use")) {
					monitors.remove(value.getMonitorID());	
					}
					
				
					String availableMonitors = monitors.toString();
					
					monitorList reply = monitorList.newBuilder().setDisplayMonitors(availableMonitors).build();
					
					responseObserver.onNext(reply);
				}

				@Override
				public void onError(Throwable t) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onCompleted() {
					// TODO Auto-generated method stub
					
				}
				
			};
		}
		
		
}

		//PLEASE IGNORE

		//This was an attempted method for creating a Vital monitor class with 2 values 
		//but I could not get it to work 
		
		
//		public class VitalMonitor {
//			int ID;
//			int RoomNum;
//			
//			public VitalMonitor(int iD, int roomNum) {
//				super();
//				ID = iD;
//				RoomNum = roomNum;
//			}
//
//			public int getID() {
//				return ID;
//			}
//
//			public void setID(int iD) {
//				ID = iD;
//			}
//
//			public int getRoomNum() {
//				return RoomNum;
//			}
//
//			public void setRoomNum(int roomNum) {
//				RoomNum = roomNum;
//			}
//			
//			@Override
//	        public String toString() {
//	            return "VitalMonitor{" +
//	                    "ID=" + ID +
//	                    ", RoomNum=" + RoomNum +
//	                    '}';
//	        }
//			
//			
//			
//			
//		}
//		public StreamObserver<monitorUse> vMonitorAvailability(StreamObserver<monitorList> responseObserver) {
//			ArrayList<VitalMonitor> ALMonitor = new ArrayList<VitalMonitor>();
//			ALMonitor.add(new VitalMonitor(1,3));
//			ALMonitor.add(new VitalMonitor(2,4));
//		
//			
//			return new StreamObserver<monitorUse> () {
//				
//				@Override
//				public void onNext(monitorUse monitor) {
//					
////					int ID = monitor.getMonitorID();
////					for (int i = 0; i<ALMonitor.size(); i++) {
////						if (monitor.getMonitorStatus() == "in use") {
////							ALMonitor.remove(i);
////						} 
////						
////					}
////					
////					if (monitor.getMonitorStatus() == "out of use") {
////						ALMonitor.add(new VitalMonitor(monitor.getMonitorID(),4));
////					}
////					
//					// TODO Auto-generated method stub
//					System.out.println("reciving monitor use data... " +  monitor.getMonitorID() + monitor.getMonitorStatus());
//					String test = ALMonitor.toString();
//					monitorList reply = monitorList.newBuilder().setDisplayMonitors(test).build();
//					responseObserver.onNext(reply);
//					
//				}
//
//				@Override
//				public void onError(Throwable t) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void onCompleted() {
//					// TODO Auto-generated method stub
//					System.out.println("receiving completed ");
//					
//					//completed too
//					responseObserver.onCompleted();
//					
//				}
//				
//			};
//		}

