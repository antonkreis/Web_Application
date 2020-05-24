package App.database;

import App.model.User;
import org.springframework.data.repository.CrudRepository;

public interface JPARepository extends CrudRepository<User, Integer>{
//    List<Ticket> findByClientName(String lastName);
//    Ticket findByID(String id);
}
