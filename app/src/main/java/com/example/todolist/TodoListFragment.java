package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TodoListFragment extends Fragment {
    private ArrayList<DataList> arrayList = new ArrayList();
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter=null;
    Context context;
    FloatingActionButton addData;
    FloatingActionButton closeArrow;
    EditText adds;
    Button insertbutton;
    LinearLayout insertData;
    INavigationListener iNavigationListener;
    private Database db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.todo_list_fragment,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = ((MainActivity) getActivity()).getDatabase();
        iNavigationListener= (INavigationListener) getActivity();
        addData=view.findViewById(R.id.AddData);
        closeArrow=view.findViewById(R.id.closeArrow);
        adds=view.findViewById(R.id.adds);
        insertData=view.findViewById(R.id.insertData);
        insertbutton=view.findViewById(R.id.insertdatabutton);
        myRecyclerView = view.findViewById(R.id.MyRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);

        adapter=new RecyclerAdapter(context, getAllTask(), new CustomeItemDelete() {
            @Override
            public void customDelteListener(DataList dataList) {
                long del=db.delete(dataList.getId());
                if(del>0){

                    Toast.makeText(getActivity(), "Task Deleted", Toast.LENGTH_SHORT).show();
                    arrayList.clear();
                    adapter.notifyDataSetChanged();
                    getAllTask();

                }
            }


        });
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setAdapter(adapter);
        addBox();
        closeBox();
        addData();




    }




    public void addBox(){

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((INavigationListener) getActivity()).showCreateListFragment();
                insertData.setVisibility(View.VISIBLE);
                addData.setVisibility(View.GONE);
                closeArrow.setVisibility(View.VISIBLE);
            }
        });

    }

    public void closeBox(){

        closeArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData.setVisibility(View.GONE);
                closeArrow.setVisibility(View.GONE);
                addData.setVisibility(View.VISIBLE);
            }
        });


    }




    public void addData(){

insertbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String text=adds.getText().toString();
        if(text.isEmpty()){

            Toast.makeText(getActivity(), "Please Enter Text", Toast.LENGTH_SHORT).show();
        }

        else {
            Calendar calendar = Calendar.getInstance();
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

            DataList dataList = new DataList();
            dataList.setData(text);
            dataList.setDate(currentDate);

            long task_is_inserted = db.insertData(dataList);
            if (task_is_inserted > 0) {
                Toast.makeText(getActivity(), "Task Inserted", Toast.LENGTH_SHORT).show();
                arrayList.clear();
                adapter.notifyDataSetChanged();
                getAllTask();
            } else {

                Toast.makeText(getActivity(), "Task insertion failed", Toast.LENGTH_SHORT).show();
            }

        }
        clear();

    }
});

    }

    public void clear(){

        adds.setText("");
    }

    public ArrayList<DataList> getAllTask(){

        for(DataList data: db.getAllTask()){

          data.getData();
          data.getDate();
          arrayList.add(data);

            System.out.println(data.getData());
            System.out.println(data.getDate());
        }

return arrayList;

    }




}


