package com.zhenmei.template.infrastructure.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
