package com.fx.mvvm.util

import com.google.gson.Gson
import com.tencent.mmkv.MMKV

/**

 * @Author : chenguang

 * @Time : On 2021/9/24 0024 15:06

 * @Description : SpUtil

 */
object SpUtil {

    fun put(key: String?, value: String?) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun put(key: String?, value: Int) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun put(key: String?, value: Boolean) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun put(key: String?, value: Long) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun put(key: String?, value: ByteArray?) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun put(key: String?, `object`: Any?) {
        MMKV.defaultMMKV().encode(key, Gson().toJson(`object`))
    }

    fun getString(key: String?): String? {
        return MMKV.defaultMMKV().decodeString(key)
    }

    fun getInt(key: String?): Int {
        return MMKV.defaultMMKV().decodeInt(key, -1)
    }

    fun getLong(key: String?): Long {
        return MMKV.defaultMMKV().decodeLong(key, -1)
    }

    fun getBoolean(key: String?): Boolean? {
        return MMKV.defaultMMKV().decodeBool(key, false)
    }

    fun getBytes(key: String?): ByteArray? {
        return MMKV.defaultMMKV().decodeBytes(key, null)
    }


}