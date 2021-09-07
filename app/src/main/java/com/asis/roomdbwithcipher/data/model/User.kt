package com.asis.roomdbwithcipher.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by cemercioglu on 25.08.2021.
 * Copyright (c) 2021  Asis Elektronik Bilişim Sis. A.Ş. All rights reserved.
 */
@Entity
class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String
) {
    constructor(firstName: String, lastName: String) : this(
        null,
        firstName = firstName,
        lastName = lastName
    )
}

