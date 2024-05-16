package org.example.dog.models.votes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
public class VoteForTheDog implements PojoMarker {
    	private String image_id;
        private String sub_id;
        private int value;

    public VoteForTheDog(String image_id, String sub_id, int value) {
        this.image_id = image_id;
        this.sub_id = sub_id;
        this.value = value;
    }
}
