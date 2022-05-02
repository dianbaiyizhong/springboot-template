package com.zhenmei.wsc.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 此类专门为分页数据服务
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ServiceMultiResultVO<T> {
    private long total;


    private List<T> result;

    public ServiceMultiResultVO(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }


}
