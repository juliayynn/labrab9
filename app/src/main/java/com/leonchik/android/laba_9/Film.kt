package com.leonchik.android.laba_9

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.UUID

@Entity
data class Film (@PrimaryKey val id: UUID = UUID.randomUUID(),
                 @SerializedName("Title") var title: String = "",
                 @SerializedName("Year") var year: String = "",
                 @SerializedName("Type") var genre: String = "",
                 @SerializedName("Poster") var posterUrl: String = "",
                 var watched: Boolean = false): Serializable