package com.yychatclient.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.yychat.model.Message;
import com.yychatclient.controller.ClientConnect;

public class FriendChat1 extends JFrame implements ActionListener{//����������
 
	//�м䲿��
	JScrollPane jsp;//������
	JTextArea jta;//�ı���
	
	
	//�ϲ�����
	JPanel jp;//���
	JTextField jtf;//һ���ı�
	JButton jb;
	
	String sender;
	String receiver;
	
	public FriendChat1(String sender,String receiver){//�Զ��幹�췽��
		this.sender=sender;
		this.receiver=receiver;
		
		jta = new JTextArea();//�ı�����
		jta.setEditable(false);//���������ı�����б༭
		jta.setForeground(Color.red);//������ɫ
		jsp = new JScrollPane(jta);//������ֻ�����ڹ��췽�����涨�����
		this.add(jsp);//���봰����
		
		jp = new JPanel();
		jtf = new JTextField(15);//�ַ�����
		jtf.setForeground(Color.red);;
		jb = new JButton("����");
		jb.addActionListener(this);
		jp.add(jtf);
		jp.add(jb);
		this.add(jp,"South");
		this.setSize(350, 240);
		this.setTitle(sender+"���ں�"+receiver+"����");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//������ʾ����
		this.setVisible(true);
		
	}
	public static void main(String[] args){
		//FriendChat friendChat=new FriendChat("1","2");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {//�¼���������
	 if(arg0.getSource()==jb) {
		 jta.append(jtf.getText()+"\r\n");
		 
		 //������� ����������Ϣ
		 Message mess=new Message();
		 mess.setSender(sender);
		 mess.setReceiver(receiver);
		 mess.setContent(jtf.getText());
		 mess.setMessageType(Message.message_Common);
		 ObjectOutputStream oos;
		try {
			Socket s=(Socket)ClientConnect.hsmSocket.get(sender);
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	 }
		
	}
	public void appendJta(String showMessage){
	  jta.append(showMessage+"\r\n");
	}

}