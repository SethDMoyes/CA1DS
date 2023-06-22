package grpc.service.patient;



import grpc.service.patient.PatientServiceGrpc.PatientServiceBlockingStub;
import grpc.service.patient.PatientServiceGrpc.PatientServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class PatientClient {
	
	//Client for Service 1 (Patient Service)
		
	
	private static PatientServiceBlockingStub blockingStub;
	private static PatientServiceStub asyncStub;

	//Channel Creation
	public static void main(String[] args) {

		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = PatientServiceGrpc.newBlockingStub(channel);

		asyncStub = PatientServiceGrpc.newStub(channel);


		
		generatePID();
		
		patientRecord();

	}
	
	//Unary Method for Patient Service
	public static void generatePID() {
		String fn = "ted";
		String ln = "green";
		
		PatientIDRequest req = PatientIDRequest.newBuilder().setFName(fn).setLName(ln).build();
		PatientIDResponse response = blockingStub.generatePID(req);
		System.out.println("The First and Last names are: " + req.getFName() + " and " + req.getLName());
		System.out.println("The patient ID is: " + response.getPatientID());
	}
	
	//Server Streaming Method for Patient Service
	public static void patientRecord() {
		
		StreamObserver<ConfirmPR> responseObserver = new StreamObserver<ConfirmPR>() {
			
			@Override
			public void onNext(ConfirmPR msg) {
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
			requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("File1").build());
			Thread.sleep(500);
			requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("File2").build());
			Thread.sleep(500);
			requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("File3").build());
			Thread.sleep(500);
			requestObserver.onNext(UploadPR.newBuilder().setPatientRecord("File4").build());
			Thread.sleep(500);
			requestObserver.onCompleted();
			Thread.sleep(10000);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
		
	
}
