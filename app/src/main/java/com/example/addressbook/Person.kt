package com.example.addressbook

import android.content.Context
import android.widget.Toast
import java.io.Serializable

class Person (
    val firstName: String,
    val lastName: String,
    val address: String,
    val phoneNumber: String
) : Serializable {
    override fun toString(): String {
        return "$firstName $lastName"
    }

}

class InputPersonValidation(private val context: Context, private val person: Person) {
    fun isValidate(): Boolean {
        if (person.firstName.isEmpty() && person.lastName.isEmpty() && person.address.isEmpty() && person.phoneNumber.isEmpty()) {
            Toast.makeText(context,  context.getString(R.string.filled_in_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.firstName.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.input_name_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.firstName.length !in 2..32) {
            Toast.makeText(context, context.getString(R.string.valid_name_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.lastName.isEmpty()) {
            Toast.makeText(context,
                context.getString(R.string.input_last_name_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.lastName.length !in 2..32) {
            Toast.makeText(context, context.getString(R.string.valid_last_name_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.address.isEmpty()) {
            Toast.makeText(context,
                context.getString(R.string.input_address_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.address.length !in 3..100) {
            Toast.makeText(context,
                context.getString(R.string.address_valid_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.phoneNumber.isEmpty()) {
            Toast.makeText(context,
                context.getString(R.string.phone_number_text), Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.phoneNumber.length !in 10..15) {
            Toast.makeText(context,
                context.getString(R.string.valid_number_text), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}