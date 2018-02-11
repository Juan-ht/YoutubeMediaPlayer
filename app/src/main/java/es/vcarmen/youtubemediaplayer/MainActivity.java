package es.vcarmen.youtubemediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    //API_KEY que nos provee la consola de desarrolladores de google para usar esta libreria
    public static final String API_KEY = "AIzaSyBAMji_5wd0cakS7AiWSlFS9PdGCsj3g6Y";

    //Aquí ponemos el id del video del enlace de youtube
    /*
     Por ejemplo: https://www.youtube.com/watch?v=liczsmUSMA4 en este video
     nos quedamos con: liczsmUSMA4
     */

    Button botonBuscar;
    Button botonReproducir;
    EditText editCadena;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer youTubePlayer;

    public static String VIDEO_ID = "liczsmUSMA4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botonBuscar = findViewById(R.id.buscarVideo);
        botonReproducir = findViewById(R.id.reproducirVideo);
        editCadena = findViewById(R.id.editBuscarVideo);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(
                        Uri.parse("https://www.youtube.com/?hl=es&gl=ES"));
                startActivity(intent);
            }
        });

        botonReproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCadena.getText();
                VIDEO_ID = editCadena.getText() + "";
                Log.v("video_id", VIDEO_ID);
                reproducirVideo(VIDEO_ID);
            }
        });

        //Instanciamos el reproductor del video
        youTubePlayerView = findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }

    //Metodo ejecutado cuando la inicializacion del video no es exitoso
    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Reproduccion fallida", Toast.LENGTH_LONG).show();
        //Log.v("Error", result+ "");
    }


    //Metodo ejecutado cuando la inicializacion de la reproducccion del video es exitosa
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {

        Toast.makeText(this, "Reproduccion exitosa", Toast.LENGTH_LONG).show();

        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        if(youTubePlayer == null){
            youTubePlayer = player;
            player.cueVideo(VIDEO_ID);
        }



        //Log.v("id_video", VIDEO_ID);
    }

    public void reproducirVideo(String videoID){
        if(youTubePlayer != null){
            youTubePlayer.loadVideo(videoID);
        }
    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}