package grpc.service.patient;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: patientService.proto")
public final class PatientServiceGrpc {

  private PatientServiceGrpc() {}

  public static final String SERVICE_NAME = "PatientService.PatientService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.service.patient.PatientIDRequest,
      grpc.service.patient.PatientIDResponse> getGeneratePIDMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "generatePID",
      requestType = grpc.service.patient.PatientIDRequest.class,
      responseType = grpc.service.patient.PatientIDResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.service.patient.PatientIDRequest,
      grpc.service.patient.PatientIDResponse> getGeneratePIDMethod() {
    io.grpc.MethodDescriptor<grpc.service.patient.PatientIDRequest, grpc.service.patient.PatientIDResponse> getGeneratePIDMethod;
    if ((getGeneratePIDMethod = PatientServiceGrpc.getGeneratePIDMethod) == null) {
      synchronized (PatientServiceGrpc.class) {
        if ((getGeneratePIDMethod = PatientServiceGrpc.getGeneratePIDMethod) == null) {
          PatientServiceGrpc.getGeneratePIDMethod = getGeneratePIDMethod = 
              io.grpc.MethodDescriptor.<grpc.service.patient.PatientIDRequest, grpc.service.patient.PatientIDResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PatientService.PatientService", "generatePID"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.patient.PatientIDRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.patient.PatientIDResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PatientServiceMethodDescriptorSupplier("generatePID"))
                  .build();
          }
        }
     }
     return getGeneratePIDMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.service.patient.UploadPR,
      grpc.service.patient.ConfirmPR> getPatientRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "patientRecord",
      requestType = grpc.service.patient.UploadPR.class,
      responseType = grpc.service.patient.ConfirmPR.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.service.patient.UploadPR,
      grpc.service.patient.ConfirmPR> getPatientRecordMethod() {
    io.grpc.MethodDescriptor<grpc.service.patient.UploadPR, grpc.service.patient.ConfirmPR> getPatientRecordMethod;
    if ((getPatientRecordMethod = PatientServiceGrpc.getPatientRecordMethod) == null) {
      synchronized (PatientServiceGrpc.class) {
        if ((getPatientRecordMethod = PatientServiceGrpc.getPatientRecordMethod) == null) {
          PatientServiceGrpc.getPatientRecordMethod = getPatientRecordMethod = 
              io.grpc.MethodDescriptor.<grpc.service.patient.UploadPR, grpc.service.patient.ConfirmPR>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "PatientService.PatientService", "patientRecord"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.patient.UploadPR.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.patient.ConfirmPR.getDefaultInstance()))
                  .setSchemaDescriptor(new PatientServiceMethodDescriptorSupplier("patientRecord"))
                  .build();
          }
        }
     }
     return getPatientRecordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PatientServiceStub newStub(io.grpc.Channel channel) {
    return new PatientServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PatientServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PatientServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PatientServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PatientServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PatientServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void generatePID(grpc.service.patient.PatientIDRequest request,
        io.grpc.stub.StreamObserver<grpc.service.patient.PatientIDResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGeneratePIDMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.service.patient.UploadPR> patientRecord(
        io.grpc.stub.StreamObserver<grpc.service.patient.ConfirmPR> responseObserver) {
      return asyncUnimplementedStreamingCall(getPatientRecordMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGeneratePIDMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.service.patient.PatientIDRequest,
                grpc.service.patient.PatientIDResponse>(
                  this, METHODID_GENERATE_PID)))
          .addMethod(
            getPatientRecordMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.service.patient.UploadPR,
                grpc.service.patient.ConfirmPR>(
                  this, METHODID_PATIENT_RECORD)))
          .build();
    }
  }

  /**
   */
  public static final class PatientServiceStub extends io.grpc.stub.AbstractStub<PatientServiceStub> {
    private PatientServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PatientServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PatientServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PatientServiceStub(channel, callOptions);
    }

    /**
     */
    public void generatePID(grpc.service.patient.PatientIDRequest request,
        io.grpc.stub.StreamObserver<grpc.service.patient.PatientIDResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGeneratePIDMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.service.patient.UploadPR> patientRecord(
        io.grpc.stub.StreamObserver<grpc.service.patient.ConfirmPR> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getPatientRecordMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PatientServiceBlockingStub extends io.grpc.stub.AbstractStub<PatientServiceBlockingStub> {
    private PatientServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PatientServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PatientServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PatientServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.service.patient.PatientIDResponse generatePID(grpc.service.patient.PatientIDRequest request) {
      return blockingUnaryCall(
          getChannel(), getGeneratePIDMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PatientServiceFutureStub extends io.grpc.stub.AbstractStub<PatientServiceFutureStub> {
    private PatientServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PatientServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PatientServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PatientServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.service.patient.PatientIDResponse> generatePID(
        grpc.service.patient.PatientIDRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGeneratePIDMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GENERATE_PID = 0;
  private static final int METHODID_PATIENT_RECORD = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PatientServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PatientServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GENERATE_PID:
          serviceImpl.generatePID((grpc.service.patient.PatientIDRequest) request,
              (io.grpc.stub.StreamObserver<grpc.service.patient.PatientIDResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PATIENT_RECORD:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.patientRecord(
              (io.grpc.stub.StreamObserver<grpc.service.patient.ConfirmPR>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PatientServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PatientServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.service.patient.PatientServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PatientService");
    }
  }

  private static final class PatientServiceFileDescriptorSupplier
      extends PatientServiceBaseDescriptorSupplier {
    PatientServiceFileDescriptorSupplier() {}
  }

  private static final class PatientServiceMethodDescriptorSupplier
      extends PatientServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PatientServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PatientServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PatientServiceFileDescriptorSupplier())
              .addMethod(getGeneratePIDMethod())
              .addMethod(getPatientRecordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
