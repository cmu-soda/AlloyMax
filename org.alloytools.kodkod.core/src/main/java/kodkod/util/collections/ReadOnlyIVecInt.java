package kodkod.util.collections;

import org.sat4j.specs.IVecInt;
import org.sat4j.specs.IteratorInt;

import java.util.NoSuchElementException;

/**
 * A wrapper for an int array that provides read-only access to the array via
 * the IVecInt interface.
 *
 * @author Emina Torlak
 */
public final class ReadOnlyIVecInt implements IVecInt {

    private static final long serialVersionUID = -7689441271777278043L;
    private int[]             vec;

    /**
     * Sets this.vec to the given vector and returns this.
     */
    public IVecInt wrap(int[] vec) {
        this.vec = vec;
        return this;
    }

    @Override
    public int size() {
        return vec.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int unsafeGet(int arg0) {
        return vec[arg0];
    }

    @Override
    public int last() {
        return vec[vec.length - 1];
    }

    @Override
    public int[] toArray() {
        return vec;
    }

    @Override
    public int get(int arg0) {
        if (arg0 < 0 || arg0 >= vec.length)
            throw new IndexOutOfBoundsException("arg0: " + arg0);
        return vec[arg0];
    }

    @Override
    public boolean contains(int arg0) {
        final int[] workArray = vec; // faster access
        for (int i : workArray) {
            if (i == arg0)
                return true;
        }
        return false;
    }

    @Override
    public void copyTo(IVecInt arg0) {
        int argLength = arg0.size();
        final int[] workArray = vec; // faster access
        arg0.ensure(argLength + workArray.length);
        for (int i : workArray) {
            arg0.set(argLength++, i);
        }
    }

    @Override
    public void copyTo(int[] arg0) {
        assert arg0.length >= vec.length;
        System.arraycopy(vec, 0, arg0, 0, vec.length);
    }

    @Override
    public IteratorInt iterator() {
        return new IteratorInt() {

            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < vec.length;
            }

            @Override
            public int next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return vec[cursor++];
            }
        };
    }

    @Override
    public int containsAt(int e) {
        final int[] workArray = vec; // faster access
        for (int n = workArray.length, i = 0; i < n; i++)
            if (workArray[i] == e)
                return i;
        return -1;
    }

    @Override
    public int containsAt(int e, int from) {
        final int[] workArray = vec; // faster access
        if (from < workArray.length)
            for (int n = workArray.length, i = from + 1; i < n; i++)
                if (workArray[i] == e)
                    return i;
        return -1;
    }

    @Override
    public int indexOf(int e) {
        final int[] workArray = vec; // faster access
        for (int i = 0, n = workArray.length; i < n; i++) {
            if (workArray[i] == e)
                return i;
        }
        return -1;
    }

    // unsupported
    @Override
    public void shrink(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shrinkTo(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IVecInt pop() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void growTo(int arg0, int arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ensure(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IVecInt push(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unsafePush(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveTo(IVecInt arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveTo2(IVecInt arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveTo(int[] arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveTo(int arg0, int arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveTo(int i, int[] arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertFirst(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(int arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int arg0, int arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sortUnique() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IVecInt[] subset(int arg0) {
        throw new UnsupportedOperationException();
    }
}
