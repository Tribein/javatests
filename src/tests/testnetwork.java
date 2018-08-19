package tests;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class testnetwork extends Thread {

    private int port = 44433;
    private String message = "Java test UDP";
    private int threadtype;
    private InetAddress address;
    public testnetwork(int tt) throws Exception{
        threadtype = tt;
        address = InetAddress.getByName("localhost");
    }
    
    public void senddata() throws Exception {
        System.out.println("Running send data thread");
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
        DatagramSocket dsocket = new DatagramSocket();
        dsocket.send(packet);
        dsocket.close();
    }

    public void listensocket() throws Exception {
        System.out.println("Running listen thread");
        /*
        while(true) {
        try {
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            server.close();
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
        }        
        */
        /*
        int buffer_size = 1024;
        byte buffer[] = new byte[buffer_size];
        try{
            DatagramSocket ds = new DatagramSocket(port);
            while (true) {
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            ds.receive(p);
            System.out.println(new String(p.getData(), 0, p.getLength()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         */
        // Create a socket to listen on the port.
        DatagramSocket dsocket = new DatagramSocket(port);
        // Create a buffer to read datagrams into. If a
        // packet is larger than this buffer, the
        // excess will simply be discarded!
        byte[] buffer = new byte[2048];

        // Create a packet to receive data into the buffer
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        // Now loop forever, waiting to receive packets and printing them.
        while (true && !dsocket.isClosed() ) {
            // Wait to receive a datagram
            dsocket.receive(packet);
            // Convert the contents to a string, and display them
            String msg = new String(buffer, 0, packet.getLength());
            System.out.println(packet.getAddress().getHostName() + ": "
                    + msg);

            // Reset the length of the packet before reusing it.
            packet.setLength(buffer.length);
            dsocket.close();
        }
    }
    
    @Override
    public void run(){
        //0 - listen, send otherwise
        try{
            if(threadtype==0){
                listensocket();
            }else{
                senddata();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
