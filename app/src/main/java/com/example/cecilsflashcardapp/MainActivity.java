
package com.example.cecilsflashcardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView questionTextView;
    TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView=findViewById(R.id.flashcard_question_textview);
        answerTextView=findViewById(R.id.flashcard_answer_textview);
        TextView ans1=findViewById(R.id.answer1_textview);
        TextView ans2=findViewById(R.id.answer2_textview);
        TextView ans3=findViewById(R.id.answer3_textview);



        questionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("paulina","entered onCLick method");
                Toast.makeText(MainActivity.this,"I click the question",Toast.LENGTH_LONG).show();
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                answerTextView.setVisibility(View.INVISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
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

        final boolean[] shouldShowAnswers = {true};
        ImageView eyeVisible=findViewById(R.id.eye_visible_icon);
        eyeVisible.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (shouldShowAnswers[0]) {
                    eyeVisible.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_eye_invisible));
                    ans1.setVisibility(View.VISIBLE);
                    ans2.setVisibility(View.VISIBLE);
                    ans3.setVisibility(View.VISIBLE);
                    shouldShowAnswers[0] = false;
                } else {
                    eyeVisible.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_eye_visible));
                    ans1.setVisibility(View.INVISIBLE);
                    ans2.setVisibility(View.INVISIBLE);
                    ans3.setVisibility(View.INVISIBLE);
                    shouldShowAnswers[0] = true;
                }
            }
        });

        ImageView addButton=findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to addCardActivity
                Intent intent=new Intent(MainActivity.this, addCardActivity.class);
//                startActivity(intent);
//                intent.putExtra("QUESTION_KEY","Enter your question");
//                intent.putExtra("ANSWER_KEY","Enter your answer");
                //use request code
                startActivityForResult(intent,100);
            }
        });

        ImageView editButton=findViewById(R.id.flashcard_edit_button);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //go to addCardActivity
                Intent intentEdit=new Intent(MainActivity.this, addCardActivity.class);
                intentEdit.putExtra("QUESTION_KEY",((TextView) findViewById(R.id.flashcard_question_textview)).getText().toString());
                intentEdit.putExtra("ANSWER_KEY",((TextView) findViewById(R.id.flashcard_answer_textview)).getText().toString());
                startActivityForResult(intentEdit,100);
            }
        });

    }

    //retrieve data addCardActivity gives, based on requestCode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            //get data
            if (data!=null){ //check if there is an intent
                String questionString=data.getExtras().getString("QUESTION_KEY");
                String answerString=data.getExtras().getString("ANSWER_KEY");
                questionTextView.setText(questionString);
                answerTextView.setText(answerString);
            }
        }
    }
}