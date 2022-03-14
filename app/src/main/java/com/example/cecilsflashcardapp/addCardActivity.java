package com.example.cecilsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        //back to MainActivity
        findViewById(R.id.flashcard_cancel_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        //save button to save user-input questions
        findViewById(R.id.flashcard_save_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent data=new Intent();
                String inputQuestion=((EditText)findViewById(R.id.flashcard_question_edittext)).getText().toString();
                String inputAnswer=((EditText)findViewById(R.id.flashcard_answer_edittext)).getText().toString();
                data.putExtra("QUESTION_KEY",inputQuestion);
                data.putExtra("ANSWER_KEY",inputAnswer);
                //pass backward to main activity?
                //setResult first argument is RESULT_OK, second is intent
                setResult(RESULT_OK,data);
                finish(); //destroy and go back to MainActivity
            }
        });

    }
}