package powerup.systers.com;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.Manifest.*;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                askPermissions();
            }
        },1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
            finish();
        }
    }

    /**
     * Get write permissions on Android 6+
     */
    private void askPermissions() {
        if (ContextCompat.checkSelfPermission(this, permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[] {permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
            finish();
        }
    }
}
