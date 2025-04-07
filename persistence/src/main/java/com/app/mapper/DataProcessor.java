package com.app.mapper;

public interface DataProcessor<T, U> {
    U mapData(T t);
}
