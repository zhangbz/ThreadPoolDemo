package com.example.janiszhang.threadpooldemo;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //任务数量
    private static final int MAX = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//            fixedThreadPool(3);
//            cachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        //2000ms后执行command
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.i("MainActivity", "执行线程: " + Thread.currentThread().getName());
            }
        },2000, TimeUnit.MILLISECONDS);

        //延迟10ms后,每隔1000ms执行一次command
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.i("MainActivity", "执行线程: " + Thread.currentThread().getName());
            }
        },10,1000,TimeUnit.MILLISECONDS);

    }

    private static void fixedThreadPool(int size)  {
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0;i < MAX; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Log.i("MainActivity", "执行线程: " + Thread.currentThread().getName());
                }
            });
        }
    }

    private static void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < MAX; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    Log.i("MainActivity", "执行线程: " + Thread.currentThread().getName());
                }
            });
        }
    }
    
}
