package br.com.pix.prototype.infrastructure.repository;

import br.com.pix.prototype.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PixRepository extends MongoRepository<User, String> {

    public Optional<User> findByKeyValue(String keyValue);
}
