package org.example.dog.models.favourites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.util.PojoMarker;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class GetFavouriteDog implements PojoMarker {
    private int id;
    private String user_id;
    private String image_id;
    private String sub_id;
    private String created_at;
    private ImageData image;

    public GetFavouriteDog(int id, String user_id, String image_id, String sub_id, String created_at, ImageData image) {
        this.id = id;
        this.user_id = user_id;
        this.image_id = image_id;
        this.sub_id = sub_id;
        this.created_at = created_at;
        this.image = image;
    }

    @Getter
    @NoArgsConstructor
    public static class ImageData {
        private String id;
        private String url;

        public ImageData(String id, String url) {
            this.id = id;
            this.url = url;
        }

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
    public int hashCode() {
        return Objects.hash(id, user_id, image_id, sub_id, created_at, image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetFavouriteDog that = (GetFavouriteDog) o;
        return id == that.id && Objects.equals(user_id, that.user_id) && Objects.equals(image_id, that.image_id) && Objects.equals(sub_id, that.sub_id) && Objects.equals(created_at, that.created_at) && Objects.equals(image, that.image);
    }
}
