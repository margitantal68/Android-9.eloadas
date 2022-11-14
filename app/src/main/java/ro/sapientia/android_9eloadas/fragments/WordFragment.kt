package ro.sapientia.android_9eloadas.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ro.sapientia.android_9eloadas.R
import ro.sapientia.android_9eloadas.adapter.WordAdapter
import ro.sapientia.android_9eloadas.viewmodel.WordViewModel


class WordFragment : Fragment() {
    private lateinit var viewModel: WordViewModel

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WordViewModel(context) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory(this.requireContext())
        viewModel = ViewModelProvider(this, factory).get(WordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler_view: RecyclerView = view.findViewById(R.id.word_recycler_view)
        val adapter = WordAdapter( viewModel.liveData.value!! )
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        // Horizontal line between items
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.contentChange.observe(this.viewLifecycleOwner, Observer{
//            Log.i("XXX - WordFragment", viewModel.liveData.value!![0])
            adapter.notifyDataSetChanged()
        })

    }
}