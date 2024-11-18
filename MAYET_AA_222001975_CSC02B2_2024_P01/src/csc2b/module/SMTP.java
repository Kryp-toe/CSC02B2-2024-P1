package csc2b.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class SMTP {
	private String hostString;
	private int portNumber;
	private String SenderEmail;
	private String RecipientEmail;
	private String subjectString;
	private String messagString;
	private Date timestampDate;
	
	public SMTP(String hostString, int portNumber, String senderEmail, String recipientEmail, String subject, String messageString)
	{
		this.hostString = hostString;
		this.portNumber = portNumber;
		this.SenderEmail = senderEmail;
		this.RecipientEmail = recipientEmail;
		this.subjectString = subject;
		this.messagString = messageString;
		this.timestampDate = Date.from(null);
	}
	
	public SMTP() {
		this.hostString = null;
		this.portNumber = 0;
		this.SenderEmail = null;
		this.RecipientEmail = null;
		this.messagString = null;
		this.timestampDate =  new Date();
	}
	
	/**
	 * @return the hostString
	 */
	public String getHostString() {
		return hostString;
	}

	/**
	 * @param hostString the hostString to set
	 */
	public void setHostString(String hostString) {
		this.hostString = hostString;
	}

	/**
	 * @return the portNumber
	 */
	public int getPortNumber() {
		return portNumber;
	}

	/**
	 * @param portNumber the portNumber to set
	 */
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * @return the senderEmail
	 */
	public String getSenderEmail() {
		return SenderEmail;
	}

	/**
	 * @param senderEmail the senderEmail to set
	 */
	public void setSenderEmail(String senderEmail) {
		SenderEmail = "<" + senderEmail + ">";
	}

	/**
	 * @return the recipientEmail
	 */
	public String getRecipientEmail() {
		return RecipientEmail;
	}

	/**
	 * @param recipientEmail the recipientEmail to set
	 */
	public void setRecipientEmail(String recipientEmail) {
		RecipientEmail = "<" + recipientEmail + ">";
	}

	/**
	 * @return the subjectString
	 */
	public String getSubjectString() {
		return subjectString;
	}

	/**
	 * @param subjectString the subjectString to set
	 */
	public void setSubjectString(String subjectString) {
		this.subjectString = subjectString;
	}

	/**
	 * @return the messagString
	 */
	public String getMessagString() {
		return messagString;
	}

	/**
	 * @param messagString the messagString to set
	 */
	public void setMessagString(String messagString)
	{
		//format message string
		this.messagString = "From: " + getSenderEmail() + "\r\n"
				+ "To: " + getRecipientEmail() + "\r\n"
				+ "Date: " + timestampDate + "\r\n"
				+ "Subject: " + getSubjectString() +"\r\n"
				+ "\r\n"
				+ messagString;
		;
	}

	public void send(String text, PrintWriter out, BufferedReader in)
	{
		if(text != null) {
			out.println(text);
			out.flush();
		}
		
		String lineString = null;
		
		try {
			lineString = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(lineString != null) {
			System.out.println(lineString);
		}
	}
	
	public void SendMail()
	{
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			socket = new Socket(hostString, portNumber);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			send("HELO " + InetAddress.getLocalHost().getHostAddress(), out, in);
			send("MAIL FROM:" + this.SenderEmail, out, in);
			send("RCPT TO:" + this.RecipientEmail, out, in);
			send("DATA", out, in);
			send(getMessagString(), out, in);
			System.err.println(getMessagString());
			send(".", out, in);
			send("QUIT", out, in);
			
		} catch (UnknownHostException e) {
			System.err.println("Could not connect to Host. Please enter valid Hols Name and Port Number");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null) {
				try {
					socket.close();
					in.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.timestampDate =  new Date();
		System.out.println("\nYour Email has been Sent: \n" + messagString);
	}
}