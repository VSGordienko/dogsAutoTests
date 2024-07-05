package org.example.dog.models.votes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteForTheDog implements PojoMarker {
    private String image_id;
    private String sub_id;
    private int value;
}
