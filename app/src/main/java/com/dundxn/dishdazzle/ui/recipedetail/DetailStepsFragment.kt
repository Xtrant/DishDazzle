package com.dundxn.dishdazzle.ui.recipedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dundxn.dishdazzle.R
import com.dundxn.dishdazzle.databinding.FragmentDetailStepsBinding

class DetailStepsFragment : Fragment() {
    private var _binding: FragmentDetailStepsBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailStepsBinding.inflate(inflater, container, false)
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val step = arguments?.getString(STEP)

        binding?.tvDetailSteps?.text = step
    }

    companion object {
        const val STEP = "step"
    }

}