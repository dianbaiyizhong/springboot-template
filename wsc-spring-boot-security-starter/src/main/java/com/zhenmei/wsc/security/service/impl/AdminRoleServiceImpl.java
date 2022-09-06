package com.zhenmei.wsc.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.IdAndPageForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;
import com.zhenmei.wsc.security.convertor.MapStructConvertor;
import com.zhenmei.wsc.security.mybatis.custom.entity.UserRoleEntity;
import com.zhenmei.wsc.security.mybatis.custom.mapper.RoleDao;
import com.zhenmei.wsc.security.mybatis.generate.entity.TRole;
import com.zhenmei.wsc.security.mybatis.generate.mapper.TRoleMapper;
import com.zhenmei.wsc.security.service.AdminRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private TRoleMapper roleMapper;

    @Resource
    private RoleDao roleDao;


    @Override
    public ServiceMultiResultVO list(BasePageForm form) {


        Page<TRole> pager = new Page<>(form.getPage(), form.getRows());
        IPage<TRole> pageInfo = roleMapper.selectPage(pager, new QueryWrapper<TRole>().lambda()
                .orderByDesc(TRole::getGmtCreate)
        );
        return new ServiceMultiResultVO(pageInfo.getTotal(), MapStructConvertor.INSTANCE.roleDo2RoleDto(pageInfo.getRecords()));
    }

    @Override
    public ServiceMultiResultVO listByUserId(IdAndPageForm form) {
        Page page = new Page(form.getPage(), form.getRows());
        QueryWrapper<UserRoleEntity> qw = new QueryWrapper<>();
        qw.eq("user_id", form.getId());
        Page<UserRoleEntity> pageInfo = roleDao.getRoleByUserId(qw, page);

        return new ServiceMultiResultVO(pageInfo.getTotal(), MapStructConvertor.INSTANCE.userRoleDo2Dto(pageInfo.getRecords()));
    }
}
