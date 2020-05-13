import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.BitSet;

public class BloomFilter<T extends Comparable<T>> extends AbstractCollection<T> {
    private final int k;
    private int fill;
    private final int bits;
    private final BitSet bitSet;

    private static final double base = 1 - Math.exp(-Math.log(2));

    public BloomFilter(int bits, int expectedElements) {
        k = (int) ((bits / expectedElements) * Math.log(2));
        fill = 0;
        this.bits = bits;
        bitSet = new BitSet(bits);
    }


    public static double averageError(int bits, int expectedElements) {
        if (bits <= 0 || expectedElements <= 0) return 1;
        return Math.pow(base, (((double) bits) * Math.log(2)) / (double) expectedElements);
    }


    private int getShaHash(T s) {
        try {
            //TODO: change this awful implementation
            //Object.toString() calls hashCode() anyway
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(s.toString().getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toString(b));
            }
            BigInteger integer = new BigInteger(sb.toString().replace("-", ""));
            integer = integer.mod(BigInteger.valueOf(bits));
            int result = integer.intValue();
            if (result < 0) result -= 2 * result;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public void insert(T s) {
        int firstHash = s.hashCode();
        int secondHash = getShaHash(s);
        int temp = firstHash;
        for (int i = 1; i <= k; i++) {
            temp += secondHash;
            temp = temp % bits;
            if (temp < 0) temp -= 2 * temp;
            if (!bitSet.get(temp)) {
                bitSet.set(temp);
                fill++;
            }
        }
    }


    @Override
    public boolean contains(T s) {
        int firstHash = s.hashCode();
        int secondHash = getShaHash(s);
        int temp = firstHash;
        for (int i = 1; i <= k; i++) {
            temp += secondHash;
            temp = temp % bits;
            if (temp < 0) temp -= 2 * temp;
            if (!bitSet.get(temp)) return false;
        }
        return true;
    }


    @Override
    public int size() {
        return fill;
    }
}
