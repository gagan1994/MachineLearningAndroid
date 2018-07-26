package com.example.gagan.mlandroid.dectector;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.io.IOException;

/**
 * Created by Gagan on 6/8/2018.
 */

public class TextDetectorHelper extends BaseDetector {

    @Override
    public String getTitle() {
        return "Text Detector";
    }

    @Override
    public void initFirebaseDetectionObj() {

    }

    @Override
    public void initFirebaseVision(final Uri uri, final Context context) {
        FirebaseVisionImage image = null;
        try {
            image = FirebaseVisionImage.fromFilePath(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance()
                .getVisionTextDetector();
        Task<FirebaseVisionText> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                // Task completed successfully
                                // ...
                                detect(firebaseVisionText);
                                detectionFragment.setImamgeUri(uri);
                                Toast.makeText(context, "successfull", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });
    }

    private void detect(FirebaseVisionText firebaseVisionText) {
        for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks()) {
            Rect boundingBox = block.getBoundingBox();
            Point[] cornerPoints = block.getCornerPoints();
            String text = block.getText();
            String texts = "";
            String lines = "";
            for (FirebaseVisionText.Line line : block.getLines()) {
                // ...

                for (FirebaseVisionText.Element element : line.getElements()) {
                    // ...
                    texts = texts + " " + element.getText();
                }
                lines = lines + "\n" + texts;
                texts = "";
            }

            Log.w("Text= ", lines);
            detectionFragment.setInfoText(lines);

        }
    }


}
