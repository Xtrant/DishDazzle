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
class ListShopping {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var listShoppingId: Long = 0

    @ColumnInfo
    var userEmail: String = ""

    @ColumnInfo
    var amount: String = ""

    @ColumnInfo
    var name: String = ""

   @ColumnInfo("isList")
    var isList: Boolean = false
}