package com.gukunov.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Food
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface SearchRepositoryInterface {
    fun searchFoods(query: String): Flow<List<Food>>
}

class SearchRepository @Inject constructor() : SearchRepositoryInterface {
    private val firebaseDatabase = FirebaseDatabase.getInstance().reference

    override fun searchFoods(query: String): Flow<List<Food>> {
        return callbackFlow {
            val listener = firebaseDatabase.child("Foods")
                .orderByChild("Title")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val foods = snapshot.children.mapNotNull { it.getValue(Food::class.java) }
                            .filter { food -> food.Title!!.contains(query, ignoreCase = true) }
                        trySend(foods).isSuccess
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })

            awaitClose { firebaseDatabase.removeEventListener(listener) }
        }
    }
}

