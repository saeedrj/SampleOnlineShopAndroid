package com.example.digikala.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.digikala.AsyncTask.HttpRequest;
import com.example.digikala.R;
import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;
import static com.example.digikala.Activity.MainActivity.preferences;
public class SignUp extends AppCompatActivity {

    LinearLayout CreateAccunt;
    TextView llt_Txt_CreateAccunt_SignUp;
    ImageView llt_Img_CreateAccunt_SignUp;
    EditText pass ,rep_pass, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById_method();

        CreateAccunt.setOnClickListener(onClickListener);
        llt_Img_CreateAccunt_SignUp.setOnClickListener(onClickListener);
        llt_Txt_CreateAccunt_SignUp.setOnClickListener(onClickListener); }

    private void findViewById_method() {
        CreateAccunt = findViewById(R.id.Llt_CreateAccunt_SignUp);
        CreateAccunt = findViewById(R.id.Llt_CreateAccunt_SignUp);
        pass=findViewById(R.id.Edt_Pass_SignUp);
        rep_pass=findViewById(R.id.Edt_Pass_SignUp_rep);
        email=findViewById(R.id.Edt_Email_SignUp);
        llt_Img_CreateAccunt_SignUp=findViewById(R.id.Llt_Img_CreateAccunt_SignUp);
        llt_Txt_CreateAccunt_SignUp=findViewById(R.id.Llt_Txt_CreateAccunt_SignUp);
        pass.setOnFocusChangeListener(onFocusChangeListener);
        rep_pass.setOnFocusChangeListener(onFocusChangeListener);
        email.setOnFocusChangeListener(onFocusChangeListener); }



    View.OnFocusChangeListener onFocusChangeListener =new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus){
            if (v.getId()==R.id.Edt_Email_SignUp||email.isFocused()){email.setTextColor(Color.BLACK);}
           else if (v.getId()==R.id.Edt_Pass_SignUp||pass.isFocused()){pass.setTextColor(Color.BLACK);}
           else if (v.getId()==R.id.Edt_Pass_SignUp_rep||rep_pass.isFocused()){rep_pass.setTextColor(Color.BLACK);}


        }}};


    View.OnClickListener onClickListener =new View.OnClickListener() {
         @Override
         public void onClick(View v) {


             if (email.getText().toString().toLowerCase().endsWith(".com")&& email.getText().toString().toLowerCase().contains("@")){
                     if (pass.getText().toString().length() >= 8) {
                         if (pass.getText().toString().equals(rep_pass.getText().toString())) {
                                 HttpRequest httpRequest = new HttpRequest(SignUp.this, null);
                                 httpRequest.execute("http://"+HTTP_CODE_SERVER+"/digikala/insert.php", email.getText().toString(), pass.getText().toString());
                                 HttpRequest.http(hrequest);
                         } else {

                             rep_pass.setText(null);
                             Toast toast = Toast.makeText(SignUp.this, "  رمز شما با تکرار آن همخوانی ندارد  ", Toast.LENGTH_SHORT);
                             toast.setGravity(Gravity.TOP, 0, 0);
                             toast.getView().setBackgroundColor(Color.RED);
                             toast.show();
                         }




                     } else {


                         if (rep_pass.getText().toString().length() != 0)
                         rep_pass.setText(null);
                         pass.setTextColor(Color.argb(255, 240, 100, 100));
                         pass.setText(pass.getText() + " !");
                         Toast toast = Toast.makeText(SignUp.this, " رمز شما باید بیشتر از هشت کارامتر باشد  ", Toast.LENGTH_SHORT);
                         toast.setGravity(Gravity.TOP, 0, 0);
                         toast.getView().setBackgroundColor(Color.RED);
                         toast.show();
                         pass.requestFocus();
                     }


             } else {
                 email.setTextColor(Color.argb(255,240,100,100));
                 email.setText(email.getText()+" !");
                 Toast toast =Toast.makeText(SignUp.this, "  فرمت ایمیل شما اشتباه است  ", Toast.LENGTH_SHORT);
                 toast.setGravity(Gravity.TOP,0,0);
                 toast.getView().setBackgroundColor(Color.RED);
                 toast.show();
             }}};



    HttpRequest.HRequest hrequest=new HttpRequest.HRequest() {
        @Override
        public void onPostExecute(final String s) {

            if (s.contains("email")){
               Toast toast = Toast.makeText(SignUp.this, " اییل شما در سیستم موجود است ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.getView().setBackgroundColor(Color.RED);
                toast.show();
            }
            else if (s.contains("not")){ Toast toast = Toast.makeText(SignUp.this, " به دلیل نقص فنی اطلاعات ذخیره نشد دوباره تلاش کنید ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.getView().setBackgroundColor(Color.RED);
                toast.show();}
            else {
            preferences = getSharedPreferences("pref_Name",MODE_PRIVATE);
            preferences= PreferenceManager.getDefaultSharedPreferences(SignUp.this);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("name",s);
            editor.commit();
            finish();}


//


                    }};}

