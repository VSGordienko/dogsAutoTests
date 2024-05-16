package api.negativeFlow;

import jdk.jfr.Description;
import org.example.Steps;
import org.example.dog.models.favourites.FavouriteDog;

import org.testng.annotations.Test;

import static org.example.dog.models.DogUtils.*;
import static org.hamcrest.Matchers.equalTo;

@Test(groups = {"SMOKE"})
public class FavouritesNegativeFlowTest {

    @Test(testName = "Adding a dog to 'Favourites' without an image_id")
    @Description("Testing the functionality when adding a record without the mandatory field image_id")
    public void addToFavouritesWithoutId() {
        Steps.postDataOnResource(FAVOURITES_PATH, new FavouriteDog())
                .statusCode(400)
                .body(equalTo("\"image_id\" is required"));
        Steps.checkThatBodyIsEmpty(FAVOURITES_PATH);
    }

    @Test(testName = "Deleting a record with a non-existing id")
    @Description("Testing functionality for deleting a non-existent record")
    public void deletingANonExistentDog() {
        Steps.deleteDataFromResource(FAVOURITES_PATH + "fff")
                .statusCode(400)
                .body(equalTo("INVALID_ACCOUNT"));
    }

}
