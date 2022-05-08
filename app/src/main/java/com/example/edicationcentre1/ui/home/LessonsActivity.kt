package com.example.edicationcentre1.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.edicationcentre1.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class LessonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        val position = getIntent().getIntExtra("position", 0)
        val titleString = getIntent().getIntExtra("name", R.string.kurs1)
        val image = getIntent().getIntExtra("image", R.drawable.matem)

        val imageView = findViewById<ImageView>(R.id.placeImage)
        val title = findViewById<TextView>(R.id.title_lesson)
        val textAbout = findViewById<TextView>(R.id.textViewAbout)
        val button = findViewById<Button>(R.id.button)
        val progress = findViewById<ProgressBar>(R.id.progress)
        progress.setVisibility(View.INVISIBLE)
        imageView.setImageResource(image)
        title.setText(titleString)

        Log.i("TAG", titleString.toString())

        when(position){
            0-> {textAbout.setText(R.string.text_course1)
            }
            1-> {
                textAbout.setText(R.string.text_course2)
            }
            2-> {
                textAbout.setText(R.string.text_course3)
            }
            3-> {
                textAbout.setText(R.string.text_course4)
            }
            4-> {
                textAbout.setText(R.string.text_course5)
            }

        }

        button.setOnClickListener{

            progress.setVisibility(View.VISIBLE)
            button.setVisibility(View.INVISIBLE)
            val mAuth = FirebaseAuth.getInstance();
            val currentUser: FirebaseUser? =  mAuth.getCurrentUser()
            val userName: String? = currentUser?.displayName
            val userEmail:String? = currentUser?.email
            val db = FirebaseFirestore.getInstance()
            val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
            val currentDateandTime: String = sdf.format(Date())
            val lessonName = resources.getString(titleString)
            val dbConference = db.collection("Lessons")
            val data = LessonItems(lessonName, userName.toString(), userEmail.toString(), currentDateandTime)
            dbConference.add(data).addOnSuccessListener {
                Toast.makeText(applicationContext, "Заявка отправлено!", Toast.LENGTH_SHORT).show()
                progress.setVisibility(View.INVISIBLE)
                button.setVisibility(View.VISIBLE)
            }.addOnFailureListener { e ->
                progress.setVisibility(View.INVISIBLE)
                button.setVisibility(View.VISIBLE)
                Toast.makeText(applicationContext,"Fail to add course \n$e", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

