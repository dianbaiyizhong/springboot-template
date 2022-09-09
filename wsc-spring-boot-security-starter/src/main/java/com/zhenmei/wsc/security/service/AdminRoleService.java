package com.zhenmei.wsc.security.service;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.IdAndPageForm;
import com.zhenmei.wsc.form.RoleAuthorityForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;

public interface AdminRoleService {
    public ServiceMultiResultVO list(BasePageForm form);


    public ServiceMultiResultVO listByUserId(IdAndPageForm form);



    public void authorityRole(RoleAuthorityForm form);

}
