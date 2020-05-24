package App.database;

import App.model.BusTicket;
import org.springframework.data.repository.CrudRepository;


public interface BusTicketRepository extends CrudRepository<BusTicket, String>{

}
