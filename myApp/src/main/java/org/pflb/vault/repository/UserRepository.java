package org.pflb.vault.repository;

import org.pflb.vault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable("rofl")
    User getByLogin(String login);


}
