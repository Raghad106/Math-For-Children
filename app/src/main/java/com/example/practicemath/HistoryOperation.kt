package com.example.practicemath

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity
@Parcelize
data class HistoryOperation(
    @PrimaryKey(autoGenerate = true)
     var id: Int,
     val expressionStatement: String,
     val resultStatement: Double,
     val userAnswerStatement: Double?,
     val correctStatement: Boolean,
) : Parcelable
{

    fun getExpression():String
    {
        return this.expressionStatement
    }

    fun getUserAnswer():Double?
    {
        return this.userAnswerStatement
    }

    fun getResult():Double
    {
        return this.resultStatement
    }


    fun getCorrect():Boolean
    {
        return this.correctStatement
    }

    fun getOperationId(): Int
    {
        return this.id
    }
//

}