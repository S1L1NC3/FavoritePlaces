package com.dmd.favoriteplacesjavaversion;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dmd.favoriteplacesjavaversion.enums.FileExtensions;
import com.dmd.favoriteplacesjavaversion.enums.FirebaseFirestoreCollections;
import com.dmd.favoriteplacesjavaversion.enums.FirebaseFirestoreFields;
import com.dmd.favoriteplacesjavaversion.enums.FirebasePaths;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class FirebaseHelper {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;

    public FirebaseHelper(FirebaseAuth mAuth, FirebaseFirestore firebaseFirestore, StorageReference storageReference) {
        this.mAuth = mAuth;
        this.firebaseFirestore = firebaseFirestore;
        this.storageReference = storageReference;
    }

    public void uploadFile(FirebasePaths path, Uri uri, FileExtensions fileExtensions, final FirebaseFirestoreCollections firebaseCollection) {
        UUID uuid = UUID.randomUUID();
        final String imageName = path.toString() + uuid + fileExtensions.toString();

        storageReference.child(imageName).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("storageReferenceUpload", "onSuccess: success");

                HashMap<String, Object> postData = new HashMap<>();
                postData.put("userEmail", mAuth.getCurrentUser().getEmail());
                postData.put("date", FieldValue.serverTimestamp());

                insertCloudFirestore(imageName, postData, firebaseCollection);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("storageReferenceUpload", "onSuccess: fail");
            }
        });
    }

    public void insertCloudFirestore(String imageName, final HashMap<String, Object> postHashMapData, final FirebaseFirestoreCollections collection ){
        StorageReference storageReferenceFile = FirebaseStorage.getInstance().getReference(imageName);
        storageReferenceFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("storageReferenceFile", "onSuccess: success");
                String imageDownloadUri = uri.toString();
                postHashMapData.put(FirebaseFirestoreFields.DOWNLOAD_URL.toString(), imageDownloadUri);

                firebaseFirestore.collection(collection.toString()).add(postHashMapData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Log.i("cloudInsert", "onSuccess: success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("cloudInsert", "onSuccess: fail");
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("storageReferenceFile", "onSuccess: fail");
            }
        });

    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public void setFirebaseFirestore(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    public void setStorageReference(StorageReference storageReference) {
        this.storageReference = storageReference;
    }
}
