package com.canerkaya.harcamatakip.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.canerkaya.harcamatakip.Adapter.PaymentsAdapter
import com.canerkaya.harcamatakip.Model.PaymentModel
import com.canerkaya.harcamatakip.Model.TrToUsdModel
import com.canerkaya.harcamatakip.Model.UserModel
import com.canerkaya.harcamatakip.R
import com.canerkaya.harcamatakip.Service.ApiMethods
import com.canerkaya.harcamatakip.Service.RetrofitClient
import com.canerkaya.harcamatakip.Util.CustomSharedPreferences
import com.canerkaya.harcamatakip.ViewModel.HomeViewModel
import com.canerkaya.harcamatakip.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    private var fragmentBinding:FragmentHomeBinding?=null
    private var binding:FragmentHomeBinding?=null
    private lateinit var viewModel:HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        moveFab()
        setClicks()
        observeLiveData()
        checkUser()
        toggleGroupControl()

    }

    fun toggleGroupControl(){
        binding?.buttonGroup?.addOnButtonCheckedListener { group, checkedId, isChecked ->
            when(checkedId){
                binding?.tlButton?.id ->{viewModel.checkedButton.value = "₺"}

                binding?.dolarButton?.id ->{viewModel.checkedButton.value = "$"}

                binding?.euroButton?.id ->{viewModel.checkedButton.value = "€"}

                binding?.sterlinButton?.id ->{viewModel.checkedButton.value = "£"}
            }
        }
    }
    fun checkUser(){
        viewModel.getUser(requireContext())

    }

    fun observeLiveData(){

        viewModel.user.observe(viewLifecycleOwner,{
            when(it.gender){
                "Erkek" ->{binding?.nameTv?.text = getString(R.string.user,it.name,"Bey")}
                "Kadın" ->{binding?.nameTv?.text = getString(R.string.user,it.name,"Hanım")}
                "Diger" ->{binding?.nameTv?.text = getString(R.string.user,it.name,"")}
            }
        })

        viewModel.paymentList.observe(viewLifecycleOwner,{
            val adapter = PaymentsAdapter(it,viewModel.checkedButton.value!!)
            binding?.paymentsRecyclerView?.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        viewModel.checkedButton.observe(viewLifecycleOwner,{
            val adapter = viewModel.paymentList.value?.let { it1 -> PaymentsAdapter(it1,it) }
            binding?.paymentsRecyclerView?.adapter = adapter
            binding?.toplamTv?.setText(getString(R.string.total,viewModel.checkedButtonControl(),it))
            adapter?.notifyDataSetChanged()
        })
    }


    fun setClicks(){
        binding?.addButton?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddPaymentFragment()
            findNavController().navigate(action)
        }
        binding?.cardView?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUserFragment()
            findNavController().navigate(action)
        }
    }

    fun moveFab(){
        binding?.paymentsRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding?.addButton?.visibility == View.VISIBLE) {
                    binding?.addButton?.hide()
                } else if (dy < 0 && binding?.addButton?.visibility != View.VISIBLE) {
                    binding?.addButton?.show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}