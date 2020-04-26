<#include "menubar.ftl"/>
<#macro html menu=100 title=''>
<!DOCTYPE html>
<html lang="en">
<#include "head.ftl"/>
<body class="menubar-hoverable header-fixed menubar-pin">
    <#include "header.ftl"/>
    <!-- BEGIN BASE-->
    <div id="base">
        <!-- BEGIN CONTENT-->
        <div id="content">
            <!-- BEGIN BLANK SECTION -->
            <section>
                <div class="section-header">
                    <ol class="breadcrumb">
                        <li><a href="/">首页</a></li>
                        <#if title !=''>
                            <li class="active">${title}</li>
                        </#if>
                    </ol>
                </div><!--end .section-header -->
                <div class="section-body contain-lg">
                    <#nested/>
                </div><!--end .section-body -->
            </section>
        </div>
        <@menubar menu=menu/>
        <!-- END CONTENT -->
    </div>
    <!-- END BASE -->
</body>
</html>
</#macro>