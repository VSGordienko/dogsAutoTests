package org.example.dog.models.votes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
public class PostTheDogForVoting implements PojoMarker {

        private String message;
            private int id;
            private String image_id;
            private String sub_id;
            private int value;
            private String country_code;

    public PostTheDogForVoting(String message, int id, String image_id, String sub_id, int value, String country_code) {
        this.message = message;
        this.id = id;
        this.image_id = image_id;
        this.sub_id = sub_id;
        this.value = value;
        this.country_code = country_code;
    }
}
