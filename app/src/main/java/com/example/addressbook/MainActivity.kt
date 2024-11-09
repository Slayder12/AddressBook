package com.example.addressbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(){

    private val userData: MutableList<Person> = mutableListOf()
    private var adapter: ArrayAdapter<Person>? = null

    private lateinit var toolbarMain: Toolbar
    private lateinit var listViewLV: ListView
    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var addressET: EditText
    private lateinit var phoneNumberET: EditText
    private lateinit var saveBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)
        title = ""
        setSupportActionBar(toolbarMain)

        firstNameET = findViewById(R.id.firstNameET)
        lastNameET = findViewById(R.id.lastNameET)
        addressET = findViewById(R.id.addressNameET)
        phoneNumberET = findViewById(R.id.phoneNumberET)
        saveBTN = findViewById(R.id.saveBTN)
        listViewLV = findViewById(R.id.listViewLV)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userData)
        listViewLV.adapter = adapter

        saveBTN.setOnClickListener {
            val person = Person(
                firstNameET.text.toString(),
                lastNameET.text.toString(),
                addressET.text.toString(),
                phoneNumberET.text.toString()
            )

            if (!InputPersonValidation(this, person).isValidate()) return@setOnClickListener
            userData.add(person)
            firstNameET.text.clear()
            lastNameET.text.clear()
            addressET.text.clear()
            phoneNumberET.text.clear()

            adapter!!.notifyDataSetChanged()
            Toast.makeText(this, getString(R.string.user_add_text, person.firstName), Toast.LENGTH_SHORT).show()
        }

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val userPosition = adapter!!.getItem(position)
                val intent = Intent(this, UserInfoActivity::class.java)
                intent.putExtra(Person::class.java.name, userPosition)
                startActivity(intent)

            }

        listViewLV.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { _, _, position, _ ->
                showDeleteDialog(position)
                true
            }

    }

    private fun showDeleteDialog(position: Int) {
        val user = adapter!!.getItem(position)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.delete_person_text))
            .setIcon(R.drawable.ic_delete)
            .setMessage(getString(R.string.do_you_want_delete_person_text, user))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            userData.removeAt(position)
            adapter?.notifyDataSetChanged()
                Toast.makeText(this,
                    getString(R.string.person_deleted_text, user), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenuMain) {
            Toast.makeText(this, getString(R.string.logged_text), Toast.LENGTH_SHORT).show()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}