#include <jni.h>
#include "kodkod_engine_satlab_OpenWBOWeighted.h"

using namespace OpenWBO;

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    make
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_make
  (JNIEnv *, jclass)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    free
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_free
  (JNIEnv *, jobject, jlong)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    addVariables
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_addVariables
  (JNIEnv *, jobject, jlong, jint)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    addHardClause
 * Signature: (J[I)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_addHardClause
  (JNIEnv *, jobject, jlong, jintArray)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    addSoftClauses
 * Signature: (JI[I)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_addSoftClauses
  (JNIEnv *, jobject, jlong, jint, jintArray)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    solve
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_solve
  (JNIEnv *, jobject, jlong)
{
    //TODO
}

/*
 * Class:     kodkod_engine_satlab_OpenWBOWeighted
 * Method:    valueOf
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_kodkod_engine_satlab_OpenWBOWeighted_valueOf
  (JNIEnv *, jobject, jlong, jint)
{
    //TODO
}