package com.ace.util;

import java.util.List;

public class ListUtils {
    /**
     * Cắt danh sách xuống kích thước lớn nhất sao cho chia hết cho divisor.
     * Nếu danh sách ban đầu có số phần tử không chia hết cho divisor,
     * phương thức sẽ trả về subList chứa các phần tử từ 0 đến (size - remainder).
     *
     * @param list    danh sách đầu vào
     * @param divisor số chia
     * @param <T>     kiểu phần tử trong danh sách
     * @return danh sách đã được cắt (subList của danh sách ban đầu)
     */
    public static <T> List<T> trimListToDivisible(List<T> list, int divisor) {
        if (list == null || divisor <= 0) {
            throw new IllegalArgumentException("List must not be null and divisor must be greater than 0");
        }
        int remainder = list.size() % divisor;
        if (remainder != 0) {
            int newSize = list.size() - remainder;
            return list.subList(0, newSize);
        }
        return list;
    }

    /**
     * Cắt danh sách đầu vào sao cho danh sách kết quả chứa tối đa quantity phần tử.
     * Nếu danh sách có số phần tử ít hơn quantity thì trả về danh sách gốc.
     *
     * @param list     danh sách đầu vào
     * @param quantity số lượng tối đa phần tử cần giữ lại
     * @param <T>      kiểu phần tử trong danh sách
     * @return danh sách đã được cắt
     * @throws IllegalArgumentException nếu list là null hoặc quantity nhỏ hơn 0
     */
    public static <T> List<T> trimListToQuantity(List<T> list, int quantity) {
        if (list == null) {
            throw new IllegalArgumentException("List must not be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        return list.subList(0, Math.min(list.size(), quantity));
    }
}
