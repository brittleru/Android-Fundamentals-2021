package com.example.portfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SearchView;

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
    private ProjectsAdapter adapter;
    private ArrayList<ProjectCardView> projectCardViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        projectCardViews = new ArrayList<>();
        generateData(projectCardViews);
        makeProjectsRecView(projectCardViews);


    }


    private void makeProjectsRecView(ArrayList<ProjectCardView> projectCardViews) {
        projectsRecView = findViewById(R.id.projectsRecycler);
        projectsRecView.setHasFixedSize(true);

        adapter = new ProjectsAdapter(this, projectCardViews);
        adapter.setProjectCardViews(projectCardViews);
        projectsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        projectsRecView.setAdapter(adapter);
    }

    private void generateData(ArrayList<ProjectCardView> projectCardViews) {
        projectCardViews.add(new ProjectCardView("ViewsChallenge", new Intent(MainActivity.this, ViewsChallange.class), getResources().getString(R.string.viewsChallengeDesc), R.mipmap.a));
        projectCardViews.add(new ProjectCardView("ViewGroupsLogin", new Intent(MainActivity.this, ViewGroupsLogin.class), getResources().getString(R.string.viewGroupsLoginDesc), R.mipmap.b));
        projectCardViews.add(new ProjectCardView("ViewsGroupsScrollArray", new Intent(MainActivity.this, ViewGroupsScrollArray.class), getResources().getString(R.string.viewsGroupsScrollArrayDesc), R.mipmap.c));

        projectCardViews.add(new ProjectCardView("StylesCustomDateTimePicker", new Intent(MainActivity.this, CustomDateTimePicker.class), getResources().getString(R.string.stylesCustomDateTimePickerDesc), R.mipmap.m));
        projectCardViews.add(new ProjectCardView("StylesCollapsingToolbar", new Intent(MainActivity.this, CollapsingToolbar.class), getResources().getString(R.string.stylesCollapsingToolbarDesc), R.mipmap.q));
        projectCardViews.add(new ProjectCardView("StylesCardViewHobby", new Intent(MainActivity.this, CardViewHobby.class), getResources().getString(R.string.stylesCardViewHobbyDesc), R.mipmap.k));
        projectCardViews.add(new ProjectCardView("StylesBatteryControl", new Intent(MainActivity.this, StylesBatteryControl.class), getResources().getString(R.string.stylesBatteryControlDesc), R.mipmap.l));

        projectCardViews.add(new ProjectCardView("StoreDataSharedPreferences", new Intent(MainActivity.this, SharedPreferences.class), getResources().getString(R.string.storeDataSharedPreferencesDesc), R.mipmap.s));
        projectCardViews.add(new ProjectCardView("StoreDataProjectManager", new Intent(MainActivity.this, ProjectManager.class), getResources().getString(R.string.storeDataProjectManagerDesc), R.mipmap.r));

        projectCardViews.add(new ProjectCardView("NetworkingRetrofitMovies", new Intent(MainActivity.this, NetworkingRetrofitMovies.class), getResources().getString(R.string.networkingRetrofitMoviesDesc), R.mipmap.n));

        projectCardViews.add(new ProjectCardView("FragmentsTabsApp", new Intent(MainActivity.this, TabsApp.class), getResources().getString(R.string.fragmentsTabsAppDesc), R.mipmap.the_prestige));
        projectCardViews.add(new ProjectCardView("FragmentsNavigationDrawer", new Intent(MainActivity.this, NavDrawer.class), getResources().getString(R.string.fragmentsNavigationDrawerDesc), R.mipmap.p));
//        projectCardViews.add(new ProjectCardView("FragmentSlides", new Intent(MainActivity.this, FragSlides.class), getResources().getString(R.string.fragmentSlidesDesc), R.mipmap.j));

        projectCardViews.add(new ProjectCardView("ComplexViewsStudents", new Intent(MainActivity.this, Students.class), getResources().getString(R.string.complexViewsStudentsDesc), R.mipmap.e));
        projectCardViews.add(new ProjectCardView("ComplexViewsRecap", new Intent(MainActivity.this, Recap.class), getResources().getString(R.string.complexViewsRecapDesc), R.mipmap.d));
        projectCardViews.add(new ProjectCardView("ComplexViewsHolidayCard", new Intent(MainActivity.this, HolidayCard.class), getResources().getString(R.string.complexViewsHolidayCardDesc), R.mipmap.f));

        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsSharedata", new Intent(MainActivity.this, Sharedata.class), getResources().getString(R.string.activitiesAndIntentsSharedataDesc), R.mipmap.i));
        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsPassageActivity", new Intent(MainActivity.this, PassageActivity.class), getResources().getString(R.string.activitiesAndIntentsPassageActivityDesc), R.mipmap.h));
        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsChatApp", new Intent(MainActivity.this, MessageSending.class), getResources().getString(R.string.activitiesAndIntentsChatAppDesc), R.mipmap.o));
        projectCardViews.add(new ProjectCardView("ActivitiesAndIntentsActivitiesLifecycle", new Intent(MainActivity.this, FirstActivity.class), getResources().getString(R.string.activitiesAndIntentsActivitiesLifecycleDesc), R.mipmap.g));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.projects_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_projects);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return true;
    }


}