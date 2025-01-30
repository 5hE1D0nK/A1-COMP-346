
public class Network {
	String clientIP;
	String serverIP;
	int portID;
	String clientConnectionStatus;
	String serverConnectionStatus;
	int maxNbPackets;
	Transactions[] inComingPacket = new Transactions[maxNbPackets];
	Transactions[] outGoingPacket = new Transactions[maxNbPackets];
	String inputBufferStatus;
	String outputBufferStatus;
	int inputIndexClient;
	int inputIndexServer;
	int outputIndexServer;
	int outputIndexClient;
	String networkStatus;
	
	public Network(String context) {
		
	}
	
//	public static String getClientIP() {
//		
//	}
	
//	public static String getServerIP() {
//		
//	}
	
//	public static int getPortID() {
//		
//	}
	
//	public static String getClientConnectionStatus() {
//		
//	}
	
//	public static String getServerConnectionStatus() {
//		
//	}
	
//	public static String getInBufferStatus() {
//		
//	}
	
//	public static String getOutBufferStatus() {
//		
//	}
	
//	public static String getNetworkStatus() {
//		
//	}
	
//	public static int getInputIndexClient() {
//		
//	}
	
//	public static int getInputIndexServer() {
//		
//	}
	
//	public static int getIOutputIndexClient() {
//		
//	}
	
//	public static int getOutputIndexServer() {
//		
//	}
	
	public static void setClientIP(String cip) {
		
	}
	
	public static void  setServerIP(String sip) {
		
	}
	
	public static void setPortID(int pid) {
		
	}
	
	public static void setClientConnectionStatus(String connectStatus) {
		
	}
	
	public static void setServerConnectionStatus(String connectStatus) {
		
	}
	
	public static void setNetworkStatus(String netStatus) {
		
	}
	
	public static void setInBufferStatus(String inBufStatus) {
		
	}
	
	public static void setOutBufferStatus(String outBufStatus) {
		
	}
	
	public static void setInputIndexClient(int i1) {
		
	}
	
	public static void setInputIndexServer(int i2) {
		
	}
	
	public static void setOutputIndexClient(int o2) {
		
	}
	
	public static void setOutputIndexServer(int o1) {
		
	}
	
//	public static boolean connect(String IP) {
//		
//	}
	
	public static void disconnect(String IP) {
		
	}
	
//	public static boolean send(Transactions inPacket) {
//		
//	}
	
//	public static boolean receive(Transactions outPacket) {
//		
//	}
	
//	public static boolean transferOut(Transactions outPacket) {
//		
//	}
	
//	public static boolean transferIn(Transactions inPacket) {
//		
//	}
	
//	public static String toString() {
//		
//	}
	
//	public static run() {
//		
//	}
}
