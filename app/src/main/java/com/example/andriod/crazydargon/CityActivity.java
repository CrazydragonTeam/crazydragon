package com.example.andriod.crazydargon;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

public class CityActivity extends Activity {
    private ImageView pic_back;
    private Button btn_search;
    private EditText et_search;
    RequestQueue queue = null;
    private String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        init();
        initData();
        pic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intet = new Intent(CityActivity.this, MainActivity.class);
                startActivity(intet);
            }
        });
        btn_search=findViewById(R.id.btn_search);
        jump();
    }

    public void jump(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = et_search.getText().toString();
                if(!location.equals("")){
                    Intent intent=new Intent();
                    intent.setClass(CityActivity.this,MainActivity.class);
                    Bundle bundle =new Bundle();
                    bundle.putString("cityname",location);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    et_search.setHint("请输入城市名称");
                    return;
                }
            }
        });
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            location=bundle.getString("cityname");
            et_search.setText(location);
        }
    }
    public void init(){
        pic_back = findViewById(R.id.back);
        et_search = findViewById(R.id.searching);

    }
    public void initData(){

    }

}
