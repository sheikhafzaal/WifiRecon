package com.example.wifirecons.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class UserBadge(
    val created: Int? = null,
    val expires: Int? = null,
    val name: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) : Serializable