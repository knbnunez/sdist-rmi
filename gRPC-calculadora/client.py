import grpc
import calculator_pb2
import calculator_pb2_grpc

def main():
    server_ip = 'localhost'
    server_port = '50051'
    channel = grpc.insecure_channel(f'{server_ip}:{server_port}')
    stub = calculator_pb2_grpc.CalculatorStub(channel)
   
    while True:
        # Presentación de opciones
        print("\n1. Suma")
        print("2. Resta")
        print("3. Multiplicacion")
        print("4. Division")
        option = input("Opcion: ")

        # Verificación de entrada
        if option in ["1", "2", "3", "4"]:
            op1 = float(input("Operando 1: "))
            op2 = float(input("Operando 2: "))
            if(option == "1"):
                result = stub.Add(calculator_pb2.Request(operand1=op1, operand2=op2))
            elif(option == "2"): 
                result = stub.Subtract(calculator_pb2.Request(operand1=op1, operand2=op2))
            elif(option == "3"): 
                result = stub.Multiply(calculator_pb2.Request(operand1=op1, operand2=op2))
            elif(option == "4"): 
                result = stub.Divide(calculator_pb2.Request(operand1=op1, operand2=op2))
            #
            print(f">>> {result}") if result else print(0) # Por alguna razón, si el resultado es = 0, no se logra visualizar ningún valor, como si fuese vacío. Este operador ternario tampoco lo soluciona...
        else: print("Vuelva a intentarlo...")

if __name__ == '__main__':
    main()