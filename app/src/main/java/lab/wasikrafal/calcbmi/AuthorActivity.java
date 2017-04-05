package lab.wasikrafal.calcbmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorActivity extends AppCompatActivity
{
    @BindView(R.id.but_back) Button b_back;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        ButterKnife.bind(this);
        b_back.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View widok)
            {
                finish();
            }

        });
    }
}
