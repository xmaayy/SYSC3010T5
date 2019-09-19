import java.net.*;
import java.util.Scanner;

public class UDPSender {

	private final static int PACKETSIZE = 100 ;

	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
	         int repeat       	= Integer.parseInt( args[2] ) ;
	         socket = new DatagramSocket() ;
	         
	         // Construct the socket
	         DatagramSocket recv_socket = new DatagramSocket( port+1 ) ;
     
	         Scanner in;
	         in = new Scanner (System.in);
	         String message = null;
			 System.out.println("Enter text to be sent, ENTER to quit ");
			 message = in.nextLine();
			 if (message.length()!=0){
				 for (int i = 0; i < repeat; i++)
				 {
					 String new_message = message + (i+1) ;
					 byte [] data = new_message.getBytes() ;
					 DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
					 socket.send( packet ) ;
					 
					 System.out.println("Waiting for reply ... ");				 
					 packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
					 recv_socket.receive( packet ) ;

					 System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()).trim() ) ;
				 }
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}

