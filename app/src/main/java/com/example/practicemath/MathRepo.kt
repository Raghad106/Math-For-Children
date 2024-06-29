package com.example.practicemath

import kotlinx.coroutines.flow.Flow

class MathRepo(private val operationDao:OperationDAO)
{
    suspend fun addOperation(ho:HistoryOperation)
    {
        operationDao.addOperation(ho)
    }

    suspend fun updateOperation(ho:HistoryOperation)
    {
         operationDao.updateOperation(ho)
    }

    suspend fun deleteOperation(ho:HistoryOperation)
    {
         operationDao.deleteOperation(ho)
    }

    suspend fun deleteAllOperations()
    {
        operationDao.deleteAllOperations()
    }

    fun getAllOperations(): Flow<List<HistoryOperation>>
    {
         return operationDao.geAllOperations()
    }



}