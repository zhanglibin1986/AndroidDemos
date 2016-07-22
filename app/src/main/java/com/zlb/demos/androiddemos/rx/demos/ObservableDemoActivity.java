package com.zlb.demos.androiddemos.rx.demos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zlb.demos.androiddemos.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class ObservableDemoActivity extends AppCompatActivity {

    @Bind(R.id.text1)
    TextView textView1;
    @Bind(R.id.text2)
    TextView textView2;
    @Bind(R.id.text3)
    TextView textView3;
    @Bind(R.id.text5)
    TextView textView5;
    @Bind(R.id.text6)
    TextView textView6;
    ArrayList<Integer> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_demo);
        ButterKnife.bind(this);

        Observable.just("show Hello").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView1.setText(s);
            }
        });

        list = new ArrayList();
        for(int i = 0; i < 5; i++) {
            list.add(i);
        }
        textView6.setOnClickListener(view -> Toast.makeText(this, "test", Toast.LENGTH_SHORT).show());
    }


    @OnClick(R.id.text1)
    public void showHello() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");//发出事件
                subscriber.onCompleted();//结束事件
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log("onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                log("onError ");
            }

            @Override
            public void onNext(String s) {
                log("onNext " + s);
                textView1.setText(s);
            }
        });
    }

    @OnClick(R.id.text2)
    public void rxMap() {
        Observable.just("+ RxJava").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return textView2.getText().toString() + s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView2.setText(s);
            }
        });
    }

    @OnClick(R.id.text3)
    public void rxMap3() {
        Observable.just(textView3.getText().toString()).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                int temp = Integer.valueOf(s.substring(3, s.length()));
                int temp2 = temp + 1;
                return Integer.valueOf(temp + temp2);
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "map" + integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView3.setText(s);
            }
        });
    }

    @OnClick(R.id.text4)
    public void rxFlatMap() {


        Observable.from(list).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                Log.d("rx", "flatmap integer = " + integer);
                return Observable.just("-" + integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("rx", "Action1 s = " + s);
                Toast.makeText(ObservableDemoActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.text5)
    public void rxFlatMapFilter() {
        Observable.from(list).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 0;
            }
        }).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just(getBigWrite(integer));
            }
        }).take(2).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView5.setText(textView5.getText().toString() + s);
            }
        });
    }

    private String getBigWrite(int num) {
        switch (num) {
            case 0:
                return "零";
            case 1:
                return "壹";
            case 2:
                return "贰";
            case 3:
                return "叁";
            case 4:
            return "肆";
            case 5:
            return "伍";
            case 6:
            return "陆";
            case 7:
            return "柒";
            case 8:
            return "捌";
            case 9:
            return "玖";
            default:
                return "";

        }

    }



    private void log(String log) {
        Log.d("rx", log);
    }

}
