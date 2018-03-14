package soup.movie.ui.boxoffice;

import android.support.annotation.NonNull;

import java.util.List;

import soup.movie.data.BoxOfficeMovie;
import soup.movie.ui.BaseUiModel;

interface BoxOfficeUiModel extends BaseUiModel {

    class InProgress implements BoxOfficeUiModel {

        public InProgress() {
        }

        @Override
        public String toString() {
            return "BoxOfficeUiModel.InProgress{}";
        }
    }

    class Data implements BoxOfficeUiModel {

        private final String mTitle;
        private final List<BoxOfficeMovie> mMovies;

        Data(@NonNull String title, @NonNull List<BoxOfficeMovie> movies) {
            mTitle = title;
            mMovies = movies;
        }

        @NonNull
        String getTitle() {
            return mTitle;
        }

        @NonNull
        List<BoxOfficeMovie> getMovies() {
            return mMovies;
        }

        @Override
        public String toString() {
            return "BoxOfficeUiModel.Data{" +
                    "mTitle='" + mTitle + '\'' +
                    ", mMovies=" + mMovies +
                    '}';
        }
    }
}
