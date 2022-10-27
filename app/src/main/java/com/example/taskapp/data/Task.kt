package com.example.taskapp.data

data class Task(
    var title:String? = null,
    var description:String? = null,
    var time:Long? = null
):java.io.Serializable
