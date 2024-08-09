import org.voltdb.*;

public class SelectUsersProcedure extends VoltProcedure {

    public final SQLStmt getUsers = new SQLStmt(
            "SELECT * FROM users;"
    );

    public VoltTable[] run() throws VoltAbortException {
        // Execute the SQL statement
        voltQueueSQL(getUsers);
        // Fetch the result
        return voltExecuteSQL();
    }
}
