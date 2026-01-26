package io.github.williamandradesatana.rentalCompany.modules.client.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ClientTest {

    @Test
    void mustBeCreateClientWithName() {
        // 1. Scenery
        var client = new Client("Jon");

        // 2. Execution
        String name = client.getName();

        // 3. Verification
        assertThat(name).isEqualTo("Jon");
        assertEquals("Jon", name);
        assertNotNull(name);
        assertTrue(name.startsWith(("J")));
        assertThat(name.length()).isLessThan(100);
    }

    @Test
    void mustBeCreateClientNoName() {
        var client = new Client(null);
        var name = client.getName();
        assertNull(name);
    }

}
