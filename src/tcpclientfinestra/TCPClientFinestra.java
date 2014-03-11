package tcpclientfinestra;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class TCPClientFinestra {
    public static void main(String[] args) {
        Finestra f1=new Finestra();
        f1.setVisible(true);
    }
  }

class Finestra extends JFrame{
    JButton invia=new JButton("Invia");
    JButton converti=new JButton("Converti in maiuscolo");
    JButton cancella=new JButton("Cancella");
   
    JPanel p2=new JPanel();
    JPanel p1=new JPanel();
    JPanel p3=new JPanel();
    JTextField testo1=new JTextField("", 30);
    JTextField testo2=new JTextField("", 30);

    
    Finestra(){
        setSize(350,250);
        setLocationRelativeTo(null);
        setTitle("TCP Client");
        //testo2.setEnabled(false); 
        setResizable(false);
        setLayout(new GridLayout(3,2));
        add(p1);
        p1.add(new JLabel("Testo da inviare al server: "));
        p1.add(testo1);
        
        add(p2);
        p2.add(new JLabel("Testo ricevuto dal server: "));
        p2.add(testo2);
        
        add(p3);
        p3.add(invia);
        p3.add(converti);
        p3.add(cancella);
       
        
        
        addWindowListener(new ListF()); 
        invia.addActionListener(new ListB(testo1,testo2)); 
        cancella.addActionListener(new ListB(testo1,testo2)); 
        converti.addActionListener(new ListB(testo1,testo2)); 
    }
    
}

 class ListF implements WindowListener{
    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    
}

class ListB implements ActionListener {
    JTextField testo1;
    JTextField testo2;
    ListB(){}
    ListB(JTextField testo1, JTextField testo2){
        this.testo1=testo1;
        this.testo2=testo2;
        
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Cancella")){
            testo1.setText("");
            testo2.setText("");
        }
        if(s.equals("Converti in maiuscolo")){
            int port=2000;
            String fine="fine";
        
  try{
        InetAddress serverAddress=InetAddress.getLocalHost();
        Socket socket=new Socket(serverAddress,port);
        
        BufferedReader streamIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter streamOut=new PrintWriter(new OutputStreamWriter (socket.getOutputStream()),true);
           
           
            streamOut.println(testo1.getText());
            testo2.setText(streamIn.readLine().toUpperCase());
            
            
            
            if(testo1.getText().equals(fine)){
                System.exit(0);
            }
        
    }catch(IOException ex){
        System.err.println(ex);
    }
        }
        if(s.equals("Invia")){
             int port=2000;
            String fine="fine";
        
  try{
        InetAddress serverAddress=InetAddress.getLocalHost();
        Socket socket=new Socket(serverAddress,port);
        
        BufferedReader streamIn=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter streamOut=new PrintWriter(new OutputStreamWriter (socket.getOutputStream()),true);
           
           
            streamOut.println(testo1.getText());
            testo2.setText(streamIn.readLine());
            
            
            
            if(testo1.getText().equals(fine)){
                System.exit(0);
            }
        
    }catch(IOException ex){
        System.err.println(ex);
    }
            
        }
        
    }
}