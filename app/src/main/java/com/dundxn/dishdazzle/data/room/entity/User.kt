package com.dundxn.dishdazzle.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
class User {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var userEmail: String = ""

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var password: String = ""
}