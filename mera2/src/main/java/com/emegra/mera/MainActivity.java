package com.emegra.mera;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private ListView mCompleteListView;
    private Button mAddItemToList;
    private EditText Edt_txt;
    private List<String> mItems;
    private CompleteListAdapter mListAdapter;
    int status;
    String message;
    DbHelper db;
    private static final int MIN = 0, MAX = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mItems = new ArrayList<String>();
        mListAdapter = new CompleteListAdapter(this, mItems);
        mCompleteListView.setAdapter(mListAdapter);
        addItemsFromDB();
    }
    private void initViews() {
        Edt_txt=(EditText)findViewById(R.id.edt_txt);
        mCompleteListView = (ListView) findViewById(R.id.completeList);
        mAddItemToList = (Button) findViewById(R.id.addItemToList);
        mAddItemToList.setOnClickListener(this);
    }
    public void addItemsFromDB(){
        db=new DbHelper(getApplicationContext());
        String []valuess=db.getValueByRows();

        for (int i=0;valuess[i]!=null;i++)
        {
            mItems.add(String.valueOf(valuess[i]));
            mListAdapter.notifyDataSetChanged();
        }

    }
    private void addItemsToList() {
//        int randomVal = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
        mItems.add(String.valueOf(Edt_txt.getText()));
        mListAdapter.notifyDataSetChanged();
        chatAns ca=new chatAns();
        ca.execute("");
        message=Edt_txt.getText().toString();
        Edt_txt.setText("");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addItemToList:
                addItemsToList();
                break;
        }
    }
    class chatAns extends AsyncTask<String, Integer, String>
    {

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            Toast.makeText(getApplication(),"Sending",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected String doInBackground(String... params) {
            String serverUrl="http://eypro.earnestcep.in/chat/chat.php";

            function fb=new function();
            Map<String, String> param = new HashMap<String, String>();
            param.put("message",message);
            param.put("user","3");
            param.put("to", "4");
            ContentValues cv=new ContentValues();
            cv.put("message",message);
            cv.put("user","3");
            cv.put("tou","4");
            try {
                status=fb.post(serverUrl, param);
            } catch (IOException e) {
                e.printStackTrace();
            }
          db=new DbHelper(getApplicationContext());
            db.insert(cv);
            db.getValues();

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
           if (status==200)
           {
               Toast.makeText(getApplication(),"Send Successfully\n"+db.getValues(),Toast.LENGTH_SHORT).show();

           }
        }
    }
}