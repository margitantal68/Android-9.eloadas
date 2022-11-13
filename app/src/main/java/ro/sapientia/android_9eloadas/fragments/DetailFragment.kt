package ro.sapientia.android_9eloadas.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ro.sapientia.android_9eloadas.R
import ro.sapientia.android_9eloadas.viewmodel.SharedViewModel

class DetailFragment : Fragment() {

    // Fragment's ViewModel
    // val sharedViewModel: SharedViewModel by viewModels()

    // Activity's ViewModel - shared between activity's fragments
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView: ImageView = view.findViewById(R.id.imageViewDetail)
        val editText1: EditText = view.findViewById(R.id.editTextDetail1)
        val editText2: EditText = view.findViewById(R.id.editTextDetail2)
        val button: Button = view.findViewById(R.id.buttonDetail)
        // 1. valtozat
        // val position = sharedViewModel.position
        // 2. valtozat
        val position = sharedViewModel.position.value!!
        //
        val list = sharedViewModel.list

        editText1.setText(list[position].text1)
        editText2.setText(list[position].text2)
        imageView.setImageResource(list[position].imageResource)

        button.setOnClickListener{
            list[position].text1 = editText1.text.toString()
            list[position].text2 = editText2.text.toString()
            findNavController().navigate(R.id.action_detailFragment_to_listFragment)
        }
    }
}