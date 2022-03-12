
package com.example.cecilsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView questionText=findViewById(R.id.flashcard_question_textview);
        TextView flashcardAnswer=findViewById(R.id.flashcard_answer_textview);
        TextView ans1=findViewById(R.id.answer1_textview);
        TextView ans2=findViewById(R.id.answer2_textview);
        TextView ans3=findViewById(R.id.answer3_textview);
        ImageView toggle=findViewById(R.id.choices_visibility_imageview);

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

        ans1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                findViewById(R.id.answer1_textview).setBackgroundColor(getResources().getColor(R.color.red,null));
            }
        });

        ans2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                findViewById(R.id.answer2_textview).setBackgroundColor(getResources().getColor(R.color.yellow,null));
            }
        });

        ans3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                findViewById(R.id.answer3_textview).setBackgroundColor(getResources().getColor(R.color.green,null));
            }
        });


        toggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ans1.setVisibility(View.INVISIBLE);
                ans2.setVisibility(View.INVISIBLE);
                ans3.setVisibility(View.INVISIBLE);
                ((ImageView)findViewById(R.id.choices_visibility_imageview)).setImageResource(R.drawable.show_icon);
            }
        });



    }
}