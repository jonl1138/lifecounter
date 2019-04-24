package edu.washington.jonl1138.lifecounter

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*

import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        for (i in 1..4) {
            for (j in 1..4) {
                var modifier:String = "1"
                if (j == 2) {
                    modifier = "-1"
                } else if (j == 3) {
                    modifier = "5"
                } else if (j == 4){
                    modifier = "-5"
                }



                val currentID = "p" + i.toString() + modifier


                val resID = resources.getIdentifier(currentID, "id", "edu.washington.jonl1138.lifecounter")
                Log.i("debugging", currentID)
                Log.i("debugging", resID.toString())

                val currentButton = findViewById<Button>(resID)
                Log.i("debugging", currentButton.toString())
                currentButton.setOnClickListener { CustomClickListener(currentID) }

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


class CustomClickListener(val id: String): View.OnClickListener {

    var player: String = ""
    var increment: String = ""

    init {
        player = id[1].toString()
        if (id.length == 3) {
            increment = id[2].toString()
        } else {
            increment = id.substring(2,4)
        }
    }
    override fun onClick(v: View) {
        Log.i("debugging", "clicked")
        val playerValue:String = "p" + player + "_lives"
        val resID = v.resources.getIdentifier(playerValue, "id", "edu.washington.jonl1138.lifecounter")
        val p1 = v.findViewById<TextView>(resID)
        val oldText:String = p1.text.toString()
        val newValue:Int = Integer.parseInt(oldText.substring(oldText.length-1,oldText.length)) + Integer.parseInt(increment)
        p1.text = "Lives: " + newValue.toString()
    }
}

