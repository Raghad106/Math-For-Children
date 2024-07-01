package com.example.practicemath

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.practicemath.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() ,OnFragmentClickListener
{
    private lateinit var binding: ActivityMainBinding
    private var correctAnswerCount: Int =1
    private var incorrectAnswerCount: Int=1

    private val viewModel: MathViewModel by viewModels {
        MathViewModelFactory((application as MathApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().
        add(R.id .fragmentContainer,CalculatorFragment()).commit()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
        when(item.itemId)
        {
            R.id.calculator -> replaceFragment(CalculatorFragment())
            R.id.stats -> replaceFragment(StatsFragment.newInstance(correctAnswerCount,incorrectAnswerCount))
            R.id.history -> replaceFragment(HistoryFragment())

            else->
            {
                replaceFragment(CalculatorFragment())
            }
        }
            true
        }

    }

    private fun replaceFragment (fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().
        replace(R.id .fragmentContainer,fragment).commit()
    }

    override fun onAddTOHistory(operation: HistoryOperation)
    {
        viewModel.addOperation(operation)
    }

    override fun onDeleteItem(operation:HistoryOperation)
    {
        viewModel.deleteOperation(operation)
    }

    override fun onDeleteAllItems()
    {
        showAlertDialog()
    }

    override fun onChangeStatsChart(correctAnswers: Int, inCorrectAnswers: Int)
    {
        correctAnswerCount=correctAnswers
        incorrectAnswerCount=inCorrectAnswers
        StatsFragment.newInstance(correctAnswers,inCorrectAnswers)
        Toast.makeText(baseContext, correctAnswers.toString()+"  "+inCorrectAnswers.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All History Record")
        builder.setMessage("Are You Sure That You Want Delete All History Record")

        builder.setPositiveButton("OK") { dialog, _ ->
            viewModel.deleteAllOperations()
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}




