import org.voltdb.client.*;
import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientFactory.createClient(clientConfig);
        try {
            client.createConnection("0.0.0.0", 32776);
            client.callProcedure("SelectSubscribers").getResults();
            System.out.println(client.getConnectedHostList());
        } catch (IOException | ProcCallException e) {
            throw new RuntimeException(e);
        }
    }
}
