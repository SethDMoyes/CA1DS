package grpc.service.vitals;

import java.util.Iterator;
import java.util.Random;

import grpc.service.vitals.vitalsServiceGrpc.vitalsServiceBlockingStub;
import grpc.service.vitals.vitalsServiceGrpc.vitalsServiceStub;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class VitalsClient {
	private static vitalsServiceBlockingStub blockingstub;
	private static vitalsServiceStub asyncStub;
		

	//Client for Service 2 (Vitals Service)
	//Channel Creation
	public static void main(String[] args) {

		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50052)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingstub = vitalsServiceGrpc.newBlockingStub(channel);

		asyncStub = vitalsServiceGrpc.newStub(channel);


		getVitals();
		vMonitorAvailability();
	
	}



	////Server Streaming Method for Vitals Service
	public static void getVitalsBlocking() {
		vitalsRequest request = vitalsRequest.newBuilder().setVID(4).build();
	
		try {
			Iterator<vitalsResponse> responces = blockingstub.getVitals(request);
	
			while(responces.hasNext()) {
				vitalsResponse temp = responces.next();
				System.out.println(temp.getPulse() + temp.getBP());				
			}
	
		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public static void getVitals() {
	
		vitalsRequest request = vitalsRequest.newBuilder().setVID(4).build();
	
	
		StreamObserver<vitalsResponse> responseObserver = new StreamObserver<vitalsResponse>() {
	
			int count =0 ;
	
			@Override
			public void onNext(vitalsResponse pvalue) {
				System.out.println("The Vital Monitor's readings are " + "Pulse: " + pvalue.getPulse() + " and  BP:" + pvalue.getBP());
			count += 1;
		}
	
		@Override
		public void onError(Throwable t) {
			t.printStackTrace();
	
		}
	
		@Override
		public void onCompleted() {
			System.out.println("stream is completed ... received "+ count + " readings");
		}
	
	};
	
	asyncStub.getVitals(request, responseObserver);
	
	try {
		Thread.sleep(15000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//Bidirectional Streaming method for Vitals Service
	public static void vMonitorAvailability() {
		StreamObserver<monitorList> responseObserver = new StreamObserver<monitorList>() {
	
			@Override
			public void onNext(monitorList value) {
				// TODO Auto-generated method stub
			
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
		
		requestObserver.onNext(monitorUse.newBuilder().setMonitorID(1).setMonitorStatus("out of use").build());
		requestObserver.onNext(monitorUse.newBuilder().setMonitorID(2).setMonitorStatus("out of use").build());
		requestObserver.onNext(monitorUse.newBuilder().setMonitorID(3).setMonitorStatus("out of use").build());
		requestObserver.onNext(monitorUse.newBuilder().setMonitorID(2).setMonitorStatus("in use").build());
		requestObserver.onNext(monitorUse.newBuilder().setMonitorID(1).setMonitorStatus("in use").build());
		requestObserver.onNext(monitorUse.newBuilder().setMonitorID(4).setMonitorStatus("out of use").build());
		
		// Mark the end of requests
		requestObserver.onCompleted();
		
	
	
		// Sleep for a bit before sending the next one.
		Thread.sleep(new Random().nextInt(1000) + 500);
	
	
	} catch (RuntimeException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {			
		e.printStackTrace();
	}
	
	
	
	try {
		Thread.sleep(15000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}	
	

}
