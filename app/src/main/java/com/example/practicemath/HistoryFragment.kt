package com.example.practicemath

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicemath.databinding.FragmentHistoryBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val ARG_PARAM1 = "param1"
private val operationsList: MutableList<HistoryOperation> = mutableListOf()
private lateinit var listener: OnFragmentClickListener

class HistoryFragment : Fragment()
{
    private lateinit var operation: HistoryOperation

    private val viewModel: MathViewModel by viewModels {
        MathViewModelFactory((requireActivity().application as MathApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (arguments!=null)
            {
                val jsonString: String? = it.getString(ARG_PARAM1)
                this.operation = jsonString?.let { json ->
                    Json.decodeFromString<HistoryOperation>(json)
                }!!
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentClickListener)
        {
            listener = context
        } else
        {
            throw RuntimeException("$context must implement OnOperationSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentHistoryBinding=
            FragmentHistoryBinding.inflate(inflater,container,false)


        viewModel.getAllOperations().observe(viewLifecycleOwner) { operations ->
            val adapterOperations = AdapterOperation(operations, listener)
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = adapterOperations
        }

        binding.clearAllTV.setOnClickListener(View.OnClickListener {
            listener.onDeleteAllItems()
        })

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(operation:HistoryOperation) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    val jsonString = Json.encodeToString(operation)
                    putString(ARG_PARAM1, jsonString)
                }
            }

        fun addOperationToList(operation:HistoryOperation)
        {
            operationsList.add(0,operation)
        }
    }
}
