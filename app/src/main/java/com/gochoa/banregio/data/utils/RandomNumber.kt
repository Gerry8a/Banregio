package com.gochoa.banregio.data.utils

import kotlin.random.Random

object RandomNumber {

    fun randomCvv(): String{
        val random = Random(System.currentTimeMillis())
        val randomNumber = random.nextInt(100, 1000)
        return randomNumber.toString()
    }
}