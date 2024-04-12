package com.pedroapps.californiatrips.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinations_table")
data class Destination(

    @ColumnInfo(name = "destination_id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "destination_name")
    var name: String,

    @ColumnInfo(name = "destination_description")
    var description: String,

    @ColumnInfo(name = "has_visited")
    var hasVisited: Boolean
)
