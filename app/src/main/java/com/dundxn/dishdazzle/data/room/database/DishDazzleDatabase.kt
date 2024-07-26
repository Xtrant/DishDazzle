package com.dundxn.dishdazzle.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dundxn.dishdazzle.data.room.entity.FavoriteFood
import com.dundxn.dishdazzle.data.room.entity.ListShopping
import com.dundxn.dishdazzle.data.room.entity.User

@Database(entities = [FavoriteFood::class, ListShopping::class, User::class], version = 8)
abstract class DishDazzleDatabase: RoomDatabase() {
    abstract fun favFoodDao(): FavFoodDao
    abstract fun userDao(): UserDao
    abstract fun listShoppingDao(): ListShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: DishDazzleDatabase? = null
        fun getInstance(context: Context): DishDazzleDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DishDazzleDatabase::class.java, "dish_dazzle.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
    }
}