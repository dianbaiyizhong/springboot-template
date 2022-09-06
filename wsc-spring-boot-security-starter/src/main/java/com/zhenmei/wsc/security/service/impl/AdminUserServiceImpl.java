package com.zhenmei.wsc.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.exception.BusinessException;
import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;
import com.zhenmei.wsc.security.convertor.MapStructConvertor;
import com.zhenmei.wsc.security.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.security.mybatis.generate.mapper.TAdminUserMapper;
import com.zhenmei.wsc.security.pojo.form.AdminUserSaveForm;
import com.zhenmei.wsc.security.service.AdminUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String passWord = encode.encode(form.getPassWord());
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
}
