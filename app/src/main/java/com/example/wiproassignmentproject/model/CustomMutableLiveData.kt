package com.example.wiproassignmentproject.model

import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class CustomMutableLiveData<LiveDataModel> : MutableLiveData<LiveDataModel>() {
    private var liveDataModel :LiveDataModel? =null
    private var exception: Exception?=null
    fun setData(liveDataModel :LiveDataModel?){
         this.liveDataModel = liveDataModel
    }
    fun setApiException(exception: Exception){
        this.exception = exception
    }
}