package cn.com.boe.cms.datasyncapi.common;

import java.util.function.Supplier;

import org.springframework.beans.BeanUtils;

public interface BaseBean {
    public default <T> T convert(Supplier<T> supplier) {
        T t = supplier.get();
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
