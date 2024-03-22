package com.example.wifirecons.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class Helpers {

    public static final byte NAME = -1;
    public static byte[] a;
    public static Context b;
    public static long c;
    public static final char[] d = "0123456789abcdef".toCharArray();
    public static final Object e = new Object();

    public Helpers() {
    }

    public static String a(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2 * 2];

        for(int i3 = 0; i3 < i2; ++i3) {
            int i4 = bArr[i + i3] & 255;
            int i5 = i3 * 2;
            char[] cArr2 = d;
            cArr[i5] = cArr2[i4 >>> 4];
            cArr[i5 + 1] = cArr2[i4 & 15];
        }

        return new String(cArr);
    }

    public static byte[] b() {
        if (a == null) {
            synchronized(e) {
                String i;
                if (a == null && (i = i()) != null) {
                    a = Base64.decode(i, 0);
                }
            }
        }

        return a;
    }

    public static byte[] c() {
        return b() != null ? b() : "sUper0sEcret1kEy2fOr3whEn4we5hAve6no7kEy8yEt".getBytes();
    }

    public static byte[] d(byte[] bArr, byte[] bArr2) {
        int max = Math.max(bArr.length, 64);
        byte[] bArr3 = new byte[max];
        byte[] bArr4 = new byte[max];

        int i2;
        for(i2 = 0; i2 < max; ++i2) {
            bArr4[i2] = 54;
            bArr3[i2] = 92;
        }

        for(i2 = 0; i2 < bArr.length; ++i2) {
            bArr4[i2] ^= bArr[i2];
            bArr3[i2] ^= bArr[i2];
        }

        return k(f(bArr3, k(f(bArr4, bArr2))));
    }

    public static byte[] f(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static Map<String, String> g(String str) {
        HashMap hashMap = new HashMap(2);
        long o = o();
        String m = m(str, o);
        if (!TextUtils.isEmpty(m)) {
            hashMap.put("IB-Request-ID", m);
            hashMap.put("Date", Utils.convertTimestampToFormattedString(o * 1000L));
        }

        return hashMap;
    }

    public static void h(long j) {
        c += j;
    }

    public static String i() {
        try {
            FileInputStream openFileInput = b.openFileInput("facebook_private_prefs.lock");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput));
            StringBuilder sb = new StringBuilder();

            while(true) {
                String sb2 = bufferedReader.readLine();
                if (sb2 == null) {
                    sb2 = sb.toString();
                    if (openFileInput != null) {
                        openFileInput.close();
                    }

                    return sb2;
                }

                sb.append(sb2);
            }
        } catch (Exception var4) {
            return null;
        }
    }

    public static void j(String str) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(b.openFileOutput("facebook_private_prefs.lock", 0));
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (Exception var2) {
        }

    }

    public static byte[] k(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (Exception var2) {
            return bArr;
        }
    }

    public static String m(String str, long j) {
        byte[] c2 = c();
        byte[] d2 = d(ByteBuffer.allocate(c2.length + 4).put(c2).putInt((int)j).array(), str.getBytes());
        d2[6] = (byte)(d2[6] & 15);
        d2[6] = (byte)(d2[6] | 64);
        d2[12] = (byte)(d2[12] & 191);
        d2[12] = (byte)(d2[12] | 128);
        return a(d2, 2, 4) + "-" + a(d2, 0, 2) + "-" + a(d2, 6, 2) + "-" + a(d2, 12, 2) + "-" + a(d2, 18, 2) + a(d2, 14, 4);
    }

    public static long o() {
        return System.currentTimeMillis() / 1000L + c;
    }

    public static void q(String str) {
        a = Base64.decode(str, 0);
        j(str);
    }
}