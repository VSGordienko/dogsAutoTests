package api.happyFlow;

import jdk.jfr.Description;
import org.example.Steps;
import org.example.dog.models.favourites.FavouriteDog;
import org.example.dog.models.favourites.RetrieveFavouriteDog;
import org.example.dog.models.favourites.FavouriteDogPublisher;
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
public class FavouritesHappyFlowTest {
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void checkThatFavouriteListIsEmpty() {
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
    }

    @Test(testName = "Favourites with one value")
    @Description("Testing the 'Favourites' functionality with the addition of one value")
    public void checkFavouritesWithOneValueTest() {
        FavouriteDogPublisher favouriteDogPublisher = Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog(IMAGE_ID1, SUB_ID1))
                .statusCode(200)
                .extract().as(FavouriteDogPublisher.class);
        Assert.assertEquals(favouriteDogPublisher.getMessage(), SUCCESS_MESSAGE);
        RetrieveFavouriteDog[] dogs = Steps.getDataFromResource(FAVOURITES_PATH)
                .statusCode(200)
                .extract()
                .as(RetrieveFavouriteDog[].class);
        RetrieveFavouriteDog dog = dogs[0];
        softAssert.assertEquals(dog.getId(), favouriteDogPublisher.getId());
        softAssert.assertEquals(dog.getUser_id(), TestConfiguration.getDogApiUserId());
        softAssert.assertEquals(dog.getImage_id(), IMAGE_ID1);
        softAssert.assertEquals(dog.getSub_id(), SUB_ID1);
        softAssert.assertEquals(dog.getImage().getId(), IMAGE_ID1);
        softAssert.assertEquals(dog.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID1));
        RetrieveFavouriteDog dogById = Steps.getDataFromResource(FAVOURITES_PATH + favouriteDogPublisher.getId())
                .statusCode(200)
                .extract()
                .as(RetrieveFavouriteDog.class);
        softAssert.assertEquals(dog, dogById);
        softAssert.assertAll();
    }

    @Test(testName = "Favourites with multiple values")
    @Description("Testing the 'Favourites' functionality with the addition of multiple values")
    public void checkFavouritesWithMultipleValuesTest() {
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
        FavouriteDogPublisher favouriteDogPublisher1 = Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog(IMAGE_ID1, SUB_ID1))
                .statusCode(200)
                .extract().as(FavouriteDogPublisher.class);
        Assert.assertEquals(favouriteDogPublisher1.getMessage(), SUCCESS_MESSAGE);
        FavouriteDogPublisher favouriteDogPublisher2 = Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog(IMAGE_ID2, SUB_ID2))
                .statusCode(200)
                .extract().as(FavouriteDogPublisher.class);
        Assert.assertEquals(favouriteDogPublisher2.getMessage(), SUCCESS_MESSAGE);
        RetrieveFavouriteDog[] dogs = Steps.getDataFromResource(FAVOURITES_PATH)
                .statusCode(200)
                .extract()
                .as(RetrieveFavouriteDog[].class);
        RetrieveFavouriteDog dog1 = dogs[0];
        softAssert.assertEquals(dog1.getId(), favouriteDogPublisher1.getId());
        softAssert.assertEquals(dog1.getUser_id(), TestConfiguration.getDogApiUserId());
        softAssert.assertEquals(dog1.getImage_id(), IMAGE_ID1);
        softAssert.assertEquals(dog1.getSub_id(), SUB_ID1);
        softAssert.assertEquals(dog1.getImage().getId(), IMAGE_ID1);
        softAssert.assertEquals(dog1.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID1));
        RetrieveFavouriteDog dog2 = dogs[1];
        softAssert.assertEquals(dog2.getId(), favouriteDogPublisher2.getId());
        softAssert.assertEquals(dog2.getUser_id(), TestConfiguration.getDogApiUserId());
        softAssert.assertEquals(dog2.getImage_id(), IMAGE_ID2);
        softAssert.assertEquals(dog2.getSub_id(), SUB_ID2);
        softAssert.assertEquals(dog2.getImage().getId(), IMAGE_ID2);
        softAssert.assertEquals(dog2.getImage().getUrl(), TestConfiguration.getDogApiImage(IMAGE_ID2));
        softAssert.assertAll();
    }

    @AfterMethod
    public void deleteDataFromFavourites() {
        RetrieveFavouriteDog[] dogs = Steps.getDataFromResource(FAVOURITES_PATH).statusCode(200)
                .extract()
                .as(RetrieveFavouriteDog[].class);
        for (int i = 0; i < Objects.requireNonNull(dogs).length; i++) {
            Steps.deleteDataFromResource(FAVOURITES_PATH + dogs[i].getId())
                    .statusCode(200)
                    .body("message", equalTo(SUCCESS_MESSAGE));
        }
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
    }
}
