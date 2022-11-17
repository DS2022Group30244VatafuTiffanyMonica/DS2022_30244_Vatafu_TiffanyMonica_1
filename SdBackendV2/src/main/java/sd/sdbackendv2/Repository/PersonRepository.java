package sd.sdbackendv2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.sdbackendv2.Entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByUsernameAndUserpassword(String username,String psw);
}
