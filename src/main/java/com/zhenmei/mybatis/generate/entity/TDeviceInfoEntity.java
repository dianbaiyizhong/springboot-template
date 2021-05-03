package com.zhenmei.mybatis.generate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author nntk
 * @since 2021-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_device_info")
public class TDeviceInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deviceId;

    private String deviceName;

    private String deviceIp;

    private LocalDateTime gmtCreate;


}
