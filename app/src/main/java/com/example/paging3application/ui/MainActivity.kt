package com.example.paging3application.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3application.R
import com.example.paging3application.controller.CharacterAdapter
import com.example.paging3application.controller.CharacterItemInteraction
import com.example.paging3application.models.CharacterData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , CharacterItemInteraction {


    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initRecyclerView()
        getData()

    }



    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            characterAdapter.setListener(this@MainActivity)
            adapter = characterAdapter

            characterAdapter.addLoadStateListener { loadState ->
                // show empty list
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                    progressDialog.isVisible = true
                else {
                    progressDialog.isVisible = false
                    // If we have an error, show a toast
                    val errorState = when {
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        Toast.makeText(this@MainActivity , it.error.stackTraceToString() , Toast.LENGTH_SHORT).show()
                    }
                }
            }



        }
    }


    private fun getData() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    override fun characterItemOnclick(model: CharacterData) {

        Toast.makeText(this@MainActivity,model.name+" clicked",Toast.LENGTH_SHORT).show()
    }


}