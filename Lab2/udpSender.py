# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
repeat = int(sys.argv[3])

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)

print ("Enter data to transmit: ENTER to quit")
msg = sys.stdin.readline().strip()
if len(msg) > 0:
    # Repeat specified number of times
    for i in range(repeat):
        data = msg + str(i)
        s.sendto(data.encode('utf-8'), server_address)

        buf, address = s.recvfrom(port)
        if not len(buf):
            break
        print ("Received %s bytes from %s %s: " % (len(buf), address, buf ))

#s.shutdown(1)
