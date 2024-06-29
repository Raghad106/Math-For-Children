package com.example.practicemath

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MathApplication:Application()
{
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy {MathDatabase.getInstance(this ,applicationScope) }
    val repository by lazy {MathRepo(database.operationDAO()) }
}