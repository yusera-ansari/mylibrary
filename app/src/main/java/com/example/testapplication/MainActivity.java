package com.example.testapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        TooltipCompat.setTooltipText(btn, "This is your tooltip");
        View anchorView = btn;

        LayoutInflater inflater = LayoutInflater.from(this);
        View tooltipView = inflater.inflate(R.layout.tooltip_overlay, null);

        // Post a runnable to ensure the view has been laid out
        anchorView.postDelayed(() -> {
            TooltipWindow tipWindow = new TooltipWindow(MainActivity.this, TooltipWindow.DRAW_BOTTOM, "Click here to view th magic");
            tipWindow.showToolTip(btn, TooltipWindow.DRAW_ARROW_DEFAULT_CENTER, false);

            PopupWindow tooltipWindow = new PopupWindow(
                    tooltipView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            tooltipWindow.setOutsideTouchable(true);
            tooltipWindow.setFocusable(true);
            int[] location = new int[2];
            anchorView.getLocationOnScreen(location);
            tooltipWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                    location[0] , location[1] );
        }, 2000);

    }
}