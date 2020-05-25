package App.database;

import App.model.BusTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public interface BusTicketRepository extends CrudRepository<BusTicket, String>{
    @Override
    @Cacheable(value = "Bus Ticket")
    Optional<BusTicket> findById(String id);
}
