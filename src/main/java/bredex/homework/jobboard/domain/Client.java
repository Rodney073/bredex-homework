package bredex.homework.jobboard.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    private String clientKey;
    private Long ClientId;
    private String clientName;
    private String email;

    /**
     * Validating client email input
     */
    public Client validateEmail(String email) {
        if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new InvalidEmailException("Invalid email address format!");
        }
        if (email.length() > 250) {
            throw new InvalidEmailException("The email can't be longer than 250 characters!");
        }
        return this;
    }

    /**
     * Validating client name input
     */
    public Client validateClientName(String clientName) {
        if (clientName.length() > 100) {
            throw new InvalidClientNameException("The name can't be longer than 100 characters!");
        }
        if (clientName.equals("")) {
            throw new InvalidClientNameException("The name can not be empty!");
        }
        return this;
    }

    public Client setUniqueClientKey() {
        this.clientKey = UUID.randomUUID().toString();
        return this;
    }
}
