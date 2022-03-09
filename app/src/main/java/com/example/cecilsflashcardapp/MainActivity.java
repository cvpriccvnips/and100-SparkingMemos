
package com.example.cecilsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView questionText=findViewById(R.id.flashcard_question_textview);
        TextView flashcardAnswer=findViewById(R.id.flashcard_answer_textview);

        questionText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//              Log.i("pulina","entered onCLick method");
//              Toast.makeText(MainActivity.this,"I click the question",Toast.LENGTH_LONG).show();
                questionText.setVisibility(View.INVISIBLE);
                flashcardAnswer.setVisibility(View.VISIBLE);
            }
        });

        flashcardAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                flashcardAnswer.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.VISIBLE);
            }
        });
    }
}