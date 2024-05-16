package org.example.dog.models.favourites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
public class PostFavouriteDog implements PojoMarker {
        private String message;
        private int id;

    public PostFavouriteDog(String message, int id) {
        this.message = message;
        this.id = id;
    }
}
