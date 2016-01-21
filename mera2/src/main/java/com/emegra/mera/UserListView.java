package com.emegra.mera;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Praveen on 8/8/2015.
 */


public class UserListView extends ActionBarActivity {
 private List<String> mItems;
 private UserListAdapter mListAdapter;
 private ListView mCompleteListView;
 String []UserName=new String[100];
 UserData []value=new UserData[100];
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.test);
//  mItems = new ArrayList<String>();
//  mCompleteListView = (ListView) findViewById(R.id.listView);
//  mListAdapter = new UserListAdapter(this, mItems);
//  mCompleteListView.setAdapter(mListAdapter);
//  UserView v=new UserView();
//  v.execute("");
 }
class UserView extends AsyncTask<String,String,String>
{
 int status;

 @Override
 protected String doInBackground(String... params) {
  String serverUrl="http://eypro.earnestcep.in/chat/userview.php";

  function fb=new function();
  Map<String, String> param = new HashMap<String, String>();
  try{
   status=fb.post(serverUrl,param);
  }
  catch (IOException e)
  {
   e.printStackTrace();
  }
  readAndParseJSON(function.getData());
  return "";
 }

 @Override
 protected void onPostExecute(String s) {
  if (status==200)
  {
  mListAdapter.notifyDataSetChanged();
  }
 }

 String convertStreamToString(java.io.InputStream is) {
  java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
  return s.hasNext() ? s.next() : "";
 }
 public void readAndParseJSON(String in) {
  try {
   System.out.println(""+in);

   try {
    // Create the root JSONObject from the JSON string.
    JSONObject  jsonRootObject = new JSONObject(in);

    //Get the instance of JSONArray that contains JSONObjects
    JSONArray jsonArray = jsonRootObject.optJSONArray("userlist");

    //Iterate the jsonArray and print the info of JSONObjects
    for(int i=0; i < jsonArray.length(); i++){
     JSONObject jsonObject = jsonArray.getJSONObject(i);


     int id = Integer.parseInt(jsonObject.optString("id").toString());
String name = jsonObject.optString("name").toString();

     mItems.add(name);
     value[i].setId(id);
     value[i].setName(name);
//     mItems.add(id,name);

    }
   } catch (JSONException e) {e.printStackTrace();}



   //				JSONObject reader = new JSONObject(in);
   //				JSONObject sys  = reader.getJSONObject("results");
   //				country = sys.getString("id");
   //				System.out.println("fine:-"+country);
   //				Toast.makeText(getApplicationContext(), ""+country, Toast.LENGTH_LONG).show();
   //				parsingComplete = false;
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }



 }
}

 class UserListAdapter extends BaseAdapter {
  private Activity mContext;
  private List<String> mList;
  private LayoutInflater layoutInflater = null;

  public UserListAdapter(Activity context, List<String> list) {
   this.mContext = context;
   this.mList = list;
   layoutInflater = (LayoutInflater) mContext
           .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
   return mList.size();
  }

  @Override
  public Object getItem(int position) {
   return mList.get(position);
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
   View v = convertView;
   CompleteListViewHolders viewHolder;
   if (convertView == null) {
    LayoutInflater li = (LayoutInflater) mContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    v = li.inflate(R.layout.user_list, null);
    viewHolder = new CompleteListViewHolders(v);
    v.setTag(viewHolder);
   } else {
    viewHolder = (CompleteListViewHolders) v.getTag();
   }
   viewHolder.mTVItem.setText(mList.get(position));

   v.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {

     TextView tx = (TextView) v.findViewById(R.id.tvc);
     Toast.makeText(mContext, tx.getText()+"-"+position, Toast.LENGTH_SHORT).show();
     return true;
    }
   });
   return v;
  }

  @Override
  public long getItemId(int position) {
   return position;
  }

 }

 class CompleteListViewHolders {
  public TextView mTVItem;


  public CompleteListViewHolders(View base) {
   mTVItem = (TextView) base.findViewById(R.id.tvc);
  }
 }

}
class UserData{
 int id;
 String name;

 public UserData(int id, String name) {
  this.id = id;
  this.name = name;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
}