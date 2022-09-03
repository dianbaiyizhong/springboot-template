package com.zhenmei.wsc.security.service;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;
import org.springframework.stereotype.Service;

@Service
public interface AdminUserService {

    public ServiceMultiResultVO list(BasePageForm form);

}
