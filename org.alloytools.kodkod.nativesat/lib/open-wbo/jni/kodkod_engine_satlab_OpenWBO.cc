#include <jni.h>
#include "kodkod_engine_satlab_OpenWBO.h"

using namespace OpenWBO;

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    make
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_kodkod_engine_satlab_OpenWBO_make
  (JNIEnv *, jclass)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    free
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_kodkod_engine_satlab_OpenWBO_free
  (JNIEnv *, jobject, jlong)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    addVariables
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_kodkod_engine_satlab_OpenWBO_addVariables
  (JNIEnv *, jobject, jlong, jint)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    addHardClause
 * Signature: (J[I)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBO_addHardClause
  (JNIEnv *, jobject, jlong, jintArray)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    addSoftClauses
 * Signature: (JI[I)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBO_addSoftClauses
  (JNIEnv *, jobject, jlong, jint, jintArray)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    solve
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBO_solve
  (JNIEnv *, jobject, jlong)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBO
 * Method:    valueOf
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBO_valueOf
  (JNIEnv *, jobject, jlong, jint)
{
    //TODO
}