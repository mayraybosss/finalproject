import com.shani.domain.Lamp;

import com.shani.repository.LampRepository;
import com.shani.repository.impl.LampRepositoryImpl;
import org.junit.Test;


public class RepositoryTest {

    LampRepository<Long, Lamp> lampRepository = new LampRepositoryImpl();

    Lamp lampAdd = new Lamp("Australia", true, "ChinaNumbaOne");


    @Test
    public void add() {
        lampRepository.save(lampAdd);
    }

    @Test
    public void findById() {
        Lamp returnedById = lampRepository.findById((long) 1);
    }

    @Test
    public void findAll() {
        lampRepository.findAll();
    }
}
