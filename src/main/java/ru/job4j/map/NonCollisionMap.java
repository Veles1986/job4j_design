package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public static void main(String[] args) {
    }

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        boolean result = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public V get(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        return !Objects.isNull(table[index])
                && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)
                ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        boolean result = false;
        if (!Objects.isNull(table[index])
                && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        int modCountStatic = this.modCount;
        return new Iterator<K>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (modCount != modCountStatic) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && Objects.isNull(table[index])) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity *= 2;
        for (MapEntry<K, V> mapEntry : table) {
            if (!Objects.isNull(mapEntry)) {
                int hash = hash(Objects.hashCode(mapEntry.key));
                int index = indexFor(hash);
                newTable[index] = mapEntry;
            }
        }
        table = newTable;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
