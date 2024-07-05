package org.example.dog.models.votes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetVotedDog implements PojoMarker {
    private int id;
    private String image_id;
    private String sub_id;
    private String created_at;
    private ImageData image;
    private int value;
    private String country_code;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageData {
        private String id;
        private String url;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ImageData imageData = (ImageData) o;
            return Objects.equals(id, imageData.id) && Objects.equals(url, imageData.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, url);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetVotedDog that = (GetVotedDog) o;
        return id == that.id && value == that.value && Objects.equals(image_id, that.image_id) && Objects.equals(sub_id, that.sub_id) && Objects.equals(created_at, that.created_at) && Objects.equals(image, that.image) && Objects.equals(country_code, that.country_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image_id, sub_id, created_at, image, value, country_code);
    }
}
