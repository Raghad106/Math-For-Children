package com.example.practicemath

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOperation(ho:HistoryOperation)

    @Update
    suspend fun updateOperation(ho:HistoryOperation)

    @Delete
    suspend fun deleteOperation(ho:HistoryOperation)

    @Query("DELETE FROM HISTORYOPERATION")
    suspend fun deleteAllOperations()

    @Query("SELECT * FROM HISTORYOPERATION ORDER by id DESC")
    fun geAllOperations():Flow<List<HistoryOperation>>
}