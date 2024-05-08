package com.gukunov.features.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentLogBinding
import com.gukunov.features.databinding.FragmentRegBinding


class LogFragment : Fragment() {

    private lateinit var signInRequest: BeginSignInRequest

    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private lateinit var mGoogleSignInClient : GoogleSignInClient

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.signIn.setOnClickListener {
            login()
        }
        binding.googleBtn.setOnClickListener {
            loginGoogle()
        }

    }


    fun login(){
        val firebaseAuth = Firebase.auth
        val email = binding.eml.text.toString()
        val password = binding.paswrd.text.toString()

        if (email.isEmpty() || password.isEmpty()  ) {

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
            Toast.makeText(requireContext(), "Неправильный формат имейла", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val action = LogFragmentDirections.actionLogFragmentToMainFragment()
                findNavController().navigate(action)
            }
            .addOnFailureListener { e ->
                // Обработка ошибок при входе
                Toast.makeText(requireContext(), "Error! ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Функция для проверки валидности имейла
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun open(){
        val action = LogFragmentDirections.actionLogFragmentToMainFragment()
        findNavController().navigate(action)
    }




    fun loginGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(com.gukunov.common.R.string.google_service))
            .requestIdToken(getString(com.gukunov.common.R.string.google_service))
            .requestEmail().build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(com.gukunov.common.R.string.google_service))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()

        val signInIntent = mGoogleSignInClient.signInIntent
        googleLauncher.launch(signInIntent)
    }

    private val googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        result.data?.let { data ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val acct = task.getResult(ApiException::class.java)
                val authCode = acct?.serverAuthCode
                val idToken = acct?.idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                Firebase.auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d("TAG", "signInWithCredential:success")
//                            val user = Firebase.auth.currentUser
//                            //updateUI(user)
//                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            val action = LogFragmentDirections.actionLogFragmentToMainFragment()
                            findNavController().navigate(action)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.exception)
                            //updateUI(null)
                        }
                    }
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                Log.w("TAG", "signInResult:failed code=" + e.statusCode)
                //updateUI(null)
            }
        }
    }

}