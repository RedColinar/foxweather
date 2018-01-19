package com.harry.foxweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.harry.foxweather.widget.PopupMenu;

/**
 * Created by Harry.Pan on 2018/1/18.
 */

public class WeatherStaticsActivity extends AppCompatActivity {
    private PopupMenu mPopupMenu;

    public Button button;
    public TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_statics);

        button = (Button) findViewById(R.id.back_button);
        textView = (TextView) findViewById(R.id.title_text);

        textView.setText(R.string.weather_statics);

        View menuLayout = getLayoutInflater().inflate(R.layout.weather_statics_toolbar_menu, null);
        menuLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mPopupMenu = new PopupMenu((ViewGroup)menuLayout);
        mPopupMenu.setMenuItemBackgroundColor(R.color.colorAccent);
        mPopupMenu.setMenuItemHoverBackgroundColor(R.color.colorPrimary);
        mPopupMenu.setOnMenuItemSelectedListener(new PopupMenu.OnMenuItemSelectedListener() {
            @Override
            public void onMenuItemSelected(View menuItem) {
                Toast.makeText(WeatherStaticsActivity.this, "Menu item clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // showAsDropDown or dismiss popup menu when clicked
        final int offsetX = 0;
        final int offsetY = 0;
        final float menuToolBarWidth = menuLayout.getMeasuredWidth();
        final View menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupMenu.isShowing()) {
                    mPopupMenu.dismiss();
                } else {
                    // based on bottom-left, need take menu width and menu icon width into account (int) (menuButton.getWidth() - offsetX - menuToolBarWidth)
                    mPopupMenu.showAsDropDown(menuButton, offsetX, offsetX);
                }
            }
        });
    }
}
