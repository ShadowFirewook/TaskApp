package com.example.taskapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long? = null,
    var title:String? = null,
    var description:String? = null,
    var time:Long? = null
):java.io.Serializable
