
package com.example.cecilsflashcardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView questionTextView;
    TextView answerTextView;

    //global class variable, access database
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    //keep track of current index
    int cardIndex=0;

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



// get the center for the clipping circle
                int cx = answerTextView.getWidth() / 2;
                int cy = answerTextView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerTextView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
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
                //first parameter is enter animation, second is exit animation
                overridePendingTransition(R.anim.right_in,R.anim.left_out);
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
        //takes in context as arg, context is where the database summoned from?
        //summonned from main activity, use this, or getApplicationContext
        flashcardDatabase=new FlashcardDatabase(getApplicationContext());
        //get the most up-to-date list of flashcards
        allFlashcards=flashcardDatabase.getAllCards();

        if(allFlashcards!=null&&allFlashcards.size()>0) { //not null or empty
            //GET first flashcard, and set it.
            Flashcard firstCard = allFlashcards.get(0);
            //pass in the question, and set it
            questionTextView.setText(firstCard.getQuestion());
            //pass in the answer, and set it
            answerTextView.setText(firstCard.getAnswer());
        }

        //go to next card
        findViewById(R.id.flashcard_next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allFlashcards==null||allFlashcards.size()==0){ //always check for edges before accessing list with index
                    return;
                }
//                try{
//                    Flashcard currentCard=allFlashcards.get(cardIndex);
//                    //update question answer view
//                    questionTextView.setText(currentCard.getQuestion());
//                    answerTextView.setText(currentCard.getAnswer());
//                }catch(IndexOutOfBoundsException e){
//                    System.out.println(e);
//                }
                cardIndex+=1; //cardIndex++ //index to keep track of this
                if(cardIndex>=allFlashcards.size()){ //condition check
                    Snackbar.make(view, //takes in a view
                            "you reached the end of card! going back to start",
                            Snackbar.LENGTH_SHORT).show(); //how long to show the Snackbar

                    cardIndex=0;  //reset the index so that user go back to beginning
                }



                //load animation
                //view variable in onClickListener
                final Animation leftOutAnim= AnimationUtils.loadAnimation(view.getContext(),R.anim.left_out);
                final Animation rightInAnim= AnimationUtils.loadAnimation(view.getContext(),R.anim.right_in);

                //set animationListener for left out first
                //let right out happen when left out finishes
                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        questionTextView.startAnimation(rightInAnim);

                        //move previous logic here
                        //update the next card, get next card
                        Flashcard currentCard=allFlashcards.get(cardIndex);
                        //update question, answer of next card
                        questionTextView.setText(currentCard.getQuestion());
                        answerTextView.setText(currentCard.getAnswer());
                        //make user see next question
                        questionTextView.setVisibility(View.VISIBLE);
                        answerTextView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });
                //trigger the first animation
                questionTextView.startAnimation(leftOutAnim);
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

                //transform question,answer into flashcard object
                Flashcard flashcard=new Flashcard(questionString,answerString);
                //add flashcard into database
                flashcardDatabase.insertCard(flashcard);
                //update list of flashcard
                allFlashcards=flashcardDatabase.getAllCards();
            }
        }
    }
}