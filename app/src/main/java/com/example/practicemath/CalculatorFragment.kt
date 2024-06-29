package com.example.practicemath

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.practicemath.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var correctAnswers: Int = 0
private var incorrectAnswers: Int = 0
lateinit var fragmentListener: OnFragmentClickListener



class CalculatorFragment : Fragment()
{
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        if (context is OnFragmentClickListener)
        {
            fragmentListener = context
        } else
        {
            throw RuntimeException("$context must implement OnOperationSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val binding:FragmentCalculatorBinding =
            FragmentCalculatorBinding.inflate(inflater,container,false)

      //  val viewModel=ViewModelProvider(requireActivity()).get(MathViewModel::class.java)

        binding.resultTV.setText("")

        binding.addBT.setOnClickListener {
            binding.expressionET.append("+")
        }

        binding.minusBT.setOnClickListener {
            binding.expressionET.append("-")
        }

        binding.multiplyBT.setOnClickListener {
            binding.expressionET.append("×")

        }

        binding.divideBT.setOnClickListener {
            binding.expressionET.append("÷")
        }

        binding.calculateBT.setOnClickListener {
            if (binding.expectedResult.text.isNotEmpty())
            {
                var text = binding.expressionET.text.toString()
                for (i in text) {
                    if (i == '×') {
                        text = text.replace(i, '*')
                    }
                    if (i == '÷') {
                        text = text.replace(i, '/')
                    }
                }
                val result = ExpressionBuilder(text).build().evaluate()
                binding.resultTV.setText(result.toString())
                val doubleNumber: Double? = binding.resultTV.text.toString().toDoubleOrNull()
                val doubleNumber2: Double? = binding.expectedResult.text.toString().toDoubleOrNull()

                if (doubleNumber==doubleNumber2)
                {
                    binding.descriptionImg.setImageResource(R.drawable.ok_amico)
                    correctAnswers++
                    fragmentListener.onChangeStatsChart(correctAnswers, incorrectAnswers)
                    val operation:HistoryOperation=HistoryOperation(0,text,result,doubleNumber2,true)
                    fragmentListener.onAddTOHistory(operation)
                }
                else
                {
                    binding.descriptionImg.setImageResource(R.drawable.try_again)
                    incorrectAnswers++
                    fragmentListener.onChangeStatsChart(correctAnswers, incorrectAnswers)
                    val operation:HistoryOperation=HistoryOperation(0,text,result,doubleNumber2,false)
                    fragmentListener.onAddTOHistory(operation)
                }
            }
            else
            {
                binding.descriptionImg.setImageResource(R.drawable.q_mark)
            }
        }


        return binding.getRoot();
    }

    companion object
    {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalculatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}