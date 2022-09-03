package com.zhenmei.wsc.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;
import com.zhenmei.wsc.security.convertor.MapStructConvertor;
import com.zhenmei.wsc.security.mybatis.generate.entity.TAdminUser;
import com.zhenmei.wsc.security.mybatis.generate.mapper.TAdminUserMapper;
import com.zhenmei.wsc.security.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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


        return new ServiceMultiResultVO(pageInfo.getTotal(), MapStructConvertor.INSTANCE.shopDto2shopVos(pageInfo.getRecords()));
    }
}
