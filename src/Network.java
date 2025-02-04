import java.util.Arrays;

public class Network {
	private String clientIP;
	private String serverIP;
	private int portID;
	private String clientConnectionStatus;
	private String serverConnectionStatus;
	private int maxNbPackets = 10;
	private Transactions[] inComingPacket = new Transactions[maxNbPackets];
	private Transactions[] outGoingPacket = new Transactions[maxNbPackets];
	private String inputBufferStatus;
	private String outputBufferStatus;
	private int inputIndexClient, inputIndexServer = 0;
	private int outputIndexServer, outputIndexClient = 0;
	private String networkStatus;
	
	public Network(String context) {
		this.networkStatus = "active";
	    this.clientConnectionStatus = "disconnected";
	    this.serverConnectionStatus = "disconnected";
	    this.inComingPacket = new Transactions[maxNbPackets];
	    this.outGoingPacket = new Transactions[maxNbPackets];
	    this.inputBufferStatus = "empty";
	    this.outputBufferStatus = "empty";
		
	}
	
	// Getters
	public synchronized String getClientIP() { return clientIP; }
	public synchronized String getServerIP() { return serverIP; }
	public synchronized int getPortID() { return portID; }
	public synchronized String getClientConnectionStatus() { return clientConnectionStatus; }
	public synchronized String getServerConnectionStatus() { return serverConnectionStatus; }
	public synchronized String getInBufferStatus() { return inputBufferStatus; }
	public synchronized String getOutBufferStatus() { return outputBufferStatus; }
	public synchronized String getNetworkStatus() { return networkStatus; }
	public synchronized int getInputIndexClient() { return inputIndexClient; }
	public synchronized int getInputIndexServer() { return inputIndexServer; }
	public synchronized int getOutputIndexClient() { return outputIndexClient; }
	public synchronized int getOutputIndexServer() { return outputIndexServer; }
	
	// Setters
	public synchronized void setClientIP(String cip) { this.clientIP = cip; }
	public synchronized void setServerIP(String sip) { this.serverIP = sip; }
	public synchronized void setPortID(int pid) { this.portID = pid; }
	public synchronized void setClientConnectionStatus(String connectStatus) { this.clientConnectionStatus = connectStatus; }
	public synchronized void setServerConnectionStatus(String connectStatus) { this.serverConnectionStatus = connectStatus; }
	public synchronized void setNetworkStatus(String netStatus) { this.networkStatus = netStatus; }
	public synchronized void setInBufferStatus(String inBufStatus) { this.inputBufferStatus = inBufStatus; }
	public synchronized void setOutBufferStatus(String outBufStatus) { this.outputBufferStatus = outBufStatus; }
	public synchronized void setInputIndexClient(int i1) { this.inputIndexClient = i1; }
	public synchronized void setInputIndexServer(int i2) { this.inputIndexServer = i2; }
	public synchronized void setOutputIndexClient(int o2) { this.outputIndexClient = o2; }
	public synchronized void setOutputIndexServer(int o1) { this.outputIndexServer = o1; }

	
	public synchronized boolean connect(String IP) {
		if (clientIP == null)
		{
			clientIP = IP;
			clientConnectionStatus = "connected";
			return true;
		} 
		else if (serverIP == null) 
		{
			serverIP = IP;
			serverConnectionStatus = "connected";
			return true;
		}
		return false;
	}
	
	public synchronized boolean disconnect(String IP) {
		if (clientIP != null && clientIP.equals(IP))
		{
			clientIP = null;
			clientConnectionStatus = "disconnected";
			return true;
		} 
		else if (serverIP != null && serverIP.equals(IP))
		{
			serverIP = null;
			serverConnectionStatus = "disconnected";
			return true;
		}
		return false;
		
	}
	
	public synchronized boolean send(Transactions inPacket) {
		while (inputIndexClient == maxNbPackets)
		{
			Thread.yield();
		}
		
		inComingPacket[inputIndexClient++] = inPacket;
		
		if (inputIndexClient == maxNbPackets) 
		{
			inputBufferStatus = "full";
		} 
		else 
		{
			inputBufferStatus = "normal";
		}
		return true;
	}
	
	public synchronized boolean receive(Transactions outPacket) {
		while (outputIndexClient == 0)
		{
			Thread.yield();
		}
		
		outGoingPacket[outputIndexClient--] = outPacket;
		
		if (outputIndexClient == 0) 
		{
			inputBufferStatus = "empty";
		} 
		else 
		{
			inputBufferStatus = "normal";
		}
		return true;
	}
	
	public synchronized boolean transferIn(Transactions inPacket) {
		while(inputIndexServer == 0)
		{
			Thread.yield();
		}
		
		inComingPacket[inputIndexServer--] = inPacket;
		
		if (inputIndexServer == 0)
		{
			inputBufferStatus = "empty";
		}
		else
		{
			inputBufferStatus = "normal";
		}
		return true;
	}
	
	public synchronized boolean transferOut(Transactions outPacket) {
		while(outputIndexServer == maxNbPackets)
		{
			Thread.yield();
		}
		
		outGoingPacket[outputIndexServer++] = outPacket;
		
		if (outputIndexServer == maxNbPackets)
		{
			outputBufferStatus = "full";
		}
		else
		{
			outputBufferStatus = "normal";
		}
		return true;
	}
	@Override
	public String toString() {
		return "Network [clientIP=" + clientIP + ", serverIP=" + serverIP + ", portID=" + portID
				+ ", clientConnectionStatus=" + clientConnectionStatus + ", serverConnectionStatus="
				+ serverConnectionStatus + ", maxNbPackets=" + maxNbPackets + ", inComingPacket="
				+ Arrays.toString(inComingPacket) + ", outGoingPacket=" + Arrays.toString(outGoingPacket)
				+ ", inputBufferStatus=" + inputBufferStatus + ", outputBufferStatus=" + outputBufferStatus
				+ ", inputIndexClient=" + inputIndexClient + ", inputIndexServer=" + inputIndexServer
				+ ", outputIndexServer=" + outputIndexServer + ", outputIndexClient=" + outputIndexClient
				+ ", networkStatus=" + networkStatus + "]";
	}
	
	public void run() {
		while (networkStatus.equals("active"))
		{
			if(clientConnectionStatus.equals("disconnected") && serverConnectionStatus.equals("disconnected"))
			{
				networkStatus = "inactive";
			}
			Thread.yield();
		}
	}
}
