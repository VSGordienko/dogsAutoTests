package org.example.dog.models.favourites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
public class FavouriteDog implements PojoMarker {
    private String image_id;
    private String sub_id;

    public FavouriteDog(String image_id, String sub_id) {
        this.image_id = image_id;
        this.sub_id = sub_id;
    }
}
