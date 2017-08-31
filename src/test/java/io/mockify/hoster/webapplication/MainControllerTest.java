package io.mockify.hoster.webapplication;

import io.mockify.hoster.model.dao.Repository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    private Repository repository = mock(Repository.class);

    @InjectMocks
    private MainController controller = new MainController(repository);



}