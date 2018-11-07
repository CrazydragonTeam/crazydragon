package com.example.andriod.crazydargon;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList <View> dots;//小圆点List
    private int oldPosition = 0;//上一次点的位置
    private int currentItem ;//当前页面
    private ImageView pic_city;//城市界面跳转图片
    private View view1, view2;//需要滑动的页面
    private ViewPager mviewPager;//显示容器
    private List <View> viewList;//需要滑动的View加入List中
    private String location="auto_ip",position;
    private TextView place,releasetime,humidity,tv_date,Temperature,weather,wind;
    private LinearLayout all;
    RequestQueue queue = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        weatherClick(all);
        bind();
        initData();
        pic_city.setOnClickListener(this);
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };
        mviewPager.setAdapter(pagerAdapter);
        dots = new ArrayList <View>();
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.get(0).setBackgroundResource(R.drawable.dot_focus);
        mviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                dots.get(oldPosition)
                        .setBackgroundResource(R.drawable.dot_normal);
                dots.get(position)
                        .setBackgroundResource(R.drawable.dot_focus);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city:
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("cityname",location);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    //bind
    public void bind() {
        pic_city = findViewById(R.id.city);
        mviewPager = findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.viewpager1, null);
        view2 = inflater.inflate(R.layout.viewpager2, null);
        viewList = new ArrayList <View>();
        viewList.add(view1);
        viewList.add(view2);
        place=findViewById(R.id.place);
        all=findViewById(R.id.all);
        releasetime=findViewById(R.id.releasetime);
        humidity=findViewById(R.id.humidity);
        tv_date =findViewById(R.id.date);
        Temperature=findViewById(R.id.Temperature);
        weather=findViewById(R.id.weather);
        wind=findViewById(R.id.wind);
    }
    public void initData(){
        Intent intent = this.getIntent();
        Bundle bundle =intent.getExtras();
        if (bundle!=null) {
            location = bundle.getString("cityname");
            weatherClick(all);

        }
    }

    public void weatherClick(View view) {


        String url = "https://free-api.heweather.com/s6/weather?key=d650e5f58163406e8b546d5f6ebfac60&location=" + location;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener <JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(jsonObject.toString(),WeatherBean.class);
                String cityName= weatherBean.getHeWeather6().get(0).getBasic().getLocation();
                String log =weatherBean.getHeWeather6().get(0).getUpdate().getLoc();
                String maxtemp = weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_max();
                String mintemp = weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_min();
                String date = weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getDate();
                String temp = weatherBean.getHeWeather6().get(0).getNow().getTmp();
                String cond = weatherBean.getHeWeather6().get(0).getNow().getCond_txt();
                String dir_dir = weatherBean.getHeWeather6().get(0).getNow().getWind_dir();
                String dir_sc = weatherBean.getHeWeather6().get(0).getNow().getWind_sc();

                String day1_maxtmp=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_max();
                String day1_mintmp=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_min();
                String day1_cond=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_txt_n();
                String day1_wind=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(0).getWind_dir();

                String day2_maxtmp=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(1).getTmp_max();
                String day2_mintmp=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(1).getTmp_min();
                String day2_cond=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(1).getCond_txt_n();
                String day2_wind=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(1).getWind_dir();

                String day3_maxtmp=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(2).getTmp_max();
                String day3_mintmp=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(2).getTmp_min();
                String day3_cond=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(2).getCond_txt_n();
                String day3_wind=weatherBean.getHeWeather6().get(0).getDaily_forecast().get(2).getWind_dir();

                place.setText(cityName);
                releasetime.setText(log+"发布");
                humidity.setText("相对湿度"+temp);
                tv_date.setText(date);
                Temperature.setText(mintemp+"~~"+maxtemp+"℃");
                weather.setText(cond);
                wind.setText(dir_dir+dir_sc+"级");


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        queue.add(request);
    }
}
