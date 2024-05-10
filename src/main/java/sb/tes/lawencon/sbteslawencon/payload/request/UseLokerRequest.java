package sb.tes.lawencon.sbteslawencon.payload.request;

public class UseLokerRequest {
    private int nomorLoker;
    private int password;
    public UseLokerRequest() {
    }
    public UseLokerRequest(int nomorLoker, int password) {
        this.nomorLoker = nomorLoker;
        this.password = password;
    }
    public int getNomorLoker() {
        return nomorLoker;
    }
    public void setNomorLoker(int nomorLoker) {
        this.nomorLoker = nomorLoker;
    }
    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }

    
}
