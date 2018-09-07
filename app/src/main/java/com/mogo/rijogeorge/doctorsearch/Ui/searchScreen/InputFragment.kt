package com.mogo.rijogeorge.doctorsearch.Ui.searchScreen


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mogo.rijogeorge.doctorsearch.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val INPUT_TYPE = "param1"

class InputFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(INPUT_TYPE)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance(location: String) =
                InputFragment().apply {
                    arguments = Bundle().apply {
                        putString(INPUT_TYPE, location)
                    }
                }
    }
}
