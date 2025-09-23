package io.github.williamandradesantana.libraryapi.repository;

import io.github.williamandradesantana.libraryapi.services.TransactionsServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionsTest {

    @Autowired
    private TransactionsServices transactionsServices;

    /*
    * Commit -> confirmar as alterações
    * Rollback -> desfazes as alterações
    * */
    @Test
    void simpleTransactionTest() {
        transactionsServices.execute();
    }
}
