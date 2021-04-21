package com.canerkaya.harcamatakip.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.canerkaya.harcamatakip.R
import com.canerkaya.harcamatakip.databinding.FragmentAddPaymentBinding


class AddPaymentFragment : Fragment() {

    private var fragmentBinding:FragmentAddPaymentBinding?=null
    private var binding:FragmentAddPaymentBinding?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPaymentBinding.bind(view)
        fragmentBinding = binding
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding =null
    }
}