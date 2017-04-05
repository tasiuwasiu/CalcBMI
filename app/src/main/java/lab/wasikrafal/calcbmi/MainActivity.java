package lab.wasikrafal.calcbmi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
{
    ICountBMI counter = new CCountBMIforKgM();
    float m;
    float h;
    float bmi=0;
    boolean isData = false;
    private ShareActionProvider mShareActionProvider;


    @BindView(R.id.tv_result) TextView result;
    @BindView(R.id.et_get_mass) EditText mass;
    @BindView(R.id.et_get_height) EditText height;
    @BindView(R.id.sw_units) Switch units;
    @BindView(R.id.but_count) Button count;
    @BindView(R.id.my_toolbar) Toolbar menu;
    @BindString(R.string.share_bmi) String share_string;
    @BindString(R.string.kg) String kg_string;
    @BindString(R.string.m) String m_string;
    @BindString(R.string.lb) String lb_string;
    @BindString(R.string.in) String in_string;
    @BindString(R.string.bmi) String bmi_string;
    @BindString(R.string.color) String color_string;
    @BindString(R.string.h) String h_string;
    @BindString(R.string.imp) String is_imperial;
    @BindString(R.string.saved) String saved_string;
    @BindString(R.string.loaded) String loaded_string;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(menu);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        if(sharedPref.contains(bmi_string))
            load(sharedPref);

        count.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View widok)
            {
                m = Float.parseFloat(mass.getText().toString());
                h = Float.parseFloat(height.getText().toString());
                try
                {
                    bmi=counter.countBMI(m, h);
                    result.setText(String.valueOf(bmi));
                    result.setTextColor(getColor(bmi));
                    setShareIntent();
                }
                catch (IllegalArgumentException e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    isData = false;
                    invalidateOptionsMenu();
                }
            }
        });

        units.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    counter = new CCountBMIforLbIn();
                    mass.setHint(lb_string);
                    height.setHint(in_string);
                }
                else
                {
                    counter = new CCountBMIforKgM();
                    mass.setHint(kg_string);
                    height.setHint(m_string);
                }
            }
        });

        result.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                isData=true;
                invalidateOptionsMenu();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_save).setEnabled(isData);
        menu.findItem(R.id.action_share).setEnabled(isData);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        return true;
    }

    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence(bmi_string, result.getText());
        savedInstanceState.putInt(color_string, result.getCurrentTextColor());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        result.setText(savedInstanceState.getCharSequence(bmi_string));
        result.setTextColor(savedInstanceState.getInt(color_string));
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        final Intent go_author = new Intent(this, AuthorActivity.class);

        if (id == R.id.action_author)
        {
            startActivity(go_author);
            return true;
        }

        if (id == R.id.action_save)
        {
            save();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onDestroy()
    {
        super.onDestroy();
    }

    private int getColor(float bmi)
    {
        int color = Color.GREEN;
        if (bmi<18.5 || bmi>25)
            color = Color.rgb(255,165,0);
        if (bmi<17 || bmi>=30)
            color = Color.RED;
        return color;
    }

    private void setShareIntent()
    {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, share_string + bmi);
        shareIntent.setType("text/plain");

        if(mShareActionProvider != null)
        {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    private void save()
    {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(counter instanceof CCountBMIforLbIn)
            editor.putBoolean(is_imperial, true);
        else
            editor.putBoolean(is_imperial, false);
        editor.putFloat(bmi_string, bmi);
        editor.putFloat(m_string, m);
        editor.putFloat(h_string, h);
        editor.apply();
        Toast.makeText(getApplicationContext(), saved_string,
                Toast.LENGTH_LONG).show();
    }

    private void load(SharedPreferences sharedPref)
    {
        if(sharedPref.getBoolean(is_imperial,false))
        {
            counter = new CCountBMIforLbIn();
            units.setChecked(true);
        }
        m = sharedPref.getFloat(m_string, 0);
        mass.setText(String.valueOf(m));
        h = sharedPref.getFloat(h_string, 0);
        height.setText(String.valueOf(h));
        bmi = sharedPref.getFloat(bmi_string, 0);
        result.setText(String.valueOf(bmi));
        result.setTextColor(getColor(bmi));
        Toast.makeText(getApplicationContext(), loaded_string,
                Toast.LENGTH_LONG).show();
    }
}