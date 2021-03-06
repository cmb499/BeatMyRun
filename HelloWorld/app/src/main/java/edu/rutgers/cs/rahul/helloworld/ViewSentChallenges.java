package edu.rutgers.cs.rahul.helloworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.plus.Plus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/*
* Created by Careena Btaganza
* Tested by Nirali Shah
* Debugged by Careena Braganza
 */
public class ViewSentChallenges extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    //String datetime;

    String currentUserId = Plus.PeopleApi.getCurrentPerson(LoginActivity.mGoogleApiClient).getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_challenges);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);



        getJSON();

    }



    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_RECEIVER_ID);
                String  datetime = jo.getString(Config.TAG_DESG);
                String receiverName = jo.getString(Config.TAG_USER_NAME);


                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_ID,id);
                employees.put(Config.TAG_RECEIVER_ID,name);
                employees.put(Config.TAG_DESG,datetime);
                employees.put(Config.TAG_USER_NAME, receiverName);//changed
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewSentChallenges.this, list, R.layout.list_item,
                new String[]{Config.TAG_ID,Config.TAG_USER_NAME},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSentChallenges.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALL_SENT,currentUserId);
                // String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewSingleSentChallenge.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Config.TAG_ID).toString();
        String recId = map.get(Config.TAG_RECEIVER_ID).toString();
        String datetime = map.get(Config.TAG_DESG).toString();
        String receiver_name = map.get(Config.TAG_USER_NAME).toString();

        intent.putExtra(Config.EMP_ID, empId);       //sender
        intent.putExtra(Config.REC_ID, recId);      //receiver
        intent.putExtra(Config.DATETIME_ID, datetime);
        intent.putExtra(Config.RECEIVER_NAME, receiver_name);

        startActivity(intent);
    }
}
