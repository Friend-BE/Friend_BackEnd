package com.friend.friend.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;


@Service
@RequiredArgsConstructor
public class FireBaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    public String uploadFiles(MultipartFile file, String nameFile)
            throws IOException, FirebaseAuthException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(nameFile, content, file.getContentType());
        String baseUrl = "https://firebasestorage.googleapis.com/v0/b/friend-327a9.appspot.com/o/"+nameFile+"?alt=media";
        //버킷 변경 시 아래 내용 변경 필요
        return "https://firebasestorage.googleapis.com/v0/b/friend-327a9.appspot.com/o/"+nameFile+"?alt=media";
    }
//    public Image getImgUrl(long userCode){
//        return imageService.getImageByUserCode(userCode);
//    }
}

