package com.fx.mvvm.data.network

/**

 * @Author : chenguang

 * @Time : On 2021/9/22 0022 14:37

 * @Description : Url

 */

object Url {
    //校验身份证号接口
    const val CHECK_USER: String = "/zjyzjt/infoPerson/checkUser.do"

    //获取用户token
    const val GET_USER_TOKEN = "/zjyzjt/infoPerson/getUserToken.do"

    //获取用户信息
    const val GET_USER_INFO = "/zjyzjt/homePage/getUserInfo.do"

    //获取首页banner列表
    const val GET_BANNER_LIST = "/zjyzjt/homePage/getBannerList.do"

    //查询报警信息接口
    const val GET_CALL_POLICE = "/zjyzjt/homePage/getPageCallPolice.do"

    //分页消息通知接口
    const val GET_PAGE_MESSAGE = "/zjyzjt/homePage/getPageMessage.do"

}
