package com.example.portfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.RelativeLayout;

import com.example.portfolio.activitieschat.MessageSending;
import com.example.portfolio.activitieslifecycle.FirstActivity;
import com.example.portfolio.activitiespassage.PassageActivity;
import com.example.portfolio.activitiessharedata.Sharedata;
import com.example.portfolio.complexholidaycard.HolidayCard;
import com.example.portfolio.complexrecap.Recap;
import com.example.portfolio.complexstudents.Students;
import com.example.portfolio.fragmentsnavigationdrawer.NavDrawer;
import com.example.portfolio.fragmentsslides.FragSlides;
import com.example.portfolio.fragmentstabsapp.TabsApp;
import com.example.portfolio.projectmanager.ProjectManager;
import com.example.portfolio.retrofitmovies.NetworkingRetrofitMovies;
import com.example.portfolio.sharedpreferences.SharedPreferences;
import com.example.portfolio.stylesbatterycontrol.StylesBatteryControl;
import com.example.portfolio.stylescardviewhobby.CardViewHobby;
import com.example.portfolio.stylescollapsingtoolbar.CollapsingToolbar;
import com.example.portfolio.stylescostumdatetimepicker.CustomDateTimePicker;
import com.example.portfolio.viewgroupslogin.ViewGroupsLogin;
import com.example.portfolio.viewgroupsscrollarray.ViewGroupsScrollArray;
import com.example.portfolio.viewschallenge.ViewsChallange;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectsRecView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        projectsRecView = findViewById(R.id.projectsRecycler);
        ArrayList<ProjectCardView> projectCardViews = new ArrayList<>();


        Intent intent = new Intent(this, ViewsChallange.class);


        projectCardViews.add(new ProjectCardView("ViewsChallenge", new Intent(MainActivity.this, ViewsChallange.class), "Description", R.mipmap.a));
        projectCardViews.add(new ProjectCardView("ViewGroupsLogin", new Intent(MainActivity.this, ViewGroupsLogin.class), "Description", R.mipmap.b));
        projectCardViews.add(new ProjectCardView("ViewsGroupsScrollArray", new Intent(MainActivity.this, ViewGroupsScrollArray.class), "Description", R.mipmap.c));

        projectCardViews.add(new ProjectCardView("StylesCustomDateTimePicker", new Intent(MainActivity.this, CustomDateTimePicker.class), "Description", R.mipmap.m));
        projectCardViews.add(new ProjectCardView("StylesCollapsingToolbar", new Intent(MainActivity.this, CollapsingToolbar.class), "Description", R.mipmap.q));
        projectCardViews.add(new ProjectCardView("StylesCardViewHobby", new Intent(MainActivity.this, CardViewHobby.class), "Description", R.mipmap.k));
        projectCardViews.add(new ProjectCardView("StylesBatteryControl", new Intent(MainActivity.this, StylesBatteryControl.class), "Description", R.mipmap.l));

        projectCardViews.add(new ProjectCardView("StoreDataSharedPreferences", new Intent(MainActivity.this, SharedPreferences.class), "Description", R.mipmap.s));
        projectCardViews.add(new ProjectCardView("StoreDataProjectManager", new Intent(MainActivity.this, ProjectManager.class), "Description", R.mipmap.r));

        projectCardViews.add(new ProjectCardView("NetworkingRetrofitMovies", new Intent(MainActivity.this, NetworkingRetrofitMovies.class), "Description", R.mipmap.n));

        projectCardViews.add(new ProjectCardView("FragmentsTabsApp", new Intent(MainActivity.this, TabsApp.class), "Description", R.mipmap.the_prestige));
        projectCardViews.add(new ProjectCardView("FragmentsNavigationDrawer", new Intent(MainActivity.this, NavDrawer.class), "Description", R.mipmap.p));
        projectCardViews.add(new ProjectCardView("FragmentSlides", new Intent(MainActivity.this, FragSlides.class), "Description", R.mipmap.j));

        projectCardViews.add(new ProjectCardView("ComplexViewsStudents", new Intent(MainActivity.this, Students.class), "Description", R.mipmap.e));
        projectCardViews.add(new ProjectCardView("ComplexViewsRecap", new Intent(MainActivity.this, Recap.class), "Description", R.mipmap.d));
        projectCardViews.add(new ProjectCardView("ComplexViewsHolidayCard", new Intent(MainActivity.this, HolidayCard.class), "Description", R.mipmap.f));

        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsSharedata", new Intent(MainActivity.this, Sharedata.class), "Description", R.mipmap.i));
        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsPassageActivity", new Intent(MainActivity.this, PassageActivity.class), "Description", R.mipmap.h));
        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsChatApp", new Intent(MainActivity.this, MessageSending.class), "Description", R.mipmap.o));
        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsActivitiesLifecycle", new Intent(MainActivity.this, FirstActivity.class), "Description", R.mipmap.g));


        ProjectsAdapter adapter = new ProjectsAdapter(this);
        adapter.setProjectCardViews(projectCardViews);
        projectsRecView.setAdapter(adapter);
        projectsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}