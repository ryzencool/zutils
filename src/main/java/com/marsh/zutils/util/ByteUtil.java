package com.marsh.zutils.util;




import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author lucky.zhou
 */
public class ByteUtil {

    private static final char[] _hex =
            {
                    '0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
            };



    private ByteUtil() {

    }

    public static byte[] decode(final String s) {
        int len = s.length();
        if (len % 2 != 0) {
            return null;
        }

        byte[] bytes = new byte[len / 2];
        int pos = 0;

        for (int i = 0; i < len; i += 2) {
            byte hi = (byte) Character.digit(s.charAt(i), 16);
            byte lo = (byte) Character.digit(s.charAt(i + 1), 16);
            bytes[pos++] = (byte) (hi * 16 + lo);
        }

        return bytes;
    }

    public static String encode(final byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length << 1);
        for (byte aByte : bytes) {
            sb.append(convertDigit(aByte >> 4));
            sb.append(convertDigit(aByte & 0x0f));
        }
        return sb.toString();
    }



    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    private static char convertDigit(final int value) {
        return _hex[value & 0x0f];
    }


    public static String binaryToHex(InputStream is) {

        StringBuilder sb = new StringBuilder();
        try {
            int value;

            while ((value = is.read()) != -1) {
                sb.append(String.format("%02X", value));
            }
        } catch (IOException e) {
            // ignore
        }
        return sb.toString();
    }

    public static long byteArr2UnsignedInt(byte[] arrByte, int index, int byteNum) {

        long returnLong = -1L;

        // 数组长度判断
        if (arrByte.length < index + byteNum) {
            return returnLong;
        }

        ByteBuffer byteBuffer;
        if (byteNum == 1) {
            returnLong = Byte.toUnsignedInt(arrByte[index]);
            return returnLong;
        } else if (byteNum == 2 || byteNum == 4 || byteNum == 8) {
            byteBuffer = ByteBuffer.allocate(byteNum);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(arrByte, index, byteNum);
            byteBuffer.flip();
        } else {
            // byteNum必须为1，2，4，8
            return returnLong;
        }

        switch (byteNum) {
            case 2:
                returnLong = Short.toUnsignedInt(byteBuffer.getShort());
                break;
            case 4:
                returnLong = Integer.toUnsignedLong(byteBuffer.getInt());
                break;
            case 8:
                returnLong = byteBuffer.getLong();
                if (returnLong < 0) {
                    returnLong = -1;
                }
                break;
        }
        return returnLong;

    }


    /**
     * 将整形unsignedInt转字节数组，数组长度为byteNum
     */
    public static byte[] unsignedInt2ByteArr(long unsignedInt, int byteNum) {
        // 无符号数必须大于零
        if (unsignedInt < 0) {
            return null;
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(byteNum);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        switch (byteNum) {
            case 1:
                byteBuffer.put((byte) unsignedInt);
                break;
            case 2:
                byteBuffer.putShort((short) unsignedInt);
                break;
            case 4:
                byteBuffer.putInt((int) unsignedInt);
                break;
            case 8:
                byteBuffer.putLong(unsignedInt);
                break;
            default:
                // byteNum必须为1，2，4，8
                return null;
        }

        return byteBuffer.array();
    }


}
