package edu.washington.jonl1138.lifecounter

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewParent
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val status = findViewById<TextView>(R.id.status)
        for (i in 1..4) {
            for (j in 1..4) {
                var modifier = "1"
                if (j == 2) {
                    modifier = "-1"
                } else if (j == 3) {
                    modifier = "5"
                } else if (j == 4){
                    modifier = "-5"
                }
                val currentID = "p" + i.toString() + modifier
                val resID = resources.getIdentifier(currentID, "id", "edu.washington.jonl1138.lifecounter")
                val currentButton = findViewById<Button>(resID)

                currentButton.setOnClickListener {
                    val currentPlayer = currentID[1].toString()
                    val increment: String
                    if (currentID.length == 3) {
                        increment = currentID[2].toString()
                    } else {
                        increment = currentID.substring(2,4)
                    }
                    val playerValue:String = "p" + currentPlayer + "_lives"
                    val matchingResID = resources.getIdentifier(playerValue, "id", "edu.washington.jonl1138.lifecounter")
                    val matchingTextView = findViewById<TextView>(matchingResID)
                    val oldText:String = matchingTextView.text.toString()
                    val newValue:Int = Integer.parseInt(oldText.substring(oldText.indexOf(' ') + 1, oldText.length)) + Integer.parseInt(increment)
                    matchingTextView.text = "Lives: " + newValue.toString()

                    Log.d("debugging", "reached here")
                    if (newValue <= 0 ) {
                        Log.d("debugging", "below zero!")
                        status.text = "Player " + currentPlayer + " LOSES!"
                        status.setVisibility(View.VISIBLE)
                    }

                }

            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}





