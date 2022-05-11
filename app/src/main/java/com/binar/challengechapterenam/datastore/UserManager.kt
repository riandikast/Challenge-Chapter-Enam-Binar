package com.binar.challengechapterenam.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map

class UserManager (context : Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")
    private val loginDataStore : DataStore<Preferences> = context.createDataStore(name = "login_prefs")

    companion object{

        val ID = preferencesKey<String>("USER_ID")
        val EMAIL = preferencesKey<String>("USER_EMAIL")
        val USERNAME = preferencesKey<String>("USER_NAME")
        val NAMALENGKAP = preferencesKey<String>("NAMA_LENGKAP")
        val BIRTH = preferencesKey<String>("USER_BIRTH")
        val ADDRESS = preferencesKey<String>("USER_ADDRESS")
        val LOGIN_STATE = preferencesKey<String>("USER_LOGIN")
    }

    suspend fun saveDataUser(id : String, email:String, username: String, namalengkap: String, birth: String, address : String) {
        dataStore.edit {
            it[ID] = id
            it[USERNAME] = username
            it[EMAIL] = email
            it[NAMALENGKAP] = namalengkap
            it[BIRTH] = birth
            it[ADDRESS] = address


        }
    }

    suspend fun saveDataLogin(login : String) {
        loginDataStore.edit {
            it[LOGIN_STATE] = login


        }
    }

    suspend fun deleteDataLogin(){
        loginDataStore.edit{
            it.clear()

        }
    }
    val userID : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [ID] ?: ""
    }

    val userUsername : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [USERNAME] ?: ""
    }

    val userEmail : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [EMAIL] ?: ""
    }

    val userNamaLengkap : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [NAMALENGKAP] ?: ""
    }

    val userBirth : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [BIRTH] ?: ""
    }

    val userAddress : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [ADDRESS] ?: ""
    }

    val userLogin : kotlinx.coroutines.flow.Flow<String> = loginDataStore.data.map {
        it [LOGIN_STATE] ?: "false"
    }
}