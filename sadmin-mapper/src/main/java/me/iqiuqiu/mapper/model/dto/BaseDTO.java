package me.iqiuqiu.mapper.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BaseDTO<T> {

    protected List<T> list;

    protected T item;

    protected long total;

}
