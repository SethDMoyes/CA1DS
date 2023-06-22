package grpc.service.vitals;

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
 * <pre>
 * Interface exported by the server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: vitalsService.proto")
public final class vitalsServiceGrpc {

  private vitalsServiceGrpc() {}

  public static final String SERVICE_NAME = "vitalsService.vitalsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.service.vitals.vitalsRequest,
      grpc.service.vitals.vitalsResponse> getGetVitalsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getVitals",
      requestType = grpc.service.vitals.vitalsRequest.class,
      responseType = grpc.service.vitals.vitalsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.service.vitals.vitalsRequest,
      grpc.service.vitals.vitalsResponse> getGetVitalsMethod() {
    io.grpc.MethodDescriptor<grpc.service.vitals.vitalsRequest, grpc.service.vitals.vitalsResponse> getGetVitalsMethod;
    if ((getGetVitalsMethod = vitalsServiceGrpc.getGetVitalsMethod) == null) {
      synchronized (vitalsServiceGrpc.class) {
        if ((getGetVitalsMethod = vitalsServiceGrpc.getGetVitalsMethod) == null) {
          vitalsServiceGrpc.getGetVitalsMethod = getGetVitalsMethod = 
              io.grpc.MethodDescriptor.<grpc.service.vitals.vitalsRequest, grpc.service.vitals.vitalsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "vitalsService.vitalsService", "getVitals"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.vitals.vitalsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.vitals.vitalsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new vitalsServiceMethodDescriptorSupplier("getVitals"))
                  .build();
          }
        }
     }
     return getGetVitalsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.service.vitals.monitorUse,
      grpc.service.vitals.monitorList> getVMonitorAvailabilityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "vMonitorAvailability",
      requestType = grpc.service.vitals.monitorUse.class,
      responseType = grpc.service.vitals.monitorList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.service.vitals.monitorUse,
      grpc.service.vitals.monitorList> getVMonitorAvailabilityMethod() {
    io.grpc.MethodDescriptor<grpc.service.vitals.monitorUse, grpc.service.vitals.monitorList> getVMonitorAvailabilityMethod;
    if ((getVMonitorAvailabilityMethod = vitalsServiceGrpc.getVMonitorAvailabilityMethod) == null) {
      synchronized (vitalsServiceGrpc.class) {
        if ((getVMonitorAvailabilityMethod = vitalsServiceGrpc.getVMonitorAvailabilityMethod) == null) {
          vitalsServiceGrpc.getVMonitorAvailabilityMethod = getVMonitorAvailabilityMethod = 
              io.grpc.MethodDescriptor.<grpc.service.vitals.monitorUse, grpc.service.vitals.monitorList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "vitalsService.vitalsService", "vMonitorAvailability"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.vitals.monitorUse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.service.vitals.monitorList.getDefaultInstance()))
                  .setSchemaDescriptor(new vitalsServiceMethodDescriptorSupplier("vMonitorAvailability"))
                  .build();
          }
        }
     }
     return getVMonitorAvailabilityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static vitalsServiceStub newStub(io.grpc.Channel channel) {
    return new vitalsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static vitalsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new vitalsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static vitalsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new vitalsServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class vitalsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getVitals(grpc.service.vitals.vitalsRequest request,
        io.grpc.stub.StreamObserver<grpc.service.vitals.vitalsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetVitalsMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.service.vitals.monitorUse> vMonitorAvailability(
        io.grpc.stub.StreamObserver<grpc.service.vitals.monitorList> responseObserver) {
      return asyncUnimplementedStreamingCall(getVMonitorAvailabilityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetVitalsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.service.vitals.vitalsRequest,
                grpc.service.vitals.vitalsResponse>(
                  this, METHODID_GET_VITALS)))
          .addMethod(
            getVMonitorAvailabilityMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.service.vitals.monitorUse,
                grpc.service.vitals.monitorList>(
                  this, METHODID_V_MONITOR_AVAILABILITY)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class vitalsServiceStub extends io.grpc.stub.AbstractStub<vitalsServiceStub> {
    private vitalsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private vitalsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected vitalsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new vitalsServiceStub(channel, callOptions);
    }

    /**
     */
    public void getVitals(grpc.service.vitals.vitalsRequest request,
        io.grpc.stub.StreamObserver<grpc.service.vitals.vitalsResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetVitalsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.service.vitals.monitorUse> vMonitorAvailability(
        io.grpc.stub.StreamObserver<grpc.service.vitals.monitorList> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getVMonitorAvailabilityMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class vitalsServiceBlockingStub extends io.grpc.stub.AbstractStub<vitalsServiceBlockingStub> {
    private vitalsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private vitalsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected vitalsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new vitalsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.service.vitals.vitalsResponse> getVitals(
        grpc.service.vitals.vitalsRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetVitalsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class vitalsServiceFutureStub extends io.grpc.stub.AbstractStub<vitalsServiceFutureStub> {
    private vitalsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private vitalsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected vitalsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new vitalsServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_VITALS = 0;
  private static final int METHODID_V_MONITOR_AVAILABILITY = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final vitalsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(vitalsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_VITALS:
          serviceImpl.getVitals((grpc.service.vitals.vitalsRequest) request,
              (io.grpc.stub.StreamObserver<grpc.service.vitals.vitalsResponse>) responseObserver);
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
        case METHODID_V_MONITOR_AVAILABILITY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.vMonitorAvailability(
              (io.grpc.stub.StreamObserver<grpc.service.vitals.monitorList>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class vitalsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    vitalsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.service.vitals.vitalsServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("vitalsService");
    }
  }

  private static final class vitalsServiceFileDescriptorSupplier
      extends vitalsServiceBaseDescriptorSupplier {
    vitalsServiceFileDescriptorSupplier() {}
  }

  private static final class vitalsServiceMethodDescriptorSupplier
      extends vitalsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    vitalsServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (vitalsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new vitalsServiceFileDescriptorSupplier())
              .addMethod(getGetVitalsMethod())
              .addMethod(getVMonitorAvailabilityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
