package com.zlb.demos.androiddemos.labels.activitys;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import butterknife.BindView;
import com.jakewharton.rxbinding.support.design.widget.RxNavigationView;
import com.jakewharton.rxbinding.view.RxView;
import com.zlb.demos.androiddemos.R;
import com.zlb.demos.androiddemos.base.BaseActivity;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends BaseActivity {
    public static String CATEGORY_DEMOS = "com.android.zlb.demos_SAMPLE_CODE";
    @BindView(R.id.main_list) protected RecyclerView recyclerView;
    @BindView(R.id.toolbar) protected Toolbar toolbar;
    @BindView(R.id.fab) protected FloatingActionButton fab;
    @BindView(R.id.drawer_layout) protected DrawerLayout drawer;
    @BindView(R.id.nav_view) protected NavigationView navigationView;

    private HomeAdapter recycleListAdapter;
    private Intent lastedActivity;
    public static final boolean launchLastActivity = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        RxView.clicks(fab).subscribe(aVoid -> Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        RxNavigationView.itemSelections(navigationView).subscribe(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.nav_camera) {
                // Handle the camera action
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {
            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }
            drawer.closeDrawer(GravityCompat.START);
        });

        initRecyclerView();
        recycleListAdapter = new HomeAdapter(this);
        recyclerView.setAdapter(recycleListAdapter);

        Observable.just(getIntent())
                .map(new Func1<Intent, String>() {
                    @Override
                    public String call(Intent intent) {
                        return intent.getStringExtra("com.example.android.apis.Path");
                    }
                })
                .flatMap(new Func1<String, Observable<List<Pair<String, Intent>>>>() {
                    @Override
                    public Observable<List<Pair<String, Intent>>> call(String s) {
                        return getDatas(s);
                    }
                })
                .subscribe(new Action1<List<Pair<String, Intent>>>() {
                               @Override
                               public void call(List<Pair<String, Intent>> pairs) {
                                   recycleListAdapter.setDatas(pairs);
                                   if(launchLastActivity && lastedActivity != null) {
                                       startActivity(lastedActivity);
                                   }
                               }
                           }
                );

        //Intent intent = getIntent();
        //String path = intent.getStringExtra("com.example.android.apis.Path");
        //recycleListAdapter.setDatas(getData(path));
    }


    private List<String> createTestDatas() {
        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            data.add("data " + i);
        }
        return data;
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    protected Observable<List<Pair<String, Intent>>> getDatas(String prefix) {
        return Observable.create(new Observable.OnSubscribe<List<Pair<String, Intent>>>() {
            @Override
            public void call(Subscriber<? super List<Pair<String, Intent>>> subscriber) {
                subscriber.onNext(getData(prefix));
            }
        });
    }

    protected List<Pair<String, Intent>> getData(String prefix) {
        if(prefix == null) {
            prefix = "";
        }
        List<Pair<String, Intent>> myData = new ArrayList<Pair<String, Intent>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        // mainIntent.addCategory("com.example.android.supportv4.SUPPORT4_SAMPLE_CODE");
        mainIntent.addCategory(CATEGORY_DEMOS);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null ? labelSeq.toString()
                    : info.activityInfo.name;

            if (prefixWithSlash.length() == 0
                    || label.startsWith(prefixWithSlash)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0]
                        : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData,
                            nextLabel,
                            activityIntent(
                                    info.activityInfo.applicationInfo.packageName,
                                    info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel,
                                browseIntent(prefix.equals("") ? nextLabel
                                        : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }


        //lastedActivity = activityIntent(list.get(list.size() -1).activityInfo.applicationInfo.packageName, list.get(list.size() -1).activityInfo.name);

        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    private final static Comparator<Pair<String, Intent>> sDisplayNameComparator = new Comparator<Pair<String, Intent>>() {
        private final Collator collator = Collator.getInstance();

        public int compare(Pair<String, Intent> map1, Pair<String, Intent> map2) {
            return collator.compare(map1.first, map2.first);
        }
    };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        Log.d("zlb", "pkg = " + pkg + " , componentName = " + componentName);
        return result;
    }

    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, MainActivity.class);
        result.putExtra("com.example.android.apis.Path", path);
        return result;
    }

    protected void addItem(List<Pair<String, Intent>> data, String name,
                           Intent intent) {
        data.add(Pair.create(name, intent));
    }

    class HomeAdapter extends RecyclerView.Adapter {
        protected List<Pair<String, Intent>> datas = new ArrayList<>();
        protected Context context;

        public HomeAdapter(Context context) {
            this.context = context;
        }

        public List<Pair<String, Intent>> getDatas() {
            return datas;
        }

        public void setDatas(List<Pair<String, Intent>> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }

        public void addDatas(List<Pair<String, Intent>> datas) {
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.simple_recycler_list_item, parent, false)) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.simple_list_item);
            textView.setText(datas.get(position).first);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(datas.get(position).second);
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }
}
