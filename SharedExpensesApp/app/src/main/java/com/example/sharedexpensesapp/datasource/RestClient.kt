package com.example.sharedexpensesapp.datasource

import android.util.Log
import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.Group
import com.example.sharedexpensesapp.model.User
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

interface GroupsCallback {
    fun onSuccess(groups: List<Group>)
    fun onFailure(error: String)
}

interface ExpensesCallback {
    fun onSuccess(expenses: List<Expense>)
    fun onFailure(error: String)
}

interface DebtsCallback {
    fun onSuccess(debts: List<Debt>)
    fun onFailure(error: String)
}

interface UsersCallback {
    fun onSuccess(users: List<User>)
    fun onFailure(error: String)
}

class RestClient private constructor(){
    companion object{
        val instance = RestClient()
    }
    private val client = OkHttpClient()
    private val host = "10.0.2.2:3000"
    fun getGroups(callback: GroupsCallback){
        val url = "http://$host/groups"
        Log.d("RestClient", "GET $url")
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) callback.onFailure("Unexpected code $response")
                    //print Headers
                    for ((name, value) in response.headers) {
                        Log.d("RestClient", "$name: $value")
                    }
                    try {
                        val responseBody = response.body
                        val groupsJson = responseBody?.string() ?: ""
                        val groups = Json.decodeFromString<List<Group>>(groupsJson)
                        callback.onSuccess(groups)
                    } catch (e: Exception) {
                        callback.onFailure("Error parsing response: ${e.message}")
                    } finally {
                        response.close()
                    }
                }
            }
        })
    }

    fun getExpenses(callback: ExpensesCallback, groupId: String){
        val url = "http://$host/groups/${groupId}/expenses"
        Log.d("RestClient", "GET $url")
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) callback.onFailure("Unexpected code $response")
                    //print Headers
                    for ((name, value) in response.headers) {
                        Log.d("RestClient", "$name: $value")
                    }
                    try {
                        val responseBody = response.body
                        val expensesJson = responseBody?.string() ?: ""
                        val expenses = Json.decodeFromString<List<Expense>>(expensesJson)
                        callback.onSuccess(expenses)
                    } catch (e: Exception) {
                        callback.onFailure("Error parsing response: ${e.message}")
                    } finally {
                        response.close()
                    }
                }
            }
        })
    }

    fun getDebts(callback: DebtsCallback, groupId: String){
        val url = "http://$host/groups/${groupId}/debts"
        Log.d("RestClient", "GET $url")
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) callback.onFailure("Unexpected code $response")
                    //print Headers
                    for ((name, value) in response.headers) {
                        Log.d("RestClient", "$name: $value")
                    }
                    try {
                        val responseBody = response.body
                        val debtJson = responseBody?.string() ?: ""
                        val debts = Json.decodeFromString<List<Debt>>(debtJson)
                        callback.onSuccess(debts)
                    } catch (e: Exception) {
                        callback.onFailure("Error parsing response: ${e.message}")
                    } finally {
                        response.close()
                    }
                }
            }
        })
    }

    fun getUsers(callback: UsersCallback, groupId: String){
        val url = "http://$host/groups/${groupId}/users"
        Log.d("RestClient", "GET $url")
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) callback.onFailure("Unexpected code $response")
                    //print Headers
                    for ((name, value) in response.headers) {
                        Log.d("RestClient", "$name: $value")
                    }
                    try {
                        val responseBody = response.body
                        val usersJson = responseBody?.string() ?: ""
                        val users = Json.decodeFromString<List<User>>(usersJson)
                        callback.onSuccess(users)
                    } catch (e: Exception) {
                        callback.onFailure("Error parsing response: ${e.message}")
                    } finally {
                        response.close()
                    }
                }
            }
        })
    }

}