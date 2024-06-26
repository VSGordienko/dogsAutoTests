package api.happyFlow;

import jdk.jfr.Description;
import org.example.Steps;
import org.example.dog.models.favourites.FavouriteDog;
import org.example.dog.models.favourites.GetFavouriteDog;
import org.example.dog.models.favourites.PostFavouriteDog;
import org.example.util.TestConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.example.dog.models.DogUtils.*;
import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"SMOKE"})
public class FavouritesHappyFlowTest {
    @BeforeTest
    public void checkThatFavouriteListIsEmpty() {
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
    }

    @Test(testName = "Favourites with one value")
    @Description("Testing the 'Favourites' functionality with the addition of one value")
    public void checkFavouritesWithOneValueTest() {
        PostFavouriteDog postFavouriteDog = Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog(IMAGE_ID1, SUB_ID1))
                .statusCode(200)
                .extract().as(PostFavouriteDog.class);
        Assert.assertEquals(postFavouriteDog.getMessage(), SUCCESS_MESSAGE);
        GetFavouriteDog[] dogs = Steps.getDataFromResource(FAVOURITES_PATH)
                .statusCode(200)
                .extract()
                .as(GetFavouriteDog[].class);
        GetFavouriteDog dog = dogs[0];
        Assert.assertEquals(dog.getId(), postFavouriteDog.getId());
        Assert.assertEquals(dog.getUser_id(), TestConfiguration.getDogApiUserId());
        Assert.assertEquals(dog.getImage_id(), IMAGE_ID1);
        Assert.assertEquals(dog.getSub_id(), SUB_ID1);
        Assert.assertEquals(dog.getImage().getId(), IMAGE_ID1);
        Assert.assertEquals(dog.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID1));
        GetFavouriteDog dogById = Steps.getDataFromResource(FAVOURITES_PATH +postFavouriteDog.getId())
                .statusCode(200)
                .extract()
                .as(GetFavouriteDog.class);
        Assert.assertEquals(dog,dogById);
    }

    @Test(testName = "Favourites with multiple values")
    @Description("Testing the 'Favourites' functionality with the addition of multiple values")
    public void checkFavouritesWithMultipleValuesTest() {
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
        PostFavouriteDog postFavouriteDog1 = Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog(IMAGE_ID1, SUB_ID1))
                .statusCode(200)
                .extract().as(PostFavouriteDog.class);
        Assert.assertEquals(postFavouriteDog1.getMessage(), SUCCESS_MESSAGE);
        PostFavouriteDog postFavouriteDog2 = Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog(IMAGE_ID2, SUB_ID2))
                .statusCode(200)
                .extract().as(PostFavouriteDog.class);
        Assert.assertEquals(postFavouriteDog2.getMessage(), SUCCESS_MESSAGE);
        GetFavouriteDog[] dogs = Steps.getDataFromResource(FAVOURITES_PATH)
                .statusCode(200)
                .extract()
                .as(GetFavouriteDog[].class);
        GetFavouriteDog dog1 = dogs[0];
        Assert.assertEquals(dog1.getId(), postFavouriteDog1.getId());
        Assert.assertEquals(dog1.getUser_id(), TestConfiguration.getDogApiUserId());
        Assert.assertEquals(dog1.getImage_id(), IMAGE_ID1);
        Assert.assertEquals(dog1.getSub_id(), SUB_ID1);
        Assert.assertEquals(dog1.getImage().getId(), IMAGE_ID1);
        Assert.assertEquals(dog1.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID1));
        GetFavouriteDog dog2 = dogs[1];
        Assert.assertEquals(dog2.getId(), postFavouriteDog2.getId());
        Assert.assertEquals(dog2.getUser_id(), TestConfiguration.getDogApiUserId());
        Assert.assertEquals(dog2.getImage_id(), IMAGE_ID2);
        Assert.assertEquals(dog2.getSub_id(), SUB_ID2);
        Assert.assertEquals(dog2.getImage().getId(), IMAGE_ID2);
        Assert.assertEquals(dog2.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID2));
    }

    @AfterMethod
    public void deleteDataFromFavourites() {
        GetFavouriteDog[] dogs = Steps.getDataFromResource(FAVOURITES_PATH).statusCode(200)
                .extract()
                .as(GetFavouriteDog[].class);
        for (int i = 0; i < Objects.requireNonNull(dogs).length; i++) {
            Steps.deleteDataFromResource(FAVOURITES_PATH + dogs[i].getId())
                    .statusCode(200)
                    .body("message", equalTo(SUCCESS_MESSAGE));
        }
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
    }
}
