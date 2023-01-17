package com.zhenmei.wsc.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Splitter;
import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.exception.BusinessException;
import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.BatchDeleteForm;
import com.zhenmei.wsc.rbac.convertor.MapStructConvertor;
import com.zhenmei.wsc.rbac.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.rbac.mybatis.generate.mapper.TAdminUserMapper;
import com.zhenmei.wsc.rbac.pojo.form.AdminUserSaveForm;
import com.zhenmei.wsc.rbac.service.AdminUserService;
import com.zhenmei.wsc.response.ServiceMultiResultVO;

import com.zm.utils.security.BCryptUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {


    @Resource
    private TAdminUserMapper adminUserMapper;


    @Override
    public ServiceMultiResultVO list(BasePageForm form) {

        Page<TAdminUser> pager = new Page<>(form.getPage(), form.getRows());
        IPage<TAdminUser> pageInfo = adminUserMapper.selectPage(pager, new QueryWrapper<TAdminUser>().lambda()
                .orderByDesc(TAdminUser::getGmtCreate)
        );

        return new ServiceMultiResultVO(pageInfo.getTotal(), MapStructConvertor.INSTANCE.userDo2UserDto(pageInfo.getRecords()));
    }

    @Override
    public void saveAdminUser(AdminUserSaveForm form) {

        TAdminUser adminUser = new TAdminUser();
        adminUser.setUserName(form.getUserName());

        String passWord = BCryptUtils.encrypt(form.getPassWord());
        adminUser.setPassWord(passWord);

        adminUser.setGmtCreate(LocalDateTime.now());

        try {
            adminUserMapper.insert(adminUser);

        } catch (Exception e) {
            if (e.getMessage().contains("SQLIntegrityConstraintViolationException")) {
                throw BusinessException
                        .builder()
                        .restCode(RestCode.CODE_409001)
                        .clientTip("已存在相同用户名")
                        .build();
            }
        }
    }

    @Override
    public void deleteUser(BatchDeleteForm form) {

        System.out.println(form.getIds());
        List<String> userIds = Splitter.on(',').omitEmptyStrings().splitToList(form.getIds());

        for (String userId : userIds) {
            adminUserMapper.deleteById(userId);
        }
    }
}
