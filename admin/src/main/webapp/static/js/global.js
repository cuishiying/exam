/**
 * Created by cuishiying on 2017/6/12.
 */
$(function () {
    /*左侧导航开始*/
    $(document).ready(function () {
        $('ul.nav > li').click(function (e) {
            // e.preventDefault();
            $('ul.nav > li').removeClass('active');
            $(this).addClass('active');
        });
    });
    /*左侧导航结束*/
})