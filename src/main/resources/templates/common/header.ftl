<!-- BEGIN HEADER-->
<header id="header" class="header-inverse">
    <div class="headerbar">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="headerbar-left">
            <ul class="header-nav header-nav-options">
                <li class="header-nav-brand" >
                    <div class="brand-holder">
                        <a href="/">
                            <span class="text-lg text-bold text-default-bright">Elong Hotel</span>
                        </a>
                    </div>
                </li>
                <li>
                    <a class="btn btn-icon-toggle menubar-toggle" data-toggle="menubar" href="javascript:void(0);">
                        <i class="fa fa-bars"></i>
                    </a>
                </li>
            </ul>
        </div>
        <div class="headerbar-right">
            <ul class="header-nav header-nav-options">
                <li>
                    <form class="navbar-search" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" name="headerSearch" placeholder="Enter your keyword">
                        </div>
                        <button type="submit" class="btn btn-icon-toggle ink-reaction"><i class="fa fa-search"></i></button>
                    </form>
                </li>
            </ul>
            <ul class="header-nav header-nav-profile">
                <li class="dropdown">
                    <a href="javascript:void(0);" class="dropdown-toggle ink-reaction" data-toggle="dropdown">
                        <img src="/asset/img/logo1.png" alt="" />
                        <span class="profile-info">
                            <#--${Session.user.userName!'0'}-->
                            <small id="loginName"></small>
                        </span>
                    </a>
                    <ul class="dropdown-menu animation-dock">
                        <li><a href="/logout"><i class="fa fa-fw fa-power-off text-danger"></i> 退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</header>
<!-- END HEADER-->