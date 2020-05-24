package App.database;

import App.model.PlaneTicket;
import org.springframework.data.repository.CrudRepository;


public interface PlaneTicketRepository extends CrudRepository<PlaneTicket, String>{

}
