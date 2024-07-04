package api.negativeFlow;

import jdk.jfr.Description;
import org.example.Steps;
import org.testng.annotations.Test;

import static org.example.dog.models.DogUtils.*;
import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"SMOKE"})
public class VotesNegativeFlowTest {

    @Test(testName = "Getting a record with a non-existing id")
    @Description("Testing functionality for getting a non-existent record")
    public void wrongIdForGetVotes() {
        Steps.getDataFromResource(VOTES_PATH + "fff")
                .statusCode(404)
                .body(equalTo("NOT_FOUND"));
    }

    @Test(testName = "Deleting a record with a non-existing id")
    @Description("Testing functionality for deleting a non-existent record")
    public void deletingNonExistentDog() {
        Steps.deleteDataFromResource(VOTES_PATH + "fff")
                .statusCode(404)
                .body(equalTo("NO_VOTE_FOUND_MATCHING_ID"));
    }
}
