package com.gukunov.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gukunov.entity.food.Price
import com.gukunov.entity.food.Time
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface GetTimeRepositoryInterface{
    fun getTime(): Flow<List<Time>>
}
class GetTimeRepository @Inject constructor() : GetTimeRepositoryInterface {
    private val database = FirebaseDatabase.getInstance().reference.child("Time")

    override  fun getTime(): Flow<List<Time>> {
        return callbackFlow {
            val listener = database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val foods = snapshot.children.mapNotNull { it.getValue(Time::class.java) }
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