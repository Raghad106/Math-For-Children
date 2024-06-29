package com.example.practicemath

interface OnFragmentClickListener
{
    fun onAddTOHistory(operation:HistoryOperation)
    fun onDeleteItem(operation:HistoryOperation)
    fun onDeleteAllItems()
    fun onChangeStatsChart(correctAnswers:Int, inCorrectAnswers:Int)
}