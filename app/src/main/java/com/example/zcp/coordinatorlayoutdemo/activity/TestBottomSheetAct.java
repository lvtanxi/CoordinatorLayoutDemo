package com.example.zcp.coordinatorlayoutdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zcp.coordinatorlayoutdemo.R;

/**
 * User: 吕勇
 * Date: 2016-07-14
 * Time: 13:29
 * Description:
 */
public class TestBottomSheetAct extends AppCompatActivity{
    private boolean isSetBottomSheetHeight;

    public static void startTestBottomSheetAct(Activity activity) {
        activity.startActivity(new Intent(activity, TestBottomSheetAct.class));
    }
    private CoordinatorLayout testCoor;
    private RelativeLayout designBottomSheetBar;
    private RelativeLayout designBottomSheet;
    private TextView bottomSheetTv;
    private ImageView bottomSheetIv;
    private BottomSheetBehavior behavior;

    private void assignViews() {
        testCoor = (CoordinatorLayout) findViewById(R.id.test_coor);
        designBottomSheetBar = (RelativeLayout) findViewById(R.id.design_bottom_sheet_bar);
        designBottomSheet = (RelativeLayout) findViewById(R.id.design_bottom_sheet);
        bottomSheetTv = (TextView) findViewById(R.id.bottom_sheet_tv);
        bottomSheetIv = (ImageView) findViewById(R.id.bottom_sheet_iv);
        behavior=BottomSheetBehavior.from(designBottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState!=BottomSheetBehavior.STATE_COLLAPSED&&bottomSheetTv.getVisibility()==View.VISIBLE){
                    bottomSheetTv.setVisibility(View.GONE);
                    bottomSheetIv.setVisibility(View.VISIBLE);
                }else if(newState==BottomSheetBehavior.STATE_COLLAPSED&&bottomSheetTv.getVisibility()==View.GONE){
                    bottomSheetTv.setVisibility(View.VISIBLE);
                    bottomSheetIv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                if(bottomSheet.getTop()<2*designBottomSheetBar.getHeight()){
                    designBottomSheetBar.setVisibility(View.VISIBLE);
                    designBottomSheetBar.setAlpha(slideOffset);
                    designBottomSheetBar.setTranslationY(bottomSheet.getTop()-2*designBottomSheetBar.getHeight());
                }
                else{
                    designBottomSheetBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        designBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        designBottomSheetBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_bottom_test);
        assignViews();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!isSetBottomSheetHeight){
            CoordinatorLayout.LayoutParams linearParams =(CoordinatorLayout.LayoutParams) designBottomSheet.getLayoutParams();
            linearParams.height=testCoor.getHeight()-designBottomSheetBar.getHeight();
            designBottomSheet.setLayoutParams(linearParams);
            isSetBottomSheetHeight=true;
        }
    }
}
