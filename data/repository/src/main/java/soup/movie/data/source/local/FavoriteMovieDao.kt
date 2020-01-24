package soup.movie.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import soup.movie.data.model.entity.FavoriteMovie

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovieList(): Flow<List<FavoriteMovie>>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavoriteMovie(movie: FavoriteMovie)

    @Query("DELETE FROM favorite_movies WHERE id = :movieId")
    suspend fun deleteFavoriteMovie(movieId: String)

    @Query("SELECT COUNT(id) FROM favorite_movies WHERE id = :movieId")
    suspend fun getCountForFavoriteMovie(movieId: String): Int
}