<!DOCTYPE html>
<html lang="en">
<#include "common/head.ftl"/>
<body class="menubar-hoverable header-fixed">
<!-- BEGIN LOGIN SECTION -->
<section class="section-account">
    <div class="img-backdrop" style="background-image: url('/asset/img/2.jpg')"></div>
    <div class="spacer"></div>
    <div class="card contain-sm style-transparent">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-6">
                    <br/>
                    <span class="text-lg text-bold text-primary">艺龙酒店商品库审批平台</span>
                    <br/><br/>
                    <form class="form" action="/login/in" method="post" autocomplete="off">
                        <div class="form-group">
                            <input type="text" class="form-control" id="username" name="username" required>
                            <label for="username">用户名</label>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password" required>
                            <label for="password">密码</label>
                            <p class="help-block">
                                <#if message??>${message!}</#if>
                            </p>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="code" name="code" required>
                            <label for="code">验证码</label>
                            <img class="codeimg" id="codeimg" src="/captcha" height="48px" width="130px"/>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-6 text-left">
                                <div class="checkbox checkbox-inline checkbox-styled">
                                    <label>
                                        <input type="checkbox" name="remember" value="true" checked><span>记住我</span>
                                    </label>
                                </div>
                            </div><!--end .col -->
                            <div class="col-xs-6 text-right">
                                <button class="btn btn-primary btn-raised" type="submit">登 录</button>
                            </div><!--end .col -->
                        </div><!--end .row -->
                    </form>
                </div><!--end .col -->
            </div><!--end .row -->
        </div><!--end .card-body -->
    </div><!--end .card -->
</section>
<!-- END LOGIN SECTION -->
<#include "common/script.ftl"/>
</body>
</html>
