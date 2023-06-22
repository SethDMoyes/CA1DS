package grpc.service.patient;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import grpc.service.patient.PatientServiceGrpc.PatientServiceImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class PatientServer extends PatientServiceImplBase {

	//Server for Service 2 (Vitals Service)
	public static void main(String[] args) {
		PatientServer patientserver = new PatientServer();

		Properties prop = patientserver.getProperties();
		
		patientserver.registerService(prop);
		
		int port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;

		try {

			Server server = ServerBuilder.forPort(port)
					.addService(patientserver)
					.build()
					.start();

			System.out.println("Patient Server started, listening on " + port);

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
		
		 try (InputStream input = new FileInputStream("src/main/resources/patient.properties")) {

	            prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            System.out.println("Patient Service properies ...");
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
	           // int service_port = 1234;
	            int service_port = Integer.valueOf( prop.getProperty("service_port") );// #.50051;

	            
	            String service_description_properties = prop.getProperty("service_description")  ;
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
	            
	            // Wait a bit
	            Thread.sleep(1000);

	            // Unregister all services
	            //jmdns.unregisterAllServices();

	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	}
	//Unary Method for Vitals Service
	//https://www.geeksforgeeks.org/convert-a-string-to-character-array-in-java/
	public void generatePID(PatientIDRequest request, StreamObserver<PatientIDResponse> responseOBserver) {
		System.out.println("The First and Last names are: " + request.getFName() + " and " + request.getLName());
		
		String PID = "";
		String mash = request.getFName().concat(request.getLName());
		char [] c = mash.toCharArray();
		for (int i = 0; i < c.length; i++) {
            if (c[i] == 'A' || c[i] =='a') {
                c[i] = '1';
            } else if (c[i] == 'E'|| c[i] =='e') {
                c[i] = '2';
            } else if (c[i] == 'I'|| c[i] =='i') {
                c[i] = '0';
            } else if (c[i] == 'O'|| c[i] =='o') {
                c[i] = '2';
            } else if (c[i] == 'U'|| c[i] =='u') {
                c[i] = '9';
            } else if (c[i] == 'T'|| c[i] =='t'|| c[i] =='S'|| c[i] =='s'|| c[i] =='B' || c[i] =='b'){
                c[i] = '*';
            }else {}
		}
		PID = new String(c);
            
		
		PatientIDResponse reply = PatientIDResponse.newBuilder().setPatientID(PID).build();
		responseOBserver.onNext(reply);
		responseOBserver.onCompleted();
	}
	

	//Client Streaming Method for Vitals Service
	public StreamObserver<UploadPR> patientRecord(StreamObserver<ConfirmPR> responseObserver){
		return new StreamObserver<UploadPR>() {
			int count = 0;
			
			@Override
			public void onNext(UploadPR request) {
				System.out.println("Uploading Patient Records ... " + request.getPatientRecord());
				count++;
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("Receving last file ...");
				String PRC = "Completed";
				
				ConfirmPR reply = ConfirmPR.newBuilder().setConfirmation(PRC).setRecordSize(count).build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
				
			}
		};

	}
}
	

