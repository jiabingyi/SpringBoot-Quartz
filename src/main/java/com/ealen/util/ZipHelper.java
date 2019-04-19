package com.ealen.util;

import org.apache.commons.codec.binary.Base64;
import sun.nio.cs.ArrayDecoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author lizhedao
 * @since 16-11-11
 * 5600rows bill (1.4M)
 * decompressOpt average:32ms
 * decompress average:15ms
 */
public class ZipHelper {
    static Charset cs = Charset.forName("UTF-8");
    static CharsetDecoder cd = cs.newDecoder()
            .onMalformedInput(CodingErrorAction.REPLACE)
            .onUnmappableCharacter(CodingErrorAction.REPLACE);
    ;

    public static String decompressOpt(String str) throws Throwable {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(decode(str));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        int batch = 48;
        byte[] buffer = new byte[256];
        char[] chars = new char[batch * 256];
        String s;
        int n;
        int time = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
            time++;
            if (time >= batch) {
//                byte[] bytes = out.toByteArray();
//                getChars(out.toByteArray(), chars);
//                decode(bytes,0,bytes.length,chars);
                s = out.toString();
                stringBuilder.append(s);// new String(chars) new String(chars)
                Thread.sleep(1);
                out.reset();
                time = 0;
            }
        }
        if (time > 0)
            stringBuilder.append(out.toString());
        return stringBuilder.toString();
    }

    static char[] decode(byte[] ba, int off, int len, char[] ca) {
//        int en = scale(len, cd.maxCharsPerByte());
//        char[] ca = new char[en];
        if (len == 0)
            return ca;
        if (cd instanceof ArrayDecoder) {
            int clen = ((ArrayDecoder) cd).decode(ba, off, len, ca);
            return ca;
        } else {
            cd.reset();
            ByteBuffer bb = ByteBuffer.wrap(ba, off, len);
            CharBuffer cb = CharBuffer.wrap(ca);
            try {
                CoderResult cr = cd.decode(bb, cb, true);
                if (!cr.isUnderflow())
                    cr.throwException();
                cr = cd.flush(cb);
                if (!cr.isUnderflow())
                    cr.throwException();
            } catch (CharacterCodingException x) {
                // Substitution is always enabled,
                // so this shouldn't happen
                throw new Error(x);
            }
            return ca;
        }
    }

    private static void getChars1(byte[] bytes, char[] chars) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0)
                chars[i] = (char) (bytes[i] + 256);
            else
                chars[i] = ((char) (bytes[i]));
        }
    }

    private static void getChars(byte[] bytes, char[] chars) throws Throwable {
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        char[] array = cb.array();
        for (int i = 0; i < array.length; i++) {
            chars[i] = array[i];
        }
    }

    public static String decompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(decode(str));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
        return out.toString();
    }

    public static byte[] decode(String str) {
        byte[] bt = null;
        Base64 base64 = new Base64();
        bt = base64.decode(str);

        return bt;
    }

    public static String gzip(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return new String(new Base64().encode(out.toByteArray()));
    }

}
