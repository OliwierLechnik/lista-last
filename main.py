import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("127.0.0.1",42069))

s.sendall(b"uwu")

print("sent uwu")

#print(s.recv(1024).decode("utf-8"))

s.close()
