package ru.mirea.burmakin.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    EditText editText;
    EditText editText2;
    int res;
    String r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Текущий поток: " + mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("MireaThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
    }

    public void onClick(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Запущен поток № " + numberThread);
                editText = (EditText) findViewById(R.id.editTextTextPersonName);
                editText2 = (EditText) findViewById(R.id.editTextTextPersonName2);
                int num1 = Integer.parseInt(editText.getText().toString());
                int num2 = Integer.parseInt(editText2.getText().toString());
                res = (num1 / (num2 * 4));
                r = Integer.toString(res);

                long endTime = System.currentTimeMillis()
                        + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Log.i("ThreadProject", "Выполнен поток № " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        TextView info = findViewById(R.id.textView);
        info.setText(r);
    }

}