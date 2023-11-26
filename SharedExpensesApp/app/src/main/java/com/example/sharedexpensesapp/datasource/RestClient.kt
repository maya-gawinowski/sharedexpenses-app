package com.example.sharedexpensesapp.datasource

import android.util.Log
import com.example.sharedexpensesapp.model.Debt
import com.example.sharedexpensesapp.model.Expense
import com.example.sharedexpensesapp.model.Group
import com.example.sharedexpensesapp.model.User
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class RestClient private constructor(){
    companion object{
        val instance = RestClient()
    }
    private val client = OkHttpClient()
    private val host = "10.0.2.2:3000"
    private val mediaType = "application/json; charset=utf-8".toMediaType()
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

                    try {
                        val groups = Json.decodeFromString<List<Group>>(response.body?.string() ?: "")
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
                    try {
                        val expenses = Json.decodeFromString<List<Expense>>(response.body?.string() ?: "")
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
                    try {
                        val debts = Json.decodeFromString<List<Debt>>(response.body?.string() ?: "")
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
                    try {
                        val users = Json.decodeFromString<List<User>>(response.body?.string() ?: "")
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
    fun createGroups(userIds: List<String>, name: String, currency: String, description: String=""){
        val url = "http://$host/groups"
        Log.d("RestClient", "POST $url")
        val idsStringify= userIds.joinToString(separator = "\",\"", prefix="[\"", postfix = "\"]")
        val body = """{"userIds":${idsStringify},"name":"$name", "currency":"$currency","description":"$description"}"""
        val request = Request.Builder()
            .url(url)
            .post(body.toRequestBody(mediaType))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) Log.d("RestClient","Unexpected code $response")
                    Log.d("RestClient", "Response POST group: ${response.body?.string()}")
                }
            }
        })
    }
    fun addExpense(groupId: String, payerId:String,participantsIds:List<String>, amount:Double, date:String, description: String=""){
        val url = "http://$host/groups/$groupId/expenses"
        val idsStringify= participantsIds.joinToString(separator = "\",\"", prefix="[\"", postfix = "\"]")
        val body = """{"payerId":"$payerId", "participantsIds":$idsStringify, "amount":$amount, "date":"$date", "description":"$description"}"""
        Log.d("RestClient", "POST $url")
        val request = Request.Builder()
            .url(url)
            .post(body.toRequestBody(mediaType))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) Log.d("RestClient","Unexpected code $response")
                    Log.d("RestClient", "Response POST add expenses: ${response.body?.string()}")
                }
            }
        })
    }
    fun deleteGroup(groupId:String){
        val url = "http://$host/groups/$groupId"
        val request = Request.Builder()
            .url(url)
            .delete()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) Log.d("RestClient","Unexpected code $response")
                    Log.d("RestClient", "Response DELETE group: ${response.body?.string()}")
                }
            }
        })
    }
    fun addUsersToGroup(groupId: String, userIds: List<String>){
        val url = "http://$host/groups/$groupId"
        Log.d("RestClient", "PUT $url")
        val idsStringify= userIds.joinToString(separator = "\",\"", prefix="[\"", postfix = "\"]")
        val body = """{"addUserIds":$idsStringify}"""
        val request = Request.Builder()
            .url(url)
            .put(body.toRequestBody(mediaType))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) Log.d("RestClient", "Unexpected code $response")
                    Log.d("RestClient", "Response PUT add User: ${response.body?.string()}")
                }
            }
        })
    }
    fun removeUsersFromGroup(groupId: String, userIds: List<String>){
        val url = "http://$host/groups/$groupId"
        Log.d("RestClient", "PUT $url")
        val idsStringify= userIds.joinToString(separator = "\",\"", prefix="[\"", postfix = "\"]")
        val body = """{"removeUserIds":$idsStringify}"""
        val request = Request.Builder()
            .url(url)
            .put(body.toRequestBody(mediaType))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) Log.d("RestClient", "Unexpected code $response")
                    Log.d("RestClient", "Response PUT remove User: ${response.body?.string()}")
                }
            }
        })
    }
    fun getUserIdByName(username: String, callback: UserIdCallback) {
        val url = "http://$host/users/$username"
        Log.d("RestClient", "GET $url")
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback.onFailure(e.message ?: "Unknown error")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        callback.onFailure("Unexpected code $response")
                        return
                    }

                    try {
                        val user = Json.decodeFromString<User>(response.body?.string() ?: "")
                        callback.onSuccess(user.id)
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



