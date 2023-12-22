package com.example.smartgarden.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartgarden.databinding.FragmentHomeBinding
import com.example.smartgarden.databinding.FragmentMyplantBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        // Ambil UID pengguna saat ini
        if (userId != null) {
            val userReference = FirebaseDatabase.getInstance().reference.child("users").child(userId)
            userReference?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Parsing data pengguna menjadi objek UserModel
                    val userName = snapshot.child("name").getValue(String::class.java)
                    // Tampilkan data pengguna ke XML menggunakan data binding
                    binding.apply {
                        // Pastikan user tidak null sebelum mengakses propertinya
                        userName?.let {
                            // Setel teks nama dan alamat ke TextView di XML
                            tvUsernameHome.text = it
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("ProfileFragment", "Gagal membaca data pengguna", error.toException())
                }
            })
        } else {
            Log.e("ProfileFragment", "userId kosong")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}