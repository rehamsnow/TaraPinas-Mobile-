package com.example.ham.goralets.Filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.ham.goralets.R;

public class FilteredCheckbox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_checkbox);


    }
    public void onSelect(View view){
        boolean checked = ((CheckBox) view).isChecked();
            switch (view.getId()){
                case R.id.CBTravelAgency:
                    if(checked) {
                        Intent a = new Intent(FilteredCheckbox.this, Filter_TravelAgency.class);
                        startActivity(a);
                    }
                    break;
                case R.id.CBTravelDeal:
                    if(checked) {
                        Intent b = new Intent(FilteredCheckbox.this, Filter_TravelDeal.class);
                        startActivity(b);}
                    break;
                case R.id.CBCost1:
                    if(checked) {
                        Intent c = new Intent(FilteredCheckbox.this, Filter_Cost1.class);
                        startActivity(c);}
                    break;
                case R.id.CBCost2:
                    if(checked) {
                        Intent d = new Intent(FilteredCheckbox.this, Filter_Cost2.class);
                        startActivity(d);}
                    break;
                case R.id.CBCost3:
                    if(checked) {
                        Intent e = new Intent(FilteredCheckbox.this, Filter_Cost3.class);
                        startActivity(e);}
                    break;
                case R.id.CBCost4:
                    if(checked) {
                        Intent f = new Intent(FilteredCheckbox.this, Filter_Cost4.class);
                        startActivity(f);}
                    break;
            }
    }


}
