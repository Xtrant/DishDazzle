package com.dundxn.dishdazzle.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["userEmail"],
        childColumns = ["userEmail"],
        onDelete = ForeignKey.CASCADE
    )
])
class FavoriteFood {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var favFoodId: String = ""

    @ColumnInfo
    var userEmail: String = ""

    @ColumnInfo
    var title: String = ""

    @ColumnInfo
    var region: String = ""

    @ColumnInfo
    var uri: String = ""

}