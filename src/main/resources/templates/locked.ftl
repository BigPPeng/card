<!DOCTYPE html>
<html lang="en">
<#include "common/head.ftl"/>
<body class="menubar-hoverable header-fixed">
<!-- BEGIN LOCKED SECTION -->
<section class="section-account">
    <div class="img-backdrop" style="background-image: url('/asset/img/1.jpg')"></div>
    <div class="spacer"></div>
    <div class="card contain-xs style-transparent">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-12">
                    <img class="img-circle" src="/asset/img/logo1.png" alt="" />
                    <h2>${login.userName!}</h2>
                    <form class="form" action="/login" accept-charset="utf-8" method="post" autocomplete="off">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-content">
                                    <input type="hidden" name="username" value="${login.loginName!}">
                                    <input type="hidden" name="rember" value="true">
                                    <input type="password" class="form-control" name="password" required>
                                    <label for="password">密码</label>
                                    <p class="help-block"></p>
                                </div>
                                <div class="input-group-btn">
                                    <button class="btn btn-floating-action btn-primary" type="submit"><i class="fa fa-unlock"></i></button>
                                </div>
                            </div><!--end .input-group -->
                        </div><!--end .form-group -->
                    </form>
                </div><!--end .col -->
            </div><!--end .row -->
        </div><!--end .card-body -->
    </div><!--end .card -->
</section>
<!-- END LOCKED SECTION -->
<#include "common/script.ftl"/>
</body>
</html>
