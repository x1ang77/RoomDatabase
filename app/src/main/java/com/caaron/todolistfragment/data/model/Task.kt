package com.caaron.todolistfragment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id: Long? = null,
    val title: String,
    val date: String,
    val details: String,
    val priority: Int = 0,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false
        if (title != other.title) return false
        if (date != other.date) return false
        if (details != other.details) return false
        if (priority != other.priority) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + title.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + details.hashCode()
        result = 31 * result + priority
        result = 31 * result + image.contentHashCode()
        return result
    }
}