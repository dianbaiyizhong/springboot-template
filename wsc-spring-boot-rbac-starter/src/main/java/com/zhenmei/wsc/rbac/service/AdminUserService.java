package com.zhenmei.wsc.rbac.service;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.BatchDeleteForm;
import com.zhenmei.wsc.rbac.pojo.form.AdminUserSaveForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;
import org.springframework.stereotype.Service;

public interface AdminUserService {

    public ServiceMultiResultVO list(BasePageForm form);

    public void saveAdminUser(AdminUserSaveForm form);

    public void deleteUser(BatchDeleteForm form);


}
