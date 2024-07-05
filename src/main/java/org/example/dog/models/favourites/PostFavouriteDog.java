package org.example.dog.models.favourites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostFavouriteDog implements PojoMarker {
    private String message;
    private int id;
}
