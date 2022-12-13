package com.example.factsaboutnumber.screen.infoNumber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.factsaboutnumber.databinding.FragmentInfoNumberBinding

class InfoNumberFragment : Fragment() {

    private lateinit var binding: FragmentInfoNumberBinding

    companion object {
        val TAG = InfoNumberFragment::class.simpleName
        fun newInstance(): InfoNumberFragment {
            return InfoNumberFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoNumberBinding.inflate(inflater, container, false)

        val infoNumberViewModel = ViewModelProvider(this).get(InfoNumberViewModel::class.java)

        val args = this.arguments
        val numberData = args?.getInt("number")
        binding.tvNumber.text = numberData.toString()
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            popBack()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun popBack(){
        parentFragmentManager.popBackStack()
    }
}