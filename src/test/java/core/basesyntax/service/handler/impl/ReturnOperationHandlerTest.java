package core.basesyntax.service.handler.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.exception.InvalidInputException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.enums.Operation;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ReturnOperationHandlerTest extends BaseOperationHandlerTest {
    @Test
    void handle_returnFruitTransaction_Ok() {
        db.put(BANANA, 30);
        FruitTransaction transaction = new FruitTransaction(Operation.RETURN, BANANA, 10);
        handleTransaction(transaction);
        assertEquals(Map.of(BANANA, 40), db);
    }

    @Test
    void handle_returnFruitTransaction_notOk() {
        FruitTransaction transaction = new FruitTransaction(Operation.RETURN, BANANA, 10);
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                () -> handleTransaction(transaction));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Can't return fruits that are/were not in storage";
        assertEquals(expectedMessage, actualMessage);
    }
}
