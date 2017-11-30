package powerup.systers.com;


import android.content.Intent;
import android.widget.Toast;

public abstract class Activity extends android.app.Activity{
    private static long time;
    @Override
    public void onBackPressed(){
        if(time+2000>System.currentTimeMillis()){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }else{
            Toast.makeText(this.getApplicationContext(),R.string.press_once_more_to_exit,Toast.LENGTH_SHORT).show();
        }
        time=System.currentTimeMillis();

    }
}
