package org.example.dog.models.favourites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDog implements PojoMarker {
    private String image_id;
    private String sub_id;
}
