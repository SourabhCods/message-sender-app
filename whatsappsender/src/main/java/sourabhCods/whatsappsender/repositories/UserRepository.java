package sourabhCods.whatsappsender.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sourabhCods.whatsappsender.entities.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    @Query("{ 'firstName': ?0, 'lastName': ?1 }")
    List<UserEntity> findByFullName(String firstName, String lastName);
    @Query("{'id' : ?0}")
    UserEntity findUserById(String id);

}
