package com.gukunov.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Price
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetPriceRepositoryInterface{
     fun getPrice(): Flow<List<Price>>
}

class GetPriceRepository@Inject constructor() : GetPriceRepositoryInterface {
    private val database = FirebaseDatabase.getInstance().reference.child("Price")

    override  fun getPrice(): Flow<List<Price>> {
        return callbackFlow {
            val listener = database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val foods = snapshot.children.mapNotNull { it.getValue(Price::class.java) }
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