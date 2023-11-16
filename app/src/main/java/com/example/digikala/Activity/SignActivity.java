package com.example.digikala.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.digikala.AsyncTask.HttpRequest;
import com.example.digikala.R;
import static com.example.digikala.Activity.MainActivity.HTTP_CODE_SERVER;
import static com.example.digikala.Activity.MainActivity.preferences;


public class SignActivity extends AppCompatActivity  {


        EditText phone , pass ;
        CheckBox showpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        phone=findViewById(R.id.Edt_Email_Sign);
        pass=findViewById(R.id.Edt_Pass_Sign);
        showpass=findViewById(R.id.Chb_CheckBox_Sign); }


    public void SignOnClick(View view){

       switch (view.getId()){
            case R.id.Chb_CheckBox_Sign:
               passShow();
                break;
                case R.id.Txt_CheckBox_Sign:
               passShow();
                break;

           case R.id.Edt_Email_Sign:
               break;

           case R.id.Edt_Pass_Sign:
               Toast.makeText(this, "Edt_Pass_Sign" , Toast.LENGTH_SHORT).show();
               break;


           case R.id.Txt_ForgetPass_Sign:
               if (phone.getText().toString().toUpperCase().startsWith(".09")&& phone.getText().toString().toUpperCase().contains("@")){
                   Toast.makeText(this, "تکمیل نشده", Toast.LENGTH_SHORT).show();
               }
               else {
                   phone.requestFocus();
                   pass.setText(null);
                   Toast toast = Toast.makeText(this, "فرمت ایمیل شما اشتباه است", Toast.LENGTH_LONG);
                   toast.setGravity(Gravity.CENTER,0,0);
                   toast.show();
               }
               break;

           case R.id.Llt_CreateAccunt_Sign :
            createAcunntSign();
               break;
           case R.id.Llt_Img_CreateAccunt_Sign:
               createAcunntSign();
               break;

            case R.id.Llt_Txt_CreateAccunt_Sign:
                createAcunntSign();
               break;

            case R.id.Txt_CreateAccunt_Sign:
                Intent intent = new Intent(SignActivity.this, SignUp.class);
                startActivity(intent);
                finish();
               break;


           }
       }

    private void createAcunntSign() {

            if (!phone.getText().toString().isEmpty()){
                if (phone.getText().toString().endsWith(".com")&& phone.getText().toString().contains("@")&& pass.getText().toString().length()>8) {


                    HttpRequest httpRequest = new HttpRequest(SignActivity.this, null);
                    httpRequest.execute("http://"+HTTP_CODE_SERVER+"/digikala/sigin.php"     ,    phone.getText().toString()   ,     pass.getText().toString());
                    HttpRequest.http( new HttpRequest.HRequest() {
                        @Override
                        public void onPostExecute(final String s) {


                            if (s.contains("not exist")){
                                Toast.makeText(SignActivity.this, "ایمل یا رمز شما اشتباه است", Toast.LENGTH_SHORT).show();
                            }
                            else {
//                            Intent intent =new Intent(SignActivity.this,MainActivity.class);
                                preferences = getSharedPreferences("pref_Name",MODE_PRIVATE);
                                preferences= PreferenceManager.getDefaultSharedPreferences(SignActivity.this);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("name",s);
                                editor.commit();
                           // intent.putExtra("name",s);
                           // setResult(RESULT_OK,intent);
                            finish();}



//                            if (s==null){
//                                Intent intent = new Intent(SignActivity.this, MainActivity.class);
//                                startActivity(intent);
//                            }
//                            else {
//                                if (s=="no"){
//                                    Toast.makeText(SignActivity.this, "1", Toast.LENGTH_SHORT).show();
//                                }
//                                else if (s=="yes"){
//                                    Toast.makeText(SignActivity.this, "2", Toast.LENGTH_SHORT).show();
//                                }
//                                else {
//                                    Toast.makeText(SignActivity.this, "default", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(SignActivity.this, s, Toast.LENGTH_SHORT).show();
//                                }
//                            }

                        }}); }



                else {
                    Toast.makeText(this, "ایمیل یا رمز شما اشتباه است", Toast.LENGTH_SHORT).show();
                    pass.setText(null);
                    phone.requestFocus();
                }
            }

    }




    private void passShow() {
        if (showpass.isChecked())
            pass.setInputType(InputType.TYPE_CLASS_TEXT);

        else  pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
    }



}
