package com.example.Music_App.Activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Music_App.DAO.nhacDAO;
import com.example.Music_App.R;
import com.example.Music_App.model.Nhac;

import java.util.ArrayList;
import java.util.List;

public class Screen_listening_music extends AppCompatActivity {

    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private ImageView imgDia, tym;
    private ObjectAnimator animator;
    private TextView tvTimeStart, tvTimeEnd, tv_tieu_de, tv_ten_bai_hat, tv_ten_ca_si;
    private ImageView img_album_bai_hat, img_tron_bai, img_prev, img_play_pause, img_next, img_phat_1_bai, img_down;
    private int currentTrackIndex = 0;
    private boolean isPlaying = false;
    private boolean isRepeating = false;
    private boolean isShuffling = false;
    private List<Nhac> audioFiles;
    private nhacDAO audioFileDAO;
    private boolean isImageOne = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_listening_music);

        // Initialize UI components
        seekBar = findViewById(R.id.seekBar);
        imgDia = findViewById(R.id.img_dia);
        tvTimeStart = findViewById(R.id.tv_time_start);
        tvTimeEnd = findViewById(R.id.tv_time_end);
        tv_tieu_de = findViewById(R.id.tv_tieu_de);
        tv_ten_bai_hat = findViewById(R.id.tv_ten_bai_hat);
        tv_ten_ca_si = findViewById(R.id.tv_ten_ca_si);
        img_album_bai_hat = findViewById(R.id.img_album_bai_hat);
        img_tron_bai = findViewById(R.id.img_tron_bai);
        img_prev = findViewById(R.id.img_prev);
        img_down = findViewById(R.id.img_down);
        img_play_pause = findViewById(R.id.img_play_pause);
        img_next = findViewById(R.id.img_next);
        img_phat_1_bai = findViewById(R.id.img_phat_1_bai);
        tym = findViewById(R.id.tym);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImageOne){
                    tym.setImageResource(R.drawable.love);
                    Toast toast = Toast.makeText(Screen_listening_music.this,"Bạn Đã thêm bài hát vào Danh Sách Yêu Thích",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.LEFT | Gravity.TOP, 20, 30);
                    toast.show();

                }else {
                    tym.setImageResource(R.drawable.love1);
                    Toast toast = Toast.makeText(Screen_listening_music.this,"Bạn đã bỏ bài hát ra khỏi Danh Sách Yêu Thích",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.LEFT | Gravity.TOP, 20, 30);
                    toast.show();
                }
                isImageOne = !isImageOne;
            }
        });

        img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen_listening_music.this, TRANGCHU.class);
                intent.putExtra("playlist", new ArrayList<>(audioFiles));
                intent.putExtra("currentTrackIndex", currentTrackIndex);
                finish();
               setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });


        audioFiles = (ArrayList<Nhac>) getIntent().getSerializableExtra("playlist");
        currentTrackIndex = getIntent().getIntExtra("currentTrackIndex", 0);

        if (audioFiles != null && !audioFiles.isEmpty()) {
            playTrack(currentTrackIndex);
        } else {
            Log.e("Screen_listening_music", "Audio files list is empty or null");
        }

        // Set up SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() == seekBar.getMax()) {
                    startActivity(new Intent(Screen_listening_music.this, Screen_QuangCao.class));
                    finish();
                }
            }
        });

        img_play_pause.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    img_play_pause.setImageResource(R.drawable.play); // Thay đổi hình ảnh thành Play
                    isPlaying = false;
                    handler.removeCallbacks(runnable); // Dừng cập nhật SeekBar khi dừng phát
                    if (animator != null) {
                        animator.pause(); // Dừng vòng quay đĩa
                    }
                } else {
                    mediaPlayer.start();
                    img_play_pause.setImageResource(R.drawable.pause); // Thay đổi hình ảnh thành Pause
                    isPlaying = true;
                    updateSeekBar(); // Bắt đầu cập nhật SeekBar khi tiếp tục phát
                    if (animator != null) {
                        animator.resume(); // Tiếp tục vòng quay đĩa
                    }
                }
            }
        });


        img_next.setOnClickListener(v -> playNextTrack());
        img_prev.setOnClickListener(v -> playPreviousTrack());
        img_phat_1_bai.setOnClickListener(v -> toggleRepeatMode());
        img_tron_bai.setOnClickListener(v -> toggleShuffleMode());
    }

    private void startRotation() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(imgDia, "rotation", 0f, 360f);
            animator.setDuration(7000);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setRepeatMode(ObjectAnimator.RESTART);
            animator.start();
        } else {
            animator.start(); // Nếu animator đã tồn tại, tiếp tục vòng quay
        }
    }


    private void updateSeekBar() {
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();

            // Cập nhật SeekBar
            seekBar.setMax(duration);
            seekBar.setProgress(currentPosition);

            // Cập nhật thời gian hiện tại
            tvTimeStart.setText(formatTime(currentPosition));

            // Cập nhật thời gian kết thúc (nếu chưa cập nhật)
            if (tvTimeEnd.getText().toString().isEmpty()) {
                tvTimeEnd.setText(formatTime(duration));
            }

            // Tiếp tục cập nhật SeekBar sau mỗi giây
            handler.postDelayed(runnable, 1000);
        }
    }



    private void updateTimeStart() {
        if (mediaPlayer != null) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            tvTimeStart.setText(formatTime(currentPosition));
        }
    }

    private void updateTimeEnd() {
        if (mediaPlayer != null) {
            int duration = mediaPlayer.getDuration();
            tvTimeEnd.setText(formatTime(duration));
        }
    }

    private String formatTime(int milliseconds) {
        int minutes = (milliseconds / 1000) / 60;
        int seconds = (milliseconds / 1000) % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                updateSeekBar();
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (animator != null) {
            animator.cancel(); // Hủy animator khi activity bị hủy
        }
        handler.removeCallbacks(runnable);
    }


    private void playNextTrack() {
        if (audioFiles != null && !audioFiles.isEmpty()) {
            currentTrackIndex = (currentTrackIndex + 1) % audioFiles.size();
            playTrack(currentTrackIndex);
        }
    }

    private void playPreviousTrack() {
        if (audioFiles != null && !audioFiles.isEmpty()) {
            currentTrackIndex = (currentTrackIndex - 1 + audioFiles.size()) % audioFiles.size();
            playTrack(currentTrackIndex);
        }
    }

    private void toggleRepeatMode() {
        isRepeating = !isRepeating;
        img_phat_1_bai.setImageResource(isRepeating ? R.drawable.repeat_on : R.drawable.ic_repeat);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(isRepeating);
        }
    }
    private void toggleShuffleMode() {
        isShuffling = !isShuffling;
        img_tron_bai.setImageResource(isShuffling ? R.drawable.shuffle_on : R.drawable.ic_shuffle);
    }

    private void playTrack(int index) {
        if (audioFiles != null && !audioFiles.isEmpty()) {
            Nhac track = audioFiles.get(index);
            String tenNhac = track.getTenNhac();
            String tenCaSi = track.getTenCaSi();
            String hinhNhac = track.getHinhNhac();
            String fileNhac = track.getFileNhac();

            tv_tieu_de.setText(tenNhac);
            tv_ten_bai_hat.setText(tenNhac);
            tv_ten_ca_si.setText(tenCaSi);

            int resID = getResources().getIdentifier(hinhNhac, "drawable", getPackageName());
            if (resID != 0) {
                imgDia.setImageResource(resID);
                img_album_bai_hat.setImageResource(resID);
            } else {
                imgDia.setImageResource(R.drawable.rosealbum); // Sử dụng hình ảnh mặc định nếu không tìm thấy
            }

            int residnhac = getResources().getIdentifier(fileNhac, "raw", getPackageName());
            if (residnhac != 0) {
                if (mediaPlayer != null) {
                    mediaPlayer.reset();
                }
                mediaPlayer = MediaPlayer.create(this, residnhac);
                mediaPlayer.setOnPreparedListener(mp -> {
                    seekBar.setMax(mediaPlayer.getDuration());
                    updateTimeEnd();
                    mediaPlayer.start();
                    startRotation();
                    updateSeekBar();
                });

                mediaPlayer.setOnCompletionListener(mp -> {
                    if (isRepeating) {
                        // Nếu đang ở chế độ lặp lại, phát lại bài hát hiện tại
                        mediaPlayer.start();
                    } else if (isShuffling) {
                        // Nếu đang ở chế độ xáo trộn, phát bài hát ngẫu nhiên
                        playRandomTrack();
                    } else {
                        // Phát bài hát tiếp theo
                        playNextTrack();
                    }
                });
            } else {
                Log.e("Screen_listening_music", "Tệp âm thanh không tìm thấy");
            }
        }
    }
    private void playRandomTrack() {
        if (audioFiles != null && !audioFiles.isEmpty()) {
            int randomIndex = (int) (Math.random() * audioFiles.size());
            playTrack(randomIndex);
        }
    }

}

