package com.example.practicemath

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicemath.databinding.FragmentStatsBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class StatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var correctAnswers: Int =1
    private var incorrectAnswers: Int=1

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            correctAnswers = it.getInt(ARG_PARAM1)
            incorrectAnswers = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pieChart: PieChart = binding.pieChart

        val entries = listOf(
            PieEntry(correctAnswers.toFloat(), "Correct"),
            PieEntry(incorrectAnswers.toFloat(), "Incorrect")
        )

        val dataSet = PieDataSet(entries, "Results").apply {
            colors = listOf(Color.rgb(5, 115, 206), Color.rgb(203, 203, 203))
            valueTextColor = Color.BLACK
            valueTextSize = 16f
        }

        val data = PieData(dataSet)

        pieChart.apply {
            this.data = data
            description.isEnabled = false
            centerText = "Results"
            setEntryLabelColor(Color.BLACK)
            setEntryLabelTextSize(16f)
            invalidate() // refresh
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        @JvmStatic
        fun newInstance(correctAnswers: Int, incorrectAnswers: Int) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, correctAnswers)
                    putInt(ARG_PARAM2, incorrectAnswers)
                }
            }
    }
}