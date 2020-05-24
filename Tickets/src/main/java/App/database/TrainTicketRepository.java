package App.database;

import App.model.TrainTicket;
import org.springframework.data.repository.CrudRepository;


public interface TrainTicketRepository extends CrudRepository<TrainTicket, String>{

}
