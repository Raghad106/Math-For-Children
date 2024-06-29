package com.example.practicemath

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.practicemath.databinding.DesignItemHistoryBinding

class AdapterOperation(private var operationsList: List<HistoryOperation>, private var listener: OnFragmentClickListener?): RecyclerView.Adapter<AdapterOperation.OperationHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationHolder
    {
        val binding:DesignItemHistoryBinding=DesignItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OperationHolder(binding)
    }

    override fun onBindViewHolder(holder: OperationHolder, position: Int)
    {
        val pos=position
        val historyOperation:HistoryOperation= this.operationsList[pos]
        holder.expression.text=historyOperation.getExpression()
        holder.result.text = historyOperation.getResult().toString()
        holder.userAnswer.text = historyOperation.getUserAnswer().toString()
        holder.deleteIcon.setOnClickListener(View.OnClickListener
        {
            listener?.onDeleteItem(historyOperation)
        })
    }

    override fun getItemCount(): Int
    {
        return operationsList.size
    }

    class OperationHolder(private var binding:DesignItemHistoryBinding)
        : ViewHolder(binding.root)
    {
        var userAnswer:TextView=binding.userAnswerTV
        var result:TextView=binding.resultTV
        var expression:TextView=binding.expressionTV
        var deleteIcon:ImageView=binding.cancelIV
    }
}