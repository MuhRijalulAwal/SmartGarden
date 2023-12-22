package com.example.smartgarden.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartgarden.R
import com.example.smartgarden.databinding.FragmentProfileBinding
import com.example.smartgarden.dummy.user
import com.example.smartgarden.ui.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//class ProfileFragment : Fragment() {
//    private lateinit var binding: FragmentProfileBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentProfileBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
////        val args = arguments
////        if (args != null) {
////            val username = args.getString("userName", "")
////            val email = args.getString("userEmail", "")
////
////            binding.tvUsernameProfile.text = username
////            binding.tvEmailProfile.text = email
////        } else {
////            // Handle the case where arguments are null
////        }
////
////        val userId = FirebaseAuth.getInstance().currentUser?.uid
////        if (userId != null) {
////            val userReference = FirebaseDatabase.getInstance().reference.child("users").child(userId)
////            userReference.addValueEventListener(object : ValueEventListener {
////                override fun onDataChange(snapshot: DataSnapshot) {
////                    val userName = snapshot.child("name").getValue(String::class.java)
////                    val userEmail = snapshot.child("email").getValue(String::class.java)
////                    binding.tvUsernameProfile.text = userName
////                    binding.tvEmailProfile.text = userEmail
////                }
////
////                override fun onCancelled(error: DatabaseError) {
////                    // Handle database error
////                    Log.e("ProfileFragment", "Failed to read user data", error.toException())
////                }
////            })
////        }
//    }
//}

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        // Ambil UID pengguna saat ini
        if (userId != null) {
            val userReference = FirebaseDatabase.getInstance().reference.child("users").child(userId)
            userReference?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Parsing data pengguna menjadi objek UserModel
                    val userName = snapshot.child("name").getValue(String::class.java)
                    val userAddress = snapshot.child("address").getValue(String::class.java)

                    // Tampilkan data pengguna ke XML menggunakan data binding
                    binding.apply {
                        // Pastikan user tidak null sebelum mengakses propertinya
                        userName?.let {
                            // Setel teks nama dan alamat ke TextView di XML
                            tvUsernameProfile.text = it
                        }
                        userAddress?.let {
                            // Setel teks alamat ke TextView di XML
                            tvEmailProfile.text = it
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

        binding.btnLogOut.setOnClickListener {
            // Panggil fungsi logout
            onLogoutClick(view)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun onLogoutClick(view: View) {
        // Panggil metode logout() atau kode logout yang Anda inginkan di sini
        logout()
    }

    fun logout() {
        // Hapus sesi login
        clearLoginSession()

        // Intent ke WelcomeActivity dari dalam fragment
        val intent = Intent(requireContext(),WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun clearLoginSession() {
        val sharedPreferences = requireContext().getSharedPreferences("LoginPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}