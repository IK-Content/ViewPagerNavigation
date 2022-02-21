package com.jasontsh.interviewkickstart.viewpagernavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.common.collect.ImmutableList

private const val ARG_PARAM1 = "steps left"
private const val ARG_PARAM2 = "position"
/**
 * A simple [Fragment] subclass.
 * Use the [DestinationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DestinationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.destination_text)
        val count = arguments?.getInt(ARG_PARAM1) ?: 1
        val position = arguments?.getInt(ARG_PARAM2) ?: 0
        textView.text = "destination with position $position and count $count"
        val button = view.findViewById<Button>(R.id.destination_button)
        button.text = "To next step"
        button.setOnClickListener {
            val viewModel: ViewPagerViewModel by activityViewModels()
            viewModel.stepList.value =
                ImmutableList.copyOf(viewModel.stepList.value?.mapIndexed { index, item ->
                    if (index + 1 == position) item - 1 else item
                } ?: ImmutableList.of())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param steps Parameter 1.
         * @return A new instance of fragment DestinationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(steps: Int, position: Int) =
            DestinationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, steps)
                    putInt(ARG_PARAM2, position)
                }
            }
    }
}