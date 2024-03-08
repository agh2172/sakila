import com.example.sakila.controllers.ActorController;
import com.example.sakila.entities.Actor;
import com.example.sakila.entities.PartialFilm;
import com.example.sakila.services.ActorService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ActorControllerStepDefs {

    private ActorService actorService;
    private final short expectedId = 1;

    private final Actor expectedActor = new Actor(expectedId, "Doe", "John", new ArrayList<>());

    private Actor actualActor;

    private final PartialFilm partialFilm1 = new PartialFilm((short) 1, "Dune", "I don't like sand. It's coarse and rough and irritating and it gets everywhere.",
            new Date(2024, Calendar.MARCH, 5), (byte) 1, (byte) 1, new BigDecimal(0.05), new BigDecimal(10.0));

    private final List<PartialFilm> expectedFilms = new ArrayList<PartialFilm>() {
        {
            add(partialFilm1);
        }
    };

    private List<PartialFilm> actualFilms;

    @Before
    public void setUp(){
        actorService = mock(ActorService.class);
        //actorService = mock(ActorService.class);
    }

    @Given("the actor with id {short} exists")
    public void givenActorOneExists(short id){
        //actorService = mock(ActorController.class);
        doReturn(expectedActor).when(actorService).getActorById(id);
        doReturn(expectedFilms).when(actorService).filmsStarredIn(id);
    }

    @When("the get request is made for actor {short}")
    public void whenRequestWithId(short id){
        final ActorController actorController = new ActorController(actorService);
        try {
            actualActor = actorController.getActorById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("the get request is made for films starred in {short}")
    public void whenRequestFilmsStarredIn(short id){
        final ActorController actorController = new ActorController(actorService);
        try {
            actualFilms = actorController.filmsStarredIn(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("an actor is returned")
    public void thenActorIsReturned(){
        assertNotNull(actualActor);
        assertEquals("John", actualActor.getFirstName());
        assertEquals("Doe", actualActor.getLastName());
    }

    @Then("a list of films is returned")
    public void thenFilmsAreReturned(){
        assertNotNull(expectedFilms);
        PartialFilm firstFilm = actualFilms.getFirst();
        assertEquals("Dune", firstFilm.getTitle());
    }


}
