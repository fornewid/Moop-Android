package soup.movie.data.source;

import java.util.List;

import io.reactivex.Single;
import soup.movie.data.DailyBoxOfficeRequest;
import soup.movie.data.DailyBoxOfficeResult;
import soup.movie.data.Movie;
import soup.movie.data.MovieListRequest;
import soup.movie.data.WeeklyBoxOfficeRequest;
import soup.movie.data.WeeklyBoxOfficeResult;

public class MovieRepository implements MovieDataSource {

    private final MovieDataSource mRemoteDataSource;

    public MovieRepository(MovieDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Single<List<Movie>> getMovieList(MovieListRequest movieListRequest) {
        return mRemoteDataSource.getMovieList(movieListRequest);
    }

    @Override
    public Single<DailyBoxOfficeResult> getDailyBoxOfficeList(DailyBoxOfficeRequest dailyBoxOfficeRequest) {
        return mRemoteDataSource.getDailyBoxOfficeList(dailyBoxOfficeRequest);
    }

    @Override
    public Single<WeeklyBoxOfficeResult> getWeeklyBoxOfficeList(WeeklyBoxOfficeRequest weeklyBoxOfficeRequest) {
        return mRemoteDataSource.getWeeklyBoxOfficeList(weeklyBoxOfficeRequest);
    }
}
