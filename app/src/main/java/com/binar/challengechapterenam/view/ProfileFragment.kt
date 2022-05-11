package com.binar.challengechapterenam.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import com.binar.challengechapterenam.R
import com.binar.challengechapterenam.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.logout_dialog.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class ProfileFragment : Fragment() {

    lateinit var viewModel: ViewModelUser
    lateinit var username: String
    lateinit var email : String
    lateinit var idUser : String
    lateinit var userManager : com.binar.challengechapterenam.datastore.UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        userManager = com.binar.challengechapterenam.datastore.UserManager(requireContext())

        userManager.userUsername.asLiveData().observe(requireActivity()){
            view.update1.setText(it)
        }
        userManager.userNamaLengkap.asLiveData().observe(requireActivity()){
            view.update2.setText(it)
        }
        userManager.userBirth.asLiveData().observe(requireActivity()){
            view.update3.setText(it)
        }
        userManager.userAddress.asLiveData().observe(requireActivity()){
            view.update4.setText(it)
        }
        userManager.userEmail.asLiveData().observe(requireActivity()){
            email = it.toString()
        }


        view.btnupdate.setOnClickListener {
            userManager.userID.asLiveData().observe(requireActivity()){
                idUser = it
            }
            username = view.update1.text.toString()
            val cn = view.update2.text.toString()
            val dateofbirth = view.update3.text.toString()
            val address = view.update4.text.toString()

            GlobalScope.launch {
                userManager.saveDataUser(idUser , email, username, cn, dateofbirth, address )
            }

            updateDataUser(idUser.toInt() ,username, cn, dateofbirth, address)

            view.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)

        }
        view.btnlogout.setOnClickListener {
            val custom = LayoutInflater.from(requireContext()).inflate(R.layout.logout_dialog, null)
            val a = AlertDialog.Builder(requireContext())
                .setView(custom)
                .create()

            custom.btnlogouttidak.setOnClickListener {
                a.dismiss()
            }

            custom.btnlogoutya.setOnClickListener {
                GlobalScope.launch {
                    userManager.deleteDataLogin()
                }
                a.dismiss()
                view.findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            a.show()

        }
        return view
    }


    fun updateDataUser(id : Int, username : String, completeName :String, dateofbirth : String, address : String){
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveUserObserver().observe(requireActivity(), Observer {
            if (it  != null){
                Toast.makeText(requireContext(), "Gagal Update Data", Toast.LENGTH_LONG ).show()
            }else{
                Toast.makeText(requireContext(), "Berhasil Update Data", Toast.LENGTH_LONG ).show()

            }

        })
        viewModel.updateDataUser(id, username, completeName, dateofbirth, address)

}
}