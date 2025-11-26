package utils;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;

public class VideoConverter {

    public static File convertAviToMp4(File aviFile) {
        try {
            String mp4Path = aviFile.getAbsolutePath().replace(".avi", ".mp4");
            File mp4File = new File(mp4Path);

            // Video Attributes
            VideoAttributes video = new VideoAttributes();
            video.setCodec("h264");
            video.setBitRate(2000000);
            video.setFrameRate(15);

            // Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("aac");

            // Encoding Attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setOutputFormat("mp4");
            attrs.setVideoAttributes(video);
            attrs.setAudioAttributes(audio);

            // Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(aviFile), mp4File, attrs);

            // Delete AVI file after conversion
            if (aviFile.exists()) {
                aviFile.delete();
            }

            System.out.println("✅ Video converted: " + mp4File.getName());
            return mp4File;

        } catch (EncoderException e) {
            System.out.println("❌ Failed to convert video: " + e.getMessage());
            e.printStackTrace();
            return aviFile; // Return original if conversion fails
        }
    }
}