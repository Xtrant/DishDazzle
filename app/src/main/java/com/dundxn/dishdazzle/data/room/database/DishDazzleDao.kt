package com.dundxn.dishdazzle.data.room.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dundxn.dishdazzle.data.room.entity.FavoriteFood
import com.dundxn.dishdazzle.data.room.entity.ListShopping
import com.dundxn.dishdazzle.data.room.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE userEmail = :email AND password = :password")
    fun getUser(email: String, password: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM user WHERE userEmail = :email")
    suspend fun deleteUser(email: String)
}

@Dao
interface FavFoodDao {
    @Query("SELECT * FROM FavoriteFood WHERE userEmail = :email")
    fun getFavFood(email: String): LiveData<List<FavoriteFood>>

    @Query("SELECT EXISTS(SELECT * FROM FavoriteFood WHERE title = :title AND userEmail = :email)")
    suspend fun isFavorite(title: String, email: String): Boolean

    @Insert
    suspend fun insertFavFood(favFood: FavoriteFood)

    @Delete
    suspend fun deleteFavFood(favFood: FavoriteFood)
}

@Dao
interface ListShoppingDao {
    @Query("SELECT * FROM ListShopping WHERE userEmail = :email")
    fun getListShopping(email: String): LiveData<List<ListShopping>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListShopping(listShopping: ListShopping)

    @Update
    suspend fun updateListShopping(listShopping: ListShopping)

    @Query("DELETE FROM ListShopping WHERE isList = 0")
    suspend fun deleteListShopping()

    @Query("SELECT EXISTS(SELECT * FROM ListShopping WHERE userEmail = :email AND name = :name AND isList = 1)")
    fun isListShopping(email: String, name: String): Boolean
}