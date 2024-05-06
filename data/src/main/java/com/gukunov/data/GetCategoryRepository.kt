package com.gukunov.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Food
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface CategoryRepositoryInterface {
    fun getCategory(): Flow<List<Category>>
}

class GetCategoryRepository@Inject constructor() : CategoryRepositoryInterface {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Category")

    override fun getCategory(): Flow<List<Category>> {
        return callbackFlow {
            val listener = database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val foods = snapshot.children.mapNotNull { it.getValue(Category::class.java) }
                    trySend(foods).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
            awaitClose { database.removeEventListener(listener) }
        }
    }
}