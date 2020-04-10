package com.example.wiproassignmentproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wiproassignmentproject.adapter.DemoListAdapter
import com.example.wiproassignmentproject.databinding.ActivityMainBinding
import com.example.wiproassignmentproject.di.DaggerMyAppComponent
import com.example.wiproassignmentproject.factory.MyViewModelfactory
import com.example.wiproassignmentproject.model.DataWrapper
import com.example.wiproassignmentproject.model.ResponseData
import com.example.wiproassignmentproject.model.Rows
import com.example.wiproassignmentproject.util.Utils
import com.example.wiproassignmentproject.viewmodel.MainActivityViewModel
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mbinding:ActivityMainBinding
    private lateinit var myadapter: DemoListAdapter
    lateinit var   rvView: RecyclerView

    private lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var mViewModelFactory: MyViewModelfactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  daggerMyAppComponent = DaggerMyAppComponent.builder().build()
        daggerMyAppComponent.inject(this)
        mbinding =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        rvView = mbinding.mylistview

        viewModel = ViewModelProviders.of(this,mViewModelFactory).get(MainActivityViewModel::class.java)
        if(Utils.isNetworkAvailable(this) ) {


            // getting the data from server by viewmodel using repository
            viewModel.getProjectResponse().observe(this, object : Observer<DataWrapper<ResponseData>> {
                override fun onChanged(it: DataWrapper<ResponseData>) {
                    // showing list
                    if(it.responseData!=null) {
                       var responseData = it.responseData!!
                        if (responseData.rows.isNotEmpty()) {
                            showUi(responseData.rows, responseData.title)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "No item from server.",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }else{
                        Toast.makeText(
                            this@MainActivity,
                            "${it.error}",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            })
        }else{
            Toast.makeText(this@MainActivity,"Network Not Available.",Toast.LENGTH_LONG).show()
        }
    }
    fun showUi(list:List<Rows>,_title:String){
        mbinding.myToolbar.title = _title
        rvView.apply{
            adapter = DemoListAdapter(context,list as ArrayList<Rows>)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
//        myadapter = DemoListAdapter(this,list as ArrayList<Rows>)
//        rvView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        rvView.adapter = myadapter

    }
}
