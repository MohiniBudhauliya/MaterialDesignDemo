package mb.com.materialdesigndemo;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton fabButton,callButton,smsButton;
    LinearLayout callLayout,smsLayout;
    android.support.design.widget.CoordinatorLayout mFabcontainer;
    Animation showButton,hideButton,showlayout,hidelayout;
    int clickCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fabButton=(FloatingActionButton)findViewById(R.id.mainFabButton);
        callButton=(FloatingActionButton)findViewById(R.id.callButton);
        smsButton=(FloatingActionButton)findViewById(R.id.smsButton);
        mFabcontainer=(android.support.design.widget.CoordinatorLayout)findViewById(R.id.fabContainer);
        callLayout=(LinearLayout) findViewById(R.id.callLayout);
        smsLayout=(LinearLayout) findViewById(R.id.smslayout);
        fabButton.setOnClickListener(this);
        callButton.setOnClickListener(this);
        smsButton.setOnClickListener(this);
        showButton= AnimationUtils.loadAnimation(HomeActivity.this,R.anim.showbutton);
        hideButton=AnimationUtils.loadAnimation(HomeActivity.this,R.anim.hidebutton);
        showlayout=AnimationUtils.loadAnimation(HomeActivity.this,R.anim.showlayout);
        hidelayout=AnimationUtils.loadAnimation(HomeActivity.this,R.anim.hidelayout);
        if(callLayout.getVisibility()==View.VISIBLE&&smsLayout.getVisibility()==View.VISIBLE)
        {
            callLayout.setVisibility(View.GONE);
            smsLayout.setVisibility(View.GONE);
        }
    }
    Intent chooseAction=new Intent(Intent.ACTION_VIEW);


    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id)
        {
            case R.id.mainFabButton:
                if(clickCount==0) {
                    callLayout.setVisibility(View.VISIBLE);
                    smsLayout.setVisibility(View.VISIBLE);
                    fabButton.startAnimation(showButton);
                    Snackbar snackbar = Snackbar.make(mFabcontainer, "Choose any option", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    clickCount++;
                }
                else
                {
                    callLayout.setVisibility(View.GONE);
                    smsLayout.setVisibility(View.GONE);
                    fabButton.startAnimation(hideButton);
                    clickCount--;
                }

                break;
            case R.id.callButton:
                if(chooseAction.resolveActivity(getPackageManager())!=null) {
                    chooseAction.setData(Uri.parse("tel://"));
                    startActivity(chooseAction);
                }
                else
                {
                    Toast.makeText(HomeActivity.this,"There is no app that can make call",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.smsButton:
                if(chooseAction.resolveActivity(getPackageManager())!=null) {
                    chooseAction.setData(Uri.parse("sms://"));
                    startActivity(chooseAction);
                }
                else
                {
                    Toast.makeText(HomeActivity.this,"There is no app for messaging",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
