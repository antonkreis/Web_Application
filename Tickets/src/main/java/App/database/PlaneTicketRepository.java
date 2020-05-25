package App.database;

import App.model.PlaneTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

@Service
public interface PlaneTicketRepository extends CrudRepository<PlaneTicket, String>{
    @Override
    @Cacheable(value = "Plane Ticket")
    Optional<PlaneTicket> findById(String id);
}
