package com.example.addressbook

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserInfoActivity : AppCompatActivity() {

    private lateinit var firstNameTV: TextView
    private lateinit var lastNameTV: TextView
    private lateinit var addressTV: TextView
    private lateinit var phoneTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        firstNameTV = findViewById(R.id.firstNameTV)
        lastNameTV = findViewById(R.id.lastNameTV)
        addressTV = findViewById(R.id.addressTV)
        phoneTV = findViewById(R.id.phoneTV)


        val person = intent.extras?.getSerializable(Person::class.java.name) as? Person
        firstNameTV.text = "Имя: ${person?.firstName}"
        lastNameTV.text = "Фамилия: ${person?.lastName}"
        addressTV.text = "Адресс: ${person?.address}"
        phoneTV.text = "Тел.: ${person?.phoneNumber}"

    }

}