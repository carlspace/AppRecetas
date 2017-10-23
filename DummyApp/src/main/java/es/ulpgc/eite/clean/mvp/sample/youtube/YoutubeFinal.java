package es.ulpgc.eite.clean.mvp.sample.youtube;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.database.ManejadorDataBase;

public class YoutubeFinal extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    // Google Console APIs developer clave
    public static final String DEVELOPER_KEY = "AIzaSyCJ4yOn8M9R-nAM5Lb9GSL5KrbVqo_AL00";

    //public static final String DEVELOPER_KEY = ConfigYoutube.DEVELOPER_KEY;
    //private String VIDEO_ID = ConfigYoutube.YOUTUBE_VIDEO_CODE;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerFragment myYouTubePlayerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);

        //myYouTubePlayerFragment.initialize(ConfigYoutube.DEVELOPER_KEY, YoutubeFinal.this);
        myYouTubePlayerFragment.initialize(DEVELOPER_KEY, YoutubeFinal.this);
    }



    /*
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_dummy, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
  */

    public  String pasarEnlaceYoutube(){
        Mediator app = (Mediator) getApplication();
        int id = app.getIdPlatoSeleccionado();
        ManejadorDataBase miManejador = ManejadorDataBase.getInstance();
        String enlace=miManejador.getEnlaceYoutube(id);
        return enlace;

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            //player.cueVideo(VIDEO_ID);
            Log.d("YOUTUBE", "VIDEO_ID=" + pasarEnlaceYoutube());
            player.cueVideo(pasarEnlaceYoutube());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeplayerfragment);
    }
}
