package com.keyserv.jni;

public class KeyServJNI {

//  /*
//   * Class:     com_keyserv_jni_KeyServJNI
//   * Method:    KsUnInitalize
//   * Signature: ()I
//   */
//
//    0,
//  JNIEXPORT jint JNICALL Java_com_keyserv_jni_KeyServJNI_KsInitalize
//      (JNIEnv *, jint, jobject, jobject);
//
//  JNIEXPORT jint JNICALL Java_com_keyserv_jni_KeyServJNI_KsUnInitalize
//      (JNIEnv *);

//jint jKeyType, jbyte jKeyNum, jbyte jKeyLen, jbyte jUserNum, jobjectArray jUserName, jobjectArray jKeyId, jobjectArray jKey)

//  JNIEXPORT jint JNICALL Java_com_keyserv_jni_KeyServJNI_KsGetKeyBySharer
//      (JNIEnv *, jint, jbyte, jbyte, jbyte, jobjectArray, jobjectArray, jobjectArray);
//
//  JNIEXPORT jint JNICALL Java_com_keyserv_jni_KeyServJNI_KsGetKeyByKeyID
//      (JNIEnv *, jint, jbyte, jbyte, jobjectArray, jobjectArray);

  static {
    System.loadLibrary("keyserv_jni"); //加载SO库
    System.loadLibrary("keyserv"); //加载SO库
  }
  public static native int KsUnInitalize();

  public static native int KsInitalize(int encrypt, KsQkeyInfo ksQkeyInfo, KsSrvInfo ksSrvInfo);


  public static native int KsGetKeyBySharer(int KeyType, byte keyNum, byte jKeyLen, byte userNum, KsUserName[] userNames, byte[][] keyIds, byte[][] keys);

  public static native int KsGetKeyByKeyID(int KeyType, byte keyNum, byte jKeyLen, byte[][] keyIds, byte[][] keys);
}
