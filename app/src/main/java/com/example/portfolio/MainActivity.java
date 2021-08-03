package com.example.portfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

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

        projectCardViews.add(new ProjectCardView("ViewsChallange", new Intent(MainActivity.this, ViewsChallange.Class), "Description", R.mipmap.a));
//        projectCardViews.add(new ProjectCardView("ViewsGroupsScrollArray", new Intent(MainActivity.this, ViewGroupsScrollArray.Class), "Description", R.mipmap.c));
        projectCardViews.add(new ProjectCardView("ViewGroupsLogin", new Intent(MainActivity.this, ViewGroupsLogin.Class), "Description", R.mipmap.b));
//        projectCardViews.add(new ProjectCardView("StylesCustomDateTimePicker", new Intent(MainActivity.this, StylesCustomDateTimePicker.Class), "Description", R.mipmap.m));
//        projectCardViews.add(new ProjectCardView("StylesCollapsingToolbar", new Intent(MainActivity.this, StylesCollapsingToolbar.Class), "Description", R.mipmap.q));
//        projectCardViews.add(new ProjectCardView("StylesCardViewHobby", new Intent(MainActivity.this, StylesCardViewHobby.Class), "Description", R.mipmap.k));
        projectCardViews.add(new ProjectCardView("StylesBatteryControl", new Intent(MainActivity.this, StylesBatteryControl.Class), "Description", R.mipmap.l));
//        projectCardViews.add(new ProjectCardView("StoreDataSharedPreferences", new Intent(MainActivity.this, StoreDataSharedPreferences.Class), "Description", R.mipmap.s));

//        projectCardViews.add(new ProjectCardView("StoreDataProjectManager", new Intent(MainActivity.this, StoreDataProjectManager.Class), "Description", R.mipmap.r));

        projectCardViews.add(new ProjectCardView("NetworkingRetrofitMovies", new Intent(MainActivity.this, NetworkingRetrofitMovies.Class), "Description", R.mipmap.n));
//        projectCardViews.add(new ProjectCardView("FragmentsTabsApp", new Intent(MainActivity.this, FragmentsTabsApp.Class), "Description", R.mipmap.a));
//        projectCardViews.add(new ProjectCardView("FragmentsNavigationDrawer", new Intent(MainActivity.this, FragmentsNavigationDrawer.Class), "Description", R.mipmap.p));
//        projectCardViews.add(new ProjectCardView("FragmentSlides", new Intent(MainActivity.this, FragmentSlides.Class), "Description", R.mipmap.j));
//        projectCardViews.add(new ProjectCardView("ComplexViewsStudents", new Intent(MainActivity.this, ComplexViewsStudents.Class), "Description", R.mipmap.e));
//        projectCardViews.add(new ProjectCardView("ComplexViewsRecap", new Intent(MainActivity.this, ComplexViewsRecap.Class), "Description", R.mipmap.d));
//        projectCardViews.add(new ProjectCardView("ComplexViewsHolidayCard", new Intent(MainActivity.this, ComplexViewsHolidayCard.Class), "Description", R.mipmap.f));
//        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsSharedata", new Intent(MainActivity.this, ActivitiesAndIntentsSharedata.Class), "Description", R.mipmap.i));
//        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsPassageActivity", new Intent(MainActivity.this, ActivitiesAndIntentsPassageActivity.Class), "Description", R.mipmap.h));
//        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsChatApp", new Intent(MainActivity.this, ActivitiesAndIntentsChatApp.Class), "Description", R.mipmap.o));
//        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsActivitiesLifecycle", new Intent(MainActivity.this, ActivitiesAndIntentsActivitiesLifecycle.Class), "Description", R.mipmap.g));


        ProjectsAdapter adapter = new ProjectsAdapter(this);
        adapter.setProjectCardViews(projectCardViews);
        projectsRecView.setAdapter(adapter);
        projectsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}