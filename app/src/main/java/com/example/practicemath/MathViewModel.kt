package com.example.practicemath

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MathViewModel(private val reop:MathRepo):ViewModel()
{
   fun addOperation(ho:HistoryOperation)= viewModelScope.launch {
       reop.addOperation(ho)
   }

    fun updateOperation(ho:HistoryOperation)= viewModelScope.launch {
        reop.updateOperation(ho)
    }

    fun deleteOperation(ho:HistoryOperation)= viewModelScope.launch {
        reop.deleteOperation(ho)
    }

    fun deleteAllOperations()= viewModelScope.launch {
        reop.deleteAllOperations()
    }

    fun getAllOperations(): LiveData<List<HistoryOperation>>
    {
        return reop.getAllOperations().asLiveData()
    }

}


class MathViewModelFactory(private val repository: MathRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MathViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MathViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}