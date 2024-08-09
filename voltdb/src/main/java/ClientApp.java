import org.voltdb.client.*;
import org.voltdb.VoltTable;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientFactory.createClient(config);
        try {
            client.createConnection("localhost", 32775);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
                System.out.println(client.getConnectedHostList().toString());
            try {
                client.callProcedure("SelectSubscribers");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ProcCallException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
