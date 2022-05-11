package com.binar.challengechapterenam.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.binar.challengechapterenam.R
import com.binar.challengechapterlima.model.GetAllFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment : Fragment() {
    lateinit var favorite : String
    lateinit var alreadyfavorite : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val getfilm = arguments?.getParcelable<GetAllFilmItem>("detailfilm")

        view.text1.text = getfilm?.title
        view.text2.text = getfilm?.director
        view.text3.text = getfilm?.createdAt
        view.text4.text = getfilm?.synopsis

        Glide.with(requireContext()).load(getfilm?.image).into(view.gambar1)
        favorite = "false"


        view.btnfavorite.setOnClickListener {
            for (data in favorite){
                if (favorite == "true"){
                    btnfavorite.setImageResource(R.drawable.unlove)
                    favorite = "false"
                    alreadyfavorite = "true"
                    break
                }


                if (favorite == "false"){
                    btnfavorite.setImageResource(R.drawable.love)
                    favorite = "true"
                    alreadyfavorite = "false"
                    break
                }
            }






        }
        return view
    }

}