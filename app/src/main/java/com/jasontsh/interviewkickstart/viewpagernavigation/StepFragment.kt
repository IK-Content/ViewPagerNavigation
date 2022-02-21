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

class StepFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.step_text)
        val count = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        textView.text = "This is fragment $count"
        val button: Button = view.findViewById(R.id.step_button)
        button.text = "Go to fragment with $count number of steps"
        button.setOnClickListener {
            val viewModel: ViewPagerViewModel by activityViewModels()
            viewModel.currentPosition.value = count - 1
            viewModel.stepList.value =
                ImmutableList.copyOf(viewModel.stepList.value?.mapIndexed { index, item ->
                    if (index + 1 == count) count else item
                } ?: ImmutableList.of())
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): StepFragment {
            return StepFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}