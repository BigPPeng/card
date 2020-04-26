<#macro menubar menu=100>
<!-- BEGIN MENUBAR-->
<div id="menubar" class="animate">
    <div class="menubar-fixed-panel">
        <div>
            <a class="btn btn-icon-toggle btn-default-dark menubar-toggle" data-toggle="menubar" href="javascript:void(0);">
                <i class="fa fa-bars"></i>
            </a>
        </div>
        <div class="expanded">
            <a href="/">
                <span class="text-lg text-bold text-default-dark">Elong Hotel</span>
            </a>
        </div>
    </div>
    <div class="menubar-scroll-panel no-padding">
        <ul id="main-menu" class="gui-controls">
            <li class="gui-folder no-margin active expanded">
                <a href="/">
                    <div class="gui-icon">
                        <i class="fa fa-dashboard fa-fw"></i>
                    </div>
                    <span class="title text-lg">审批平台</span>
                </a>
            </li>
            <#-- 审批人功能管理 -->
            <li class="gui-folder no-margin">
                <a href="javascript:void(0)">
                    <div class="gui-icon"><i class="fa fa-binoculars fa-fw"></i></div>
                    <span class="title text-lg">审批中心</span>
                </a>
                <ul>
                    <li><a href="/audit/selfAudit" <#if menu=201>class="active"</#if>><span class="title">我的审批</span></a></li>
                    <li><a href="/audit/history" <#if menu=202>class="active"</#if>><span class="title">审批历史</span></a></li>
                    <li><a href="/audit/abnormal" <#if menu=203>class="active"</#if>><span class="title">违规修改</span></a></li>
                </ul>
            </li>

            <#-- 申请人功能管理 -->
            <li class="gui-folder no-margin">
                <a href="javascript:void(0)">
                    <div class="gui-icon"><i class="fa fa-cogs fa-fw"></i></div>
                    <span class="title text-lg">申请中心</span>
                </a>
                <ul>
                    <li><a href="/apply/new" <#if menu=301>class="active"</#if>><span class="title">新建审批</span></a></li>
                    <li><a href="/apply/doing" <#if menu=302>class="active"</#if>><span class="title">审批中</span></a></li>
                    <li><a href="/apply/history" <#if menu=303>class="active"</#if>><span class="title">历史审批</span></a></li>
                    <li><a href="/audit/newInstance" <#if menu=301>class="active"</#if>><span class="title">新建审批</span></a></li>
                    <li><a href="/audit/doing" <#if menu=302>class="active"</#if>><span class="title">审批中</span></a></li>
                    <li><a href="/audit/workflow" <#if menu=304>class="active"</#if>><span class="title">审批流程管理</span></a></li>
                </ul>
            </li>

            <#-- 申请人功能管理 -->
            <li class="gui-folder no-margin">
                <a href="javascript:void(0)">
                    <div class="gui-icon"><i class="fa fa-cogs fa-fw"></i></div>
                    <span class="title text-lg">审批人管理</span>
                </a>
                <ul>
                    <li><a href="/approver/role" <#if menu=401>class="active"</#if>><span class="title">角色管理</span></a></li>
                    <li><a href="/approver/user" <#if menu=402>class="active"</#if>><span class="title">审批人管理</span></a></li>
                </ul>
            </li>

            <#-- 用户权限管理 -->
            <li class="gui-folder no-margin">
                <a href="javascript:void(0)">
                    <div class="gui-icon"><i class="fa fa-users fa-fw"></i></div>
                    <span class="title text-lg">用户权限管理</span>
                </a>
                <ul>
                    <li><a href="/manage/user" <#if menu=101>class="active"</#if>><span class="title">用户管理</span></a></li>
                    <li><a href="/manage/role" <#if menu=102>class="active"</#if>><span class="title">角色管理</span></a></li>
                    <li><a href="/manage/permission" <#if menu=103>class="active"</#if>><span class="title">权限管理</span></a></li>
                </ul>
            </li>
        </ul>
        <div class="menubar-foot-panel">
            <small class="no-linebreak hidden-folded">
                <span class="opacity-75">Copyright &copy; ${.now?string("yyyy")}</span> <strong>Elong Hotel</strong>
            </small>
        </div>
    </div>
</div>
<!-- END MENUBAR -->
</#macro>