package com.gukunov.features.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentRegBinding

class RegFragment : Fragment() {


    private lateinit var binding: FragmentRegBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegBinding.inflate(layoutInflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signIn.setOnClickListener {
            register()
        }
    }


    fun register(){
        val name = binding.nm.text.toString()
        val email = binding.eml.text.toString()
        val password = binding.paswrd.text.toString()

        // Проверка на пустоту полей
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() ) {
            // Показываем ошибку в EditText
            if (name.isEmpty()) {
                binding.nm.error = "Пожалуйста, заполните поле"
            }
            if (email.isEmpty()) {
                binding.eml.error = "Пожалуйста, заполните поле"
            }
            if (password.isEmpty()) {
                binding.paswrd.error = "Пожалуйста, заполните поле"
            }
            return
        }

        // Проверка валидности имейла
        if (!isValidEmail(email)) {
            binding.eml.error = "Неправильный формат имейла"
            return
        }

        val firebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Успешная регистрация
                Toast.makeText(requireContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Обработка ошибок при регистрации
                Toast.makeText(requireContext(), "Ошибка при регистрации: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}



