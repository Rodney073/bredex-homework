package bredex.homework.jobboard.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    //Test source: https://mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
    private Client client;

    @BeforeEach
    public void init() {
        client = new Client();
    }

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("validEmailProvider")
    void validEmailShouldNotThrowException(String email) {
        assertDoesNotThrow(() -> {
            client.validateEmail(email);
        });
    }

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("invalidEmailProvider")
    void invalidEmailShouldNotThrowException(String email) {
        InvalidEmailException ex = assertThrows(InvalidEmailException.class, () -> {
            client.validateEmail(email);
        });
        assertTrue(ex.getMessage().contains("Invalid email address format!"));
    }

    // Valid email addresses
    static Stream<String> validEmailProvider() {
        return Stream.of(
                "hello@example.com",                // simple
                "hello@example.co.uk",              // .co.uk, 2 tld
                "hello-2020@example.com",           // -
                "hello.2020@example.com",           // .
                "hello_2020@example.com",           // _
                "h@example.com",                    // local-part one letter
                "h@example-example.com",            // domain contains a hyphen -
                "h@example-example-example.com",    // domain contains two hyphens - -
                "h@example.example-example.com",    // domain contains . -
                "hello.world-2020@example.com");    // local-part contains . -
    }

    // Invalid email addresses
    static Stream<String> invalidEmailProvider() {
        return Stream.of(
                "我買@屋企.香港",                     // this regex doesn't support Unicode
                "hello",                            // email need at least one @
                "hello@2020@example.com",           // email doesn't allow more than one @
                ".hello@example.com",               // local-part can't start with a dot .
                "hello.@example.com",               // local-part can't end with a dot .
                "hello..world@example.com",         // local part don't allow dot . appear consecutively
                "hello!+2020@example.com",          // local-part don't allow special characters like !+
                "hello@example.a",                  // domain tld min 2 chars
                "hello@example..com",               // domain doesn't allow dot . appear consecutively
                "hello@.com",                       // domain doesn't start with a dot .
                "hello@.com.",                      // domain doesn't end with a dot .
                "hello@-example.com",               // domain doesn't allow to start with a hyphen -
                "hello@example.com-",               // domain doesn't allow to end with a hyphen -
                "hello@example_example.com",        // domain doesn't allow underscore
                "1234567890123456789012345678901234567890123456789012345678901234xx@example.com"); // local part is longer than 64 characters
    }

    @Test
    public void emailValidation() {
        //Too long, invalid address
        InvalidEmailException ex = assertThrows(InvalidEmailException.class, () -> {
            client.validateEmail("Email@EmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailEmailE.com");
        });

        assertEquals("The email can't be longer than 250 characters!", ex.getMessage());

    }

    @Test
    public void invalidClientNameShouldThrowException() {

        InvalidClientNameException ex = assertThrows(InvalidClientNameException.class, () -> {
            client.validateClientName("ClientNameClientNameClientNameClientNameClientNameClientNameClientNameClientNameClientNameClientName1");
        });

        assertEquals("The name can't be longer than 100 characters!", ex.getMessage());

        ex = assertThrows(InvalidClientNameException.class, () -> {
            client.validateClientName("");
        });

        assertEquals("The name can not be empty!", ex.getMessage());

    }

    @Test
    public void valid_100CharLongClientNameShouldNotThrowException() {

        assertDoesNotThrow(() -> {
            client.validateClientName("ClientNameClientNameClientNameClientNameClientNameClientNameClientNameClientNameClientNameClientName");
        });
        assertDoesNotThrow(() -> {
            client.validateClientName("My valid Name");
        });

    }

}