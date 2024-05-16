package api.happyFlow;

import jdk.jfr.Description;
import org.example.Steps;
import org.example.dog.models.votes.GetVotedDog;
import org.example.dog.models.votes.PostTheDogForVoting;
import org.example.dog.models.votes.VoteForTheDog;
import org.example.util.TestConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.example.dog.models.DogUtils.*;
import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"SMOKE"})
public class VotesHappyFlowTest {
    @BeforeTest
    public void checkThatVotesListIsEmpty() {
        Steps.checkThatBodyIsEmpty(VOTES_PATH);
    }

    @Test(testName = "Votes with one value")
    @Description("Testing the 'Votes' functionality with the addition of one value")
    public void votesTest() {
        PostTheDogForVoting postTheDogForVoting = Steps.postDataOnResource(VOTES_PATH, new VoteForTheDog(IMAGE_ID1, SUB_ID1, 10))
                .statusCode(201)
                .extract().as(PostTheDogForVoting.class);
        Assert.assertEquals(postTheDogForVoting.getMessage(), SUCCESS_MESSAGE);
        Assert.assertEquals(postTheDogForVoting.getImage_id(), IMAGE_ID1);
        Assert.assertEquals(postTheDogForVoting.getSub_id(), SUB_ID1);
        Assert.assertEquals(postTheDogForVoting.getValue(), 10);
        GetVotedDog[] dogs = Steps.getDataFromResource(VOTES_PATH)
                .statusCode(200)
                .extract()
                .as(GetVotedDog[].class);
        GetVotedDog dog = dogs[0];
        Assert.assertEquals(dog.getId(), postTheDogForVoting.getId());
        Assert.assertEquals(dog.getImage_id(), IMAGE_ID1);
        Assert.assertEquals(dog.getSub_id(), SUB_ID1);
        Assert.assertEquals(dog.getImage().getId(), IMAGE_ID1);
        Assert.assertEquals(dog.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID1));
        Assert.assertEquals(dog.getValue(), 10);
        GetVotedDog dogById = Steps.getDataFromResource(VOTES_PATH + postTheDogForVoting.getId())
                .statusCode(200)
                .extract()
                .as(GetVotedDog.class);
        Assert.assertEquals(dog, dogById);
    }

    @AfterMethod
    public void deleteDataFromVotes() {
        GetVotedDog[] dogs = Steps.getDataFromResource(VOTES_PATH)
                .extract()
                .as(GetVotedDog[].class);
        for (int i = 0; i < Objects.requireNonNull(dogs).length; i++) {
            Steps.deleteDataFromResource(VOTES_PATH + dogs[i].getId())
                    .statusCode(200)
                    .body("message", equalTo(SUCCESS_MESSAGE));
        }
        Steps.checkThatBodyIsEmpty(VOTES_PATH);
    }
}
