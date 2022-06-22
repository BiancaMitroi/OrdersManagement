package model;

public class Client {

    private int clientId;
    private String clientName;
    private String clientAdress;

    public Client(){

    }

    public Client(int clientId, String clientName, String clientAdress){
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientAdress = clientAdress;
    }

    public String getClientId() {
        String s = "";
        s += clientId;
        return s;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public String toString(){
        return "\nClient name: " + clientName + "\nClient address: " + clientAdress;
    }
}
