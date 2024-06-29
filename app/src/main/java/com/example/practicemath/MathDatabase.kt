package com.example.practicemath

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [HistoryOperation::class], version = 1, exportSchema = false)
abstract public class MathDatabase: RoomDatabase()
{
    abstract fun operationDAO():OperationDAO

    companion object
    {
        @Volatile
        private var instant:MathDatabase?=null

        fun getInstance(context:Context,scope: CoroutineScope):MathDatabase
        {
            return instant ?: synchronized(Any())
            {
                instant?:buildDatabase(context)
                    .also{ instant=it}
            }

        }

        private fun buildDatabase(context: Context):MathDatabase
        {
            return Room.databaseBuilder(context.applicationContext,MathDatabase::class.java,"math_database")
                .build()
        }


        }
    }

