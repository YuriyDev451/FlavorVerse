package com.gukunov.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gukunov.entity.food.Food
import com.gukunov.network.di.NetworkLayerModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface FoodRepositoryInterface {
    fun getFoods(): Flow<List<Food>>
}

class GetFoodRepository @Inject constructor() : FoodRepositoryInterface {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Foods")

    override fun getFoods(): Flow<List<Food>> {

        return callbackFlow {
            val listener = database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val foods = snapshot.children.mapNotNull { it.getValue(Food::class.java) }
                    trySend(foods).isSuccess
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
            awaitClose { database.removeEventListener(listener) }
        }

    }


//    override fun getFoods(): Flow<List<Food>> = callbackFlow {
//        val eventListener = object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val teamList = mutableListOf<Food>()
//                for (childSnapshot in snapshot.children) {
//                    println("DataSnapshot: ${childSnapshot.value}")
//
//                    try {
//                        val teams = childSnapshot.getValue(Food::class.java)
//                        if (teams != null) {
//                            teamList.add(teams)
//                        } else {
//                            println("Null Teams object found")
//                        }
//                    } catch (e: Exception) {
//                        println("Error converting DataSnapshot to Teams: ${e.message}")
//                    }
//                }
//                trySend(teamList).isSuccess
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                close(Exception(error.message))
//            }
//        }
//
//        database.addValueEventListener(eventListener)
//
//        awaitClose {
//            database.removeEventListener(eventListener)
//        }
//    }
}