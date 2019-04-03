package com.example.lambdamessages;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MessageBoard> boardList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                boardList = MessageBoardDao.getMessageBoards();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (final MessageBoard messageBoard : boardList) {
                            LinearLayout linearLayout = findViewById(R.id.linear_layout_child);
                            TextView textView = new TextView(context);
                            textView.setText(messageBoard.getTitle());
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(context, BoardView.class);
                                    intent.putExtra("MESSAGE_BOARD_KEY",messageBoard);
                                    startActivity(intent);
                                }
                            });
                            linearLayout.addView(textView);
                        }

                    }
                });
            }
        }).start();
        int i = 0;
        //
    }
}
