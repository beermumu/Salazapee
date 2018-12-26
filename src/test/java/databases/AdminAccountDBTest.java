package databases;

//import org.junit.Before;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminAccountDBTest {
    private AdminAccountDB accountDB;
    private ObservableList accountList;

    @BeforeEach
    void init(){
        accountDB = new AdminAccountDB();
        accountList  = accountDB.loadAccount();
        System.out.println(accountList);

    }

    @Test
    void test_get_user_to_arrayList(){
        for (Object id:accountDB.getAccountID()
             ) {
            System.out.println((String)id);
        }


    }

    @Test
    void check_get_all_id_in_database(){
        assertTrue(accountDB.getAccountID().contains("2"));

    }

    @Test
    void check_false_when_try_to_get_id_not_in_database(){
        assertFalse(accountDB.getAccountID().contains("10000"));
    }

}