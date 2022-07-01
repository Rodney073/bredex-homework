package bredex.homework.jobboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class Client {

    @Id
    private String id;
    private String clientName;
    private String email;

    public Client isEmailValid() {
        if (!email.matches("myRegex")) {
            throw new InvalidEmailException("Message");
        }
        return this;
    }
}
