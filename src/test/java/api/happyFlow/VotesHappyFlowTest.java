package api.happyFlow;

import jdk.jfr.Description;
import org.example.Steps;
import org.example.dog.models.votes.RetrieveVotedDog;
import org.example.dog.models.votes.DogVotingPublisher;
import org.example.dog.models.votes.VoteForTheDog;
import org.example.util.TestConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

import static org.example.dog.models.DogUtils.*;
import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"SMOKE"})
public class VotesHappyFlowTest {
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void checkThatVotesListIsEmpty() {
        Steps.checkThatBodyIsEmpty(VOTES_PATH);
    }

    @Test(testName = "Votes with one value")
    @Description("Testing the 'Votes' functionality with the addition of one value")
    public void votesTest() {
        DogVotingPublisher dogVotingPublisher = Steps.postDataOnResource(VOTES_PATH, new VoteForTheDog(IMAGE_ID1, SUB_ID1, 10))
                .statusCode(201)
                .extract().as(DogVotingPublisher.class);
        Assert.assertEquals(dogVotingPublisher.getMessage(), SUCCESS_MESSAGE);
        softAssert.assertEquals(dogVotingPublisher.getImage_id(), IMAGE_ID1);
        softAssert.assertEquals(dogVotingPublisher.getSub_id(), SUB_ID1);
        softAssert.assertEquals(dogVotingPublisher.getValue(), 10);
        RetrieveVotedDog[] dogs = Steps.getDataFromResource(VOTES_PATH)
                .statusCode(200)
                .extract()
                .as(RetrieveVotedDog[].class);
        RetrieveVotedDog dog = dogs[0];
        softAssert.assertEquals(dog.getId(), dogVotingPublisher.getId());
        softAssert.assertEquals(dog.getImage_id(), IMAGE_ID1);
        softAssert.assertEquals(dog.getSub_id(), SUB_ID1);
        softAssert.assertEquals(dog.getImage().getId(), IMAGE_ID1);
        softAssert.assertEquals(dog.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID1));
        softAssert.assertEquals(dog.getValue(), 10);
        RetrieveVotedDog dogById = Steps.getDataFromResource(VOTES_PATH + dogVotingPublisher.getId())
                .statusCode(200)
                .extract()
                .as(RetrieveVotedDog.class);
        softAssert.assertEquals(dog, dogById);
        softAssert.assertAll();
    }

    @AfterMethod
    public void deleteDataFromVotes() {
        RetrieveVotedDog[] dogs = Steps.getDataFromResource(VOTES_PATH)
                .extract()
                .as(RetrieveVotedDog[].class);
        for (int i = 0; i < Objects.requireNonNull(dogs).length; i++) {
            Steps.deleteDataFromResource(VOTES_PATH + dogs[i].getId())
                    .statusCode(200)
                    .body("message", equalTo(SUCCESS_MESSAGE));
        }
        Steps.checkThatBodyIsEmpty(VOTES_PATH);
    }
}
