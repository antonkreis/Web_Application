package App.database;

import App.model.TrainTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public interface TrainTicketRepository extends CrudRepository<TrainTicket, String>{
    @Override
    @Cacheable(value = "Train Ticket")
    Optional<TrainTicket> findById(String id);
}
