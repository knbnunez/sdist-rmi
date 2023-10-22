import grpc
from concurrent import futures
import calculator_pb2
import calculator_pb2_grpc

class CalculatorServicer(calculator_pb2_grpc.CalculatorServicer):
    #
    def Add(self, request, context):
        print(f"Peticion de Suma desde IP: {context.peer()}")
        result = request.operand1 + request.operand2
        return calculator_pb2.Response(result=result)
    #
    def Subtract(self, request, context):
        print(f"Peticion de Resta desde IP: {context.peer()}")
        result = request.operand1 - request.operand2
        return calculator_pb2.Response(result=result)
    #
    def Multiply(self, request, context):
        print(f"Peticion de Multiplicacion desde IP: {context.peer()}")
        result = request.operand1 * request.operand2
        return calculator_pb2.Response(result=result)
    #
    def Divide(self, request, context):
        print(f"Peticion de Division desde IP: {context.peer()}")
        result = request.operand1 / request.operand2
        return calculator_pb2.Response(result=result)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    calculator_pb2_grpc.add_CalculatorServicer_to_server(CalculatorServicer(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print("Servidor levantado")
    server.wait_for_termination()

if __name__ == '__main__': 
    serve()