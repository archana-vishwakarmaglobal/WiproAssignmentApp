package com.example.wiproassignmentproject.model
import androidx.lifecycle.MutableLiveData

import com.example.wiproassignmentproject.service.APIService
import com.example.wiproassignmentproject.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ListRepository @Inject constructor(){

    //private val mutableLiveResponse: MutableLiveData<ResponseData> = MutableLiveData()
    private val mutableLiveResponse: CustomMutableLiveData<DataWrapper<ResponseData>> = CustomMutableLiveData()
    private val dataWrapper:DataWrapper<ResponseData> = DataWrapper()

     fun getMutableLiveData():CustomMutableLiveData<DataWrapper<ResponseData>> {
         val apiRes: APIService = RetrofitInstance.service
         val call: Call<ResponseData> = apiRes.getList()
         call?.enqueue(object : Callback<ResponseData?> {
             override fun onFailure(call: Call<ResponseData?>, t: Throwable?) {
                 if (t is Exception) {
                     handleException(t)
                 } else {
                     //do something else
                     dataWrapper.error = "unhandled"
                     mutableLiveResponse.setValue(dataWrapper);
                 }
             }

             override fun onResponse(
                 call: Call<ResponseData?>,
                 response: Response<ResponseData?>
             ) {
                 val apiResponse: ResponseData? = response.body()
                 if (apiResponse != null) {
                     dataWrapper.responseData = apiResponse
                     mutableLiveResponse.value = dataWrapper

                 }else{
                     handleError(response);
                 }

             }
         })

         return mutableLiveResponse
     }

    private fun handleError(response: Response<ResponseData?>) {
        dataWrapper.error = "Error"
        mutableLiveResponse.setValue(dataWrapper);
    }

    private fun handleException(t: Exception?) {
        dataWrapper.error = t.toString()
        mutableLiveResponse.setValue(dataWrapper);
    }
}