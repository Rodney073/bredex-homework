package bredex.homework.jobboard.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository {
    String registerClient (Client client);
}
