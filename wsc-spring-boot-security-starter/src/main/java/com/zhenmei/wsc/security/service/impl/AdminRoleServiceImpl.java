package com.zhenmei.wsc.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Splitter;
import com.zhenmei.wsc.form.BasePageForm;
import com.zhenmei.wsc.form.IdAndPageForm;
import com.zhenmei.wsc.form.RoleAuthorityForm;
import com.zhenmei.wsc.response.ServiceMultiResultVO;
import com.zhenmei.wsc.security.convertor.MapStructConvertor;
import com.zhenmei.wsc.security.mybatis.custom.entity.UserRoleEntity;
import com.zhenmei.wsc.security.mybatis.custom.mapper.RoleDao;
import com.zhenmei.wsc.security.mybatis.generate.entity.TRole;
import com.zhenmei.wsc.security.mybatis.generate.entity.TUserRole;
import com.zhenmei.wsc.security.mybatis.generate.mapper.TRoleMapper;
import com.zhenmei.wsc.security.mybatis.generate.mapper.TUserRoleMapper;
import com.zhenmei.wsc.security.service.AdminRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private TRoleMapper roleMapper;

    @Resource
    private RoleDao roleDao;

    @Resource
    private TUserRoleMapper userRoleMapper;

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

    @Override
    public void authorityRole(RoleAuthorityForm form) {

        List<String> roleList = Splitter.on(',').omitEmptyStrings().splitToList(form.getSelectRoles());

        // 取旧数据
        List<TUserRole> oldUserRoleList = userRoleMapper.selectList(new QueryWrapper<TUserRole>()
                .lambda()
                .eq(TUserRole::getUserId, form.getRequestUserId())
        );

        Set<String> newSet = new HashSet<>();
        Set<String> oldSet = new HashSet<>();

        for (String roleId : roleList) {
            newSet.add(roleId);
        }


        for (TUserRole userRole : oldUserRoleList) {
            oldSet.add(userRole.getRoleId().toString());
            // 如果新的数据有，那就忽略
            if (!newSet.contains(userRole.getRoleId().toString())) {
                userRoleMapper.delete(new UpdateWrapper<TUserRole>()
                        .lambda()
                        .eq(TUserRole::getUserId, Long.parseLong(form.getRequestUserId()))
                        .eq(TUserRole::getRoleId, userRole.getRoleId())
                );
            }
        }
        newSet.forEach(i -> {
            if (!oldSet.contains(i)) {
                TUserRole record = new TUserRole();
                record.setUserId(Long.parseLong(form.getRequestUserId()));
                record.setRoleId(Long.valueOf(i));
                userRoleMapper.insert(record);
            }
        });


    }
}
