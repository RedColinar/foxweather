package com.harry.foxweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.harry.foxweather.gson.weatherData.Forecast;
import com.harry.foxweather.gson.weatherData.Weather;
import com.harry.foxweather.util.HttpUtil;
import com.harry.foxweather.util.Utility;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private String mWeatherId;
    int oneDayLong = 24 * 60 * 60 * 1000;

    @BindView(R.id.bing_pic_img) public ImageView bingPicImg;
    @BindView(R.id.nav_button) public Button navButton;
    @BindView(R.id.statics_button) public Button staticsButton;
    @BindView(R.id.weather_layout) public ScrollView weatherLayout;
    @BindView(R.id.title_city) public TextView titleCity;
    @BindView(R.id.title_update_time) public TextView titleUpdateTime;
    @BindView(R.id.degree_text) public TextView degreeText;
    @BindView(R.id.weather_info_text) public TextView weatherInfoText;
    @BindView(R.id.aqi_text) public TextView aqiText;
    @BindView(R.id.pm25_text) public TextView pm25Text;
    @BindView(R.id.comfort_text) public TextView comfortText;
    @BindView(R.id.car_wash_text) public TextView carWashText;
    @BindView(R.id.sport_text) public TextView sportText;

    @BindView(R.id.forecast_layout) public LinearLayout forecastLayout;
    @BindView(R.id.drawer_layout) public DrawerLayout drawerLayout;
    @BindView(R.id.swipe_refresh) public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        String bingPic = prefs.getString("bing_pic", null);
        long lastUpdatePic = prefs.getLong("last_update_pic", 0);
        boolean moreThanOneDay = (new Date().getTime() - lastUpdatePic) >= oneDayLong;

        if (weatherString != null) {
            // 有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            if (weather != null) {
                mWeatherId = weather.basic.weatherId;
                showWeatherInfo(weather);
            }
        } else {
            // 无缓存时去服务器查询天气
            mWeatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);
        }
        swipeRefreshLayout.setOnRefreshListener(() -> requestWeather(mWeatherId));
        navButton.setOnClickListener((View v) -> drawerLayout.openDrawer(GravityCompat.START));

        staticsButton.setText(R.string.statics);
        staticsButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, WeatherStaticsActivity.class);
            startActivity(intent);
            // finish();
        });

        if (bingPic != null && !moreThanOneDay) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        } else {
            loadBingPic();
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
            editor.putLong("last_update_pic", new Date().getTime());
            editor.apply();
        }
    }

    /**
     * 根据天气id请求城市天气信息。
     */
    public void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=f8c8dee47ed745c6bc109f1abfc13d13";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(() -> {
                    if (weather != null && "ok".equals(weather.status)) {
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                        mWeatherId = weather.basic.weatherId;
                        showWeatherInfo(weather);
                    } else {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                });
            }
        });
        loadBingPic();
    }

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String bingPic = "http://area.sinaapp.com/bingImg/";
        runOnUiThread(() -> Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg));
    }

    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecasts) {
            View view = LayoutInflater.from(this).inflate(R.layout.weather_forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max);
            minText.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }
        if (weather.aqi != null) {
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comfort = "舒适度：" + weather.suggestion.comfort.info;
        String carWash = "洗车指数：" + weather.suggestion.carWash.info;
        String sport = "运行建议：" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}