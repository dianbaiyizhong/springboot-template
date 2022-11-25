package com.zhenmei.wsc.rbac.service;

import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.IdAndPageForm;
import com.zhenmei.wsc.form.RoleAuthorityForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;

import java.util.List;
import java.util.Map;

public interface AdminMenuService {
    public List<Map<String, Object>> list();

}
